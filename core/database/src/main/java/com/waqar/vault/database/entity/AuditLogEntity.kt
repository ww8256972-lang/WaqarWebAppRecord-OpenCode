package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="audit_logs")
data class AuditLogEntity(@PrimaryKey val id:String, val actorUid:String?, val actionType:String, val targetCount:Int=0, val deviceKeyHash:String?=null, val threatScore:Int=0, val serverTimestamp:Long?=null, val createdAt:Long=System.currentTimeMillis())