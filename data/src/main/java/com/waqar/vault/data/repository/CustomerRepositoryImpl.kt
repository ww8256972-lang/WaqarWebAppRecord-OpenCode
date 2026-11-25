package com.waqar.vault.data.repository
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.waqar.vault.data.mapper.toDomain
import com.waqar.vault.data.mapper.toEntity
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.domain.model.Customer
import com.waqar.vault.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class CustomerRepositoryImpl @Inject constructor(private val db: com.waqar.vault.database.WaqarDatabase) : CustomerRepository {
 override fun pagingCustomers(): Flow<PagingData<Customer>> = Pager(PagingConfig(pageSize=50, prefetchDistance=25, maxSize=250)){ db.customerDao().pagingSource() }.flow.map { it.map { e -> e.toDomain() } }
 override suspend fun getById(id: String): Customer? = db.customerDao().getById(id)?.toDomain()
 override suspend fun upsert(customer: Customer) = db.customerDao().upsert(customer.toEntity())
 override suspend fun upsertAll(customers: List<Customer>) = db.customerDao().upsertAll(customers.map { it.toEntity() })
 override suspend fun softDelete(id: String) = db.customerDao().softDelete(id)
 override suspend fun deleteAll() = db.customerDao().deleteAll()
}