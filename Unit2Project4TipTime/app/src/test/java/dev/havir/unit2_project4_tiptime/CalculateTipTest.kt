package dev.havir.unit2_project4_tiptime

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateTipTest {
    @Test
    fun returnsFormattedButNotRoundedTipWithCurrencyWhenRoundUpIsFalse() {
        val billAmount = 100.00
        val tipPercentage = 15.01

        val result = calculateTip(
            billAmount = billAmount,
            tipPercentage = tipPercentage,
            roundUp = false
        )

        assertEquals("$15.01", result)
    }

    @Test
    fun returnsFormattedAndRoundedTipWithCurrencyWhenRoundUpIsTrue() {
        val billAmount = 100.00
        val tipPercentage = 15.01

        val result = calculateTip(
            billAmount = billAmount,
            tipPercentage = tipPercentage,
            roundUp = true
        )

        assertEquals("$16.00", result)
    }

    @Test
    fun returnsZeroWithCurrencyWhenBillAmountIsZero() {
        val billAmount = 0.00
        val tipPercentage = 10.15

        val result = calculateTip(
            billAmount = billAmount,
            tipPercentage = tipPercentage,
            roundUp = false
        )

        assertEquals("$0.00", result)
    }

    @Test
    fun returnsZeroWithCurrencyWhenTipPercentageIsZero() {
        val billAmount = 45.77
        val tipPercentage = 0.00

        val result = calculateTip(
            billAmount = billAmount,
            tipPercentage = tipPercentage,
            roundUp = false
        )

        assertEquals("$0.00", result)
    }
}
