package com.waqar.vault.data.di
import com.waqar.vault.data.repository.CustomerRepositoryImpl
import com.waqar.vault.domain.repository.CustomerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module @InstallIn(SingletonComponent::class)
abstract class DataModule {
 @Binds @Singleton abstract fun bindCustomerRepo(impl: CustomerRepositoryImpl): CustomerRepository
}