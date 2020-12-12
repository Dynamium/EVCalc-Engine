@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType
import org.dynamium.evcalc.engine.core.utils.math.round


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

    // ---------------- Step 1/?: Prepare data ---------------- //

    val wheelWidthP = wheelWidth.round(3).toFloat()

    // ---------------- Step 2/?: Apply wheel diameter ---------------- //

    calculatedValue += if (wheelDiameter != 16) {
        if (wheelDiameter > 16)
            StartValues.Modifiers.wheelDiameter * (wheelDiameter - 16)
        else
            StartValues.Modifiers.wheelDiameter * (16 - wheelDiameter)
    } else 0F

    // ---------------- Step 3/?: Apply wheel width ---------------- //

    calculatedValue += if (wheelWidthP != 2.125F) {
        if (wheelWidthP > 2.125) {
            if (wheelWidthP - 2.125 > 0.4) {
                var tmp = 0
                var tmp2 = wheelWidthP

                while (tmp2 > 0.4) {
                    tmp2 -= 0.4F
                    tmp += 1
                }

                StartValues.Modifiers.wheelWidth
            } else {
                0F
            }
        } else 0F
    } else 0F

    return calculatedValue
}

