@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tools

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

internal object CalculationTools {
    fun Int.invertPercents() {
        val val1 = this
        100 - val1
    }

    fun calculatePercentageFromOneToAnother() {

    }
}