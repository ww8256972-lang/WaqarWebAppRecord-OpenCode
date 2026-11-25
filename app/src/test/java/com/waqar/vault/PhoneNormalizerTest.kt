package com.waqar.vault
import org.junit.Assert.assertEquals
import org.junit.Test
class PhoneNormalizerTest {
    private fun normalize(raw: String): String {
        val digits = raw.filter { it.isDigit() }
        if (digits.isEmpty()) return ""
        val d = if (digits.length == 10) "91$digits" else if (digits.startsWith("91") && digits.length == 12) digits else digits
        return "+$d"
    }
    @Test fun tenDigitBecomesPlus91() { assertEquals("+919912345678", normalize("9912345678")) }
    @Test fun indianPlusPreserved() { assertEquals("+919912345678", normalize("+919912345678")) }
    @Test fun emptyBecomesEmpty() { assertEquals("", normalize("")) }
}