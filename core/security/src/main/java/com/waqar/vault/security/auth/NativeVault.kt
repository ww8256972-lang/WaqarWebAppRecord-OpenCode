package com.waqar.vault.security.auth
object NativeVault { init { System.loadLibrary("waqar_vault") }; external fun deriveKey(passphrase: String, salt: ByteArray): ByteArray }