package com.waqar.vault.sync
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.waqar.vault.sync.work.OutboxSyncWorker
import java.util.concurrent.TimeUnit
object SyncScheduler {
 fun schedule(context: Context) {
  val req = PeriodicWorkRequestBuilder<OutboxSyncWorker>(15, TimeUnit.MINUTES).build()
  WorkManager.getInstance(context).enqueueUniquePeriodicWork("waqar_outbox_sync", ExistingPeriodicWorkPolicy.KEEP, req)
 }
}