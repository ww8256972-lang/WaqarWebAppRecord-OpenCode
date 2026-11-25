package com.waqar.vault.sync.work
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.waqar.vault.database.WaqarDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class OutboxSyncWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
 override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
  val db = WaqarDatabase.build(applicationContext, "dev".toByteArray())
  db.mutationOutboxDao().pending().forEach { db.mutationOutboxDao().updateStatus(it.id, "DONE") }
  Result.success()
 }
}