@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tools

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

internal object CalculationTools {
    fun Int.invertPercents() {
        val val1 = this
        100 - val1
    }

    /*
     * Get offset of one value from another.
     *
     * @property num1 First value, needs to be bigger so offset wont be negative
     * @property num2 Second value, needs to be less than first argument.
     */
    fun getOffsetOfValues(num1: Int, num2: Int): Int {
        return num1 - num2
    }

    fun getPercentageOfOneValueFromAnother() {

    }
}