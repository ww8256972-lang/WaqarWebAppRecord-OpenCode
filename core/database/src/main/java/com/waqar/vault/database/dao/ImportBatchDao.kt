package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.ImportBatchEntity
@Dao
interface ImportBatchDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(batch: ImportBatchEntity)
 @Query("SELECT * FROM import_batches ORDER BY importedAt DESC") suspend fun all(): List<ImportBatchEntity>
}