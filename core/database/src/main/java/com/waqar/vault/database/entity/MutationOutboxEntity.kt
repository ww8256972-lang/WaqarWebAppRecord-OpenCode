package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="mutation_outbox")
data class MutationOutboxEntity(@PrimaryKey val id:String, val entityType:String, val entityId:String, val operation:String, val payloadJson:String, val createdAt:Long=System.currentTimeMillis(), val retryCount:Int=0, val status:String="PENDING")