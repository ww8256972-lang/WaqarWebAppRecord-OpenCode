package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName="customers", indices=[Index("phoneE164"),Index("shopName"),Index("city"),Index("category"),Index("importBatchId"),Index("updatedAt"),Index("deletedAt")])
data class CustomerEntity(@PrimaryKey val id:String, val serialCode:String, val shopName:String, val personName:String, val phoneE164:String, val whatsappE164:String?=null, val hasWhatsapp:Boolean=false, val city:String?=null, val category:String?=null, val tags:String="", val status:String="ACTIVE", val priority:Int=0, val importBatchId:String?=null, val encryptedNotes:String?=null, val hmacSha256:String?=null, val updatedAt:Long=System.currentTimeMillis(), val deletedAt:Long?=null, val createdAt:Long=System.currentTimeMillis())