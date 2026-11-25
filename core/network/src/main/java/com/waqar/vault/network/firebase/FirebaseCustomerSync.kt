package com.waqar.vault.network.firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import kotlinx.coroutines.tasks.await
class FirebaseCustomerSync(private val db: FirebaseFirestore) {
 suspend fun pushBatch(collection: String, payloads: List<Map<String, Any?>>): Int {
  var count = 0
  payloads.chunked(500).forEach { chunk ->
   val batch: WriteBatch = db.batch()
   chunk.forEach { p -> val ref = db.collection(collection).document(p["id"] as String); batch.set(ref, p) }
   batch.commit().await(); count += chunk.size
  }
  return count
 }
 suspend fun pullSince(collection: String, watermark: Long): List<Map<String, Any?>> {
  val snap = db.collection(collection).whereGreaterThan("updatedAt", watermark).get().await()
  return snap.documents.mapNotNull { it.data }
 }
}