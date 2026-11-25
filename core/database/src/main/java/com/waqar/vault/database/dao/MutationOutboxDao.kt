package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.MutationOutboxEntity
@Dao
interface MutationOutboxDao {
 @Insert(onConflict=OnConflictStrategy.IGNORE) suspend fun insert(m: MutationOutboxEntity)
 @Query("SELECT * FROM mutation_outbox WHERE status = 'PENDING' ORDER BY createdAt ASC") suspend fun pending(): List<MutationOutboxEntity>
 @Query("UPDATE mutation_outbox SET status = :status WHERE id = :id") suspend fun updateStatus(id: String, status: String)
}