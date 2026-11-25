package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.AuditLogEntity
@Dao
interface AuditLogDao {
 @Insert suspend fun insert(log: AuditLogEntity)
 @Query("SELECT * FROM audit_logs ORDER BY createdAt DESC LIMIT :limit") suspend fun recent(limit: Int = 100): List<AuditLogEntity>
}