@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType


/**
 * An internal object with start values for tire pressure calculation.
 *
 * Every start value assumes that you have a 16" wheel
 *
 */
private object StartValues {
    /**
     * Start pressure. It is for 16" wheel.
     */
    const val pressure = 2.5F


    object Modifiers {
        const val wheelDiameter = 0.05F

        const val wheelWidth = 0.1F

        /**
         * Modifiers for making tire pressure for soft rides, offroading, or something in the middle.
         */
        object RideSoftness {
            const val soft = 0F
            const val medium = 0F
            const val hard = 0F
        }
    }
}

fun calculateEucTirePressure(
    wheelDiameter: Int,
    wheelWidth: Float,
    riderWeight: Int,
    rideSoftness: RideSoftness,
    tireType: TireType
): Float {

    var calculatedValue = StartValues.pressure

    // ---------------- Step 1/?: Apply wheel diameter ---------------- //

    calculatedValue += if (wheelDiameter != 16) {
        if (wheelDiameter > 16)
            StartValues.Modifiers.wheelDiameter * (wheelDiameter - 16)
        else
            StartValues.Modifiers.wheelDiameter * (16 - wheelDiameter)
    } else 0F

    // ---------------- Step 2/?: Apply wheel width ---------------- //

    calculatedValue += if (wheelWidth != 2.125F) {
        0F
    } else 0F

    return calculatedValue
}

