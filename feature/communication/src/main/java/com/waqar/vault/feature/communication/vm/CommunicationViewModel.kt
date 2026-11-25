package com.waqar.vault.feature.communication.vm
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
@HiltViewModel
class CommunicationViewModel @Inject constructor(@ApplicationContext private val ctx: Context) : ViewModel() {
 fun openWhatsAppChat(phoneE164: String) {
  val uri = Uri.parse("https://wa.me/${phoneE164.replace("+", "")}")
  ctx.startActivity(Intent(Intent.ACTION_VIEW, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
 }
 fun openWhatsAppCall(phoneE164: String) {
  val uri = Uri.parse("https://wa.me/${phoneE164.replace("+", "")}?call")
  ctx.startActivity(Intent(Intent.ACTION_VIEW, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
 }
 fun dial(phoneE164: String) {
  val uri = Uri.parse("tel:$phoneE164")
  ctx.startActivity(Intent(Intent.ACTION_DIAL, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
 }
}