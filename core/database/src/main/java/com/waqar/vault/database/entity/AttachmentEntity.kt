package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="attachments")
data class AttachmentEntity(@PrimaryKey val id:String, val customerId:String?, val projectId:String?, val fileName:String, val mimeType:String?, val localPath:String, val remoteUrl:String?=null, val sizeBytes:Long=0, val createdAt:Long=System.currentTimeMillis())