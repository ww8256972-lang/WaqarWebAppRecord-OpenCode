package com.waqar.vault.database.dao
import androidx.room.*
import com.waqar.vault.database.entity.DomainEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface DomainDao {
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(d: DomainEntity)
 @Query("SELECT * FROM domains ORDER BY domainExpiry ASC") fun allFlow(): Flow<List<DomainEntity>>
}