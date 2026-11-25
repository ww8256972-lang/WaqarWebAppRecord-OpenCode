package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.FollowUpEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface FollowUpDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(f: FollowUpEntity)
 @Query("SELECT * FROM follow_ups WHERE completedAt IS NULL ORDER BY scheduledAt ASC") fun pendingFlow(): Flow<List<FollowUpEntity>>
}