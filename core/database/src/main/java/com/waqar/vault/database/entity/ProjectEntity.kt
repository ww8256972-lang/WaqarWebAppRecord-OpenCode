package com.waqar.vault.database.entity
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName="projects", indices=[Index("customerId"),Index("stage")])
data class ProjectEntity(@PrimaryKey val id:String, val customerId:String, val projectType:String, val title:String, val techStack:String="", val repoUrl:String?=null, val liveUrl:String?=null, val milestones:String="", val completionPercent:Int=0, val stage:String="REQUIREMENT", val deliveryDate:Long?=null, val updatedAt:Long=System.currentTimeMillis())