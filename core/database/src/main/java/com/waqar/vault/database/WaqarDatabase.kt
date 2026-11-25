package com.waqar.vault.database
import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SupportFactory
import com.waqar.vault.database.dao.*
import com.waqar.vault.database.entity.*
@Database(entities=[CustomerEntity::class,ImportBatchEntity::class,ProjectEntity::class,DomainEntity::class,FollowUpEntity::class,AuditLogEntity::class,AttachmentEntity::class,MutationOutboxEntity::class], version=1, exportSchema=false)
abstract class WaqarDatabase : RoomDatabase() {
 abstract fun customerDao(): CustomerDao
 abstract fun importBatchDao(): ImportBatchDao
 abstract fun projectDao(): ProjectDao
 abstract fun domainDao(): DomainDao
 abstract fun followUpDao(): FollowUpDao
 abstract fun auditLogDao(): AuditLogDao
 abstract fun attachmentDao(): AttachmentDao
 abstract fun mutationOutboxDao(): MutationOutboxDao
 companion object {
  fun build(context: Context, passphrase: ByteArray): WaqarDatabase {
   val factory = SupportFactory(passphrase)
   return Room.databaseBuilder(context, WaqarDatabase::class.java, "waqar_vault.db")
    .openHelperFactory(factory)
    .addCallback(object : Callback() {
     override fun onCreate(db: SupportSQLiteDatabase) {
      db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS customers_fts USING fts5(shopName, personName, phoneE164, city, content='customers', content_rowid='rowid')")
     }
    }).build()
  }
 }
}