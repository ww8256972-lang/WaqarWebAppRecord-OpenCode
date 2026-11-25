package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName="follow_ups", indices=[Index("scheduledAt"),Index("completedAt")])
data class FollowUpEntity(@PrimaryKey val id:String, val customerId:String, val scheduledAt:Long, val channel:String="CALL", val note:String?=null, val outcome:String?=null, val completedAt:Long?=null, val assignedUid:String?=null, val recurringRule:String?=null)