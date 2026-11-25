package com.waqar.vault.security.ndk
object AntiTamper {
 init { System.loadLibrary("waqar_vault") }
 external fun verifyApkSignature(): Boolean
 external fun checkTracerPid(): Boolean
 external fun checkFridaThreads(): Boolean
 external fun checkMapsForFrida(): Boolean
}