package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.AttachmentEntity
@Dao
interface AttachmentDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(a: AttachmentEntity)
 @Query("SELECT * FROM attachments WHERE customerId = :customerId") suspend fun byCustomer(customerId: String): List<AttachmentEntity>
}