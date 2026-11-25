package com.waqar.vault.security.di
import com.waqar.vault.security.keystore.SimpleVaultKeyProvider
import com.waqar.vault.security.keystore.VaultKeyProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module @InstallIn(SingletonComponent::class)
abstract class SecurityModule {
 @Binds @Singleton abstract fun bindVaultKeyProvider(impl: SimpleVaultKeyProvider): VaultKeyProvider
}