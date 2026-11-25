package com.waqar.vault.database.di
import android.content.Context
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.security.keystore.VaultKeyProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
 @Provides @Singleton
 fun provideDatabase(@ApplicationContext ctx: Context, keyProvider: VaultKeyProvider): WaqarDatabase = WaqarDatabase.build(ctx, keyProvider.dbPassphrase())
}