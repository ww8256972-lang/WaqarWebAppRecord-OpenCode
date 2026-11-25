package com.waqar.vault.database.dao
import androidx.paging.PagingSource
import androidx.room.*
import com.waqar.vault.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface CustomerDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(customer: CustomerEntity)
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsertAll(customers: List<CustomerEntity>)
 @Query("SELECT * FROM customers WHERE deletedAt IS NULL ORDER BY shopName ASC") fun pagingSource(): PagingSource<Int, CustomerEntity>
 @Query("SELECT COUNT(*) FROM customers WHERE deletedAt IS NULL") fun countFlow(): Flow<Int>
 @Query("SELECT * FROM customers WHERE id = :id") suspend fun getById(id: String): CustomerEntity?
 @Query("UPDATE customers SET deletedAt = :ts WHERE id = :id") suspend fun softDelete(id: String, ts: Long = System.currentTimeMillis())
 @Query("DELETE FROM customers WHERE id = :id") suspend fun hardDelete(id: String)
 @Query("DELETE FROM customers") suspend fun deleteAll()
 @Query("SELECT * FROM customers WHERE importBatchId = :batchId") suspend fun getByBatch(batchId: String): List<CustomerEntity>
}