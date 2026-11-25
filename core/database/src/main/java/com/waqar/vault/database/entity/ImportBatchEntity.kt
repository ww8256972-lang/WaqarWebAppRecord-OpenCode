package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="import_batches")
data class ImportBatchEntity(@PrimaryKey val id:String, val fileName:String, val totalRows:Int, val importedCount:Int, val skippedDuplicates:Int, val operatorUid:String?, val importedAt:Long=System.currentTimeMillis(), val status:String="COMPLETED")