package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName="domains", indices=[Index("domainExpiry")])
data class DomainEntity(@PrimaryKey val id:String, val customerId:String?, val domainName:String, val registrar:String?=null, val hostingProvider:String?=null, val sslExpiry:Long?=null, val domainExpiry:Long?=null, val dnsSnapshot:String?=null, val updatedAt:Long=System.currentTimeMillis())