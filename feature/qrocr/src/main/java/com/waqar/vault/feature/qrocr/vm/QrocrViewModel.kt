package com.waqar.vault.feature.qrocr.vm
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class QrocrViewModel @Inject constructor() : ViewModel() {
    fun generateQr(content: String): String = "QR:$content"
}