package com.waqar.vault.security.keystore

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimpleVaultKeyProvider @Inject constructor(
    private val context: Context
) : VaultKeyProvider {

    companion object {
        private const val KEYSTORE_ALIAS = "waqar_vault_master_key"
        private const val PREFS_NAME = "waqar_vault_secure_prefs"
        private const val PASSPHRASE_KEY = "sqlcipher_passphrase_b64"
        private const val GCM_IV_KEY = "sqlcipher_passphrase_iv_b64"
        private const val PASSPHRASE_LENGTH = 32
        private const val GCM_TAG_LENGTH = 128
    }

    private val masterKey: MasterKey by lazy {
        val builder = MasterKey.Builder(context, KEYSTORE_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        // Prefer StrongBox when available (API 28+), fall back to TEE
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
            if (!keyStore.containsAlias(KEYSTORE_ALIAS)) {
                val spec = KeyGenParameterSpec.Builder(
                    KEYSTORE_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .setIsStrongBoxBacked(true)
                    .setUserAuthenticationRequired(false)
                    .build()
                val keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore"
                )
                keyGenerator.init(spec)
                keyGenerator.generateKey()
            }
        } catch (e: Exception) {
            // StrongBox not available — fall back to TEE-backed key
            val spec = KeyGenParameterSpec.Builder(
                KEYSTORE_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .setUserAuthenticationRequired(false)
                .build()
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore"
            )
            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }
        builder.build()
    }

    private val encryptedPrefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun dbPassphrase(): ByteArray {
        val stored = encryptedPrefs.getString(PASSPHRASE_KEY, null)
        val ivStored = encryptedPrefs.getString(GCM_IV_KEY, null)

        if (stored != null && ivStored != null) {
            // Decrypt existing passphrase
            val encryptedBytes = Base64.decode(stored, Base64.NO_WRAP)
            val iv = Base64.decode(ivStored, Base64.NO_WRAP)
            val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
            val secretKey = keyStore.getKey(KEYSTORE_ALIAS, null) as SecretKey
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(GCM_TAG_LENGTH, iv))
            val passphrase = cipher.doFinal(encryptedBytes)
            return passphrase
        }

        // Generate new CSPRNG 32-byte passphrase
        val passphrase = ByteArray(PASSPHRASE_LENGTH)
        SecureRandom().nextBytes(passphrase)

        // Encrypt with Keystore-backed AES-256-GCM
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        val secretKey = keyStore.getKey(KEYSTORE_ALIAS, null) as SecretKey
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encrypted = cipher.doFinal(passphrase)
        val iv = cipher.iv

        encryptedPrefs.edit()
            .putString(PASSPHRASE_KEY, Base64.encodeToString(encrypted, Base64.NO_WRAP))
            .putString(GCM_IV_KEY, Base64.encodeToString(iv, Base64.NO_WRAP))
            .apply()

        return passphrase
    }
}