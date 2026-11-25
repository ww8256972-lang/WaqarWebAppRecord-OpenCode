package com.waqar.vault.security
import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
class SecurityHmacTest {
    @Test fun hmacWorks() {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec("key".toByteArray(), "HmacSHA256"))
        val digest = mac.doFinal("data".toByteArray())
        assertNotNull(digest)
    }
}