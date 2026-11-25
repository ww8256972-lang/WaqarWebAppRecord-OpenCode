package com.waqar.vault.feature.performance.vm
import android.content.Context
import androidx.lifecycle.ViewModel
import com.waqar.vault.database.WaqarDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.system.measureTimeMillis
@HiltViewModel
class PerformanceViewModel @Inject constructor(@ApplicationContext private val ctx: Context, private val db: WaqarDatabase) : ViewModel() {
    fun getRuntimeMetrics(): String = "Max Heap: ${runtime.maxMemory()/1048576} MB\nAvailable: ${runtime.freeMemory()/1048576} MB"
    private val runtime get() = android.app.ActivityManager.getSystemService(Context.ACTIVITY_SERVICE as android.content.Context)?.let { (it as android.app.ActivityManager).runningTasks?.size ?: 0 }
        ?: 0
    fun getRamInfo(): String {
        val mi = android.os.Debug.getMemoryInfo()
        return "Private: ${mi.privateDirty/1024} KB\nShared: ${mi.sharedDirty/1024} KB\nTotal: ${mi.totalPrivateDirty/1024} KB"
    }
    fun benchmark50Page(): Long = measureTimeMillis { runCatching { db.customerDao().pagingSource().let { } } }
}