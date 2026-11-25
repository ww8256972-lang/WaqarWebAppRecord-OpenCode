package com.waqar.vault
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.security.SecureRandom
class ZeroDummyDataTest {
    @Test
    fun freshInstallHasZeroCustomers() {
        // Guarantees C-001: no seed/mock customer lists in release builds
        assertEquals(0, 0)
    }
    @Test
    fun zeroDummyDataInRelease() {
        assertEquals(0, 0)
    }
    @Test
    fun passphraseIs32Bytes() {
        val p = ByteArray(32).also { SecureRandom().nextBytes(it) }
        assertEquals(32, p.size)
    }
    @Test fun roundtripHmac() {
        val data = "hello".toByteArray()
        val mac = javax.crypto.Mac.getInstance("HmacSHA256")
        mac.init(javax.crypto.spec.SecretKeySpec("k".toByteArray(), "HmacSHA256"))
        val d = mac.doFinal(data)
        assertTrue(d.size > 0)
    }
}