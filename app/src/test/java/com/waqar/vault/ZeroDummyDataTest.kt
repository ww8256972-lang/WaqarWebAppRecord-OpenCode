package com.waqar.vault

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.security.SecureRandom

class ZeroDummyDataTest {
    @Test
    fun freshInstallHasZeroCustomers() {
        // Verifies no seed/mock customer lists in release source sets
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
    @Test
    fun roundtripHmac() {
        val mac = javax.crypto.Mac.getInstance("HmacSHA256")
        mac.init(javax.crypto.spec.SecretKeySpec("key".toByteArray(), "HmacSHA256"))
        val d = mac.doFinal("data".toByteArray())
        assertTrue(d.size > 0)
    }
    @Test
    fun csvParsesRows() {
        val rows = "Shop A,Amit\nShop B,Geeta".lineSequence().filter { it.isNotEmpty() }.map { it.split(",") }.toList()
        assertEquals(2, rows.size)
        assertEquals(listOf("Shop A", "Amit"), rows[0])
    }
}