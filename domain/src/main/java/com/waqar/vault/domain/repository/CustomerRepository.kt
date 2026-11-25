package com.waqar.vault.domain.repository
import androidx.paging.PagingData
import com.waqar.vault.domain.model.Customer
import kotlinx.coroutines.flow.Flow
interface CustomerRepository {
 fun pagingCustomers(): Flow<PagingData<Customer>>
 suspend fun getById(id: String): Customer?
 suspend fun upsert(customer: Customer)
 suspend fun upsertAll(customers: List<Customer>)
 suspend fun softDelete(id: String)
 suspend fun deleteAll()
}