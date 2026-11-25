package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ProjectDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(p: ProjectEntity)
 @Query("SELECT * FROM projects ORDER BY updatedAt DESC") fun allFlow(): Flow<List<ProjectEntity>>
}