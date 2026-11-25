package com.waqar.vault.feature.excelimport
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
class ExcelImportParserTest {
    private fun parseCsv(text: String): List<List<String>> = text.lineSequence().filter { it.isNotEmpty() }.map { it.split(",") }.toList()
    @Test fun parsesCsvRows() {
        val rows = parseCsv("Shop A,Amit\nShop B,Geeta")
        assertEquals(2, rows.size)
        assertEquals(listOf("Shop A", "Amit"), rows[0])
    }
    @Test fun detectsShopAndPersonColumns() {
        val header = listOf("Shop Name", "Business", "Person Name")
        assertEquals(0, header.indexOfFirst { it.contains("Shop") })
        assertEquals(2, header.indexOfFirst { it.contains("Person") })
    }
}