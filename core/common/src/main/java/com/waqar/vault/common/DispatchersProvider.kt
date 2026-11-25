package com.waqar.vault.common
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
interface DispatchersProvider {
 val io: CoroutineDispatcher get() = Dispatchers.IO
 val main: CoroutineDispatcher get() = Dispatchers.Main
 val default: CoroutineDispatcher get() = Dispatchers.Default
}