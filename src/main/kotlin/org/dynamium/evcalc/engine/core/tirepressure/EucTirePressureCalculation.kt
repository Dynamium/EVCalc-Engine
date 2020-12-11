@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType


/**
 * An internal object with start values for tire pressure calculation.
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

//    var calculatedValue: Float = when (wheelDiameter) {
//        10 -> StartValues.Pressure.tenInch
//        14 -> StartValues.Pressure.fourteenInch
//        15 -> ((StartValues.Pressure.fourteenInch + StartValues.Pressure.sixteenInch) / 2).round(2).toFloat()
//        16 -> StartValues.Pressure.sixteenInch
//        17 -> ((StartValues.Pressure.sixteenInch + StartValues.Pressure.eighteenInch) / 2).round(2).toFloat()
//        18 -> StartValues.Pressure.eighteenInch
//        22 -> StartValues.Pressure.twentyTwoInch
//        else -> {
//            if (wheelDiameter < 14) {
//
//            }
//            0F
//        }
//    }

    var calculatedValue = StartValues.pressure

    // ---------------- Step 1/?: Apply wheel diameter ---------------- //

    calculatedValue += if (wheelDiameter != 16) {
        if (wheelDiameter > 16)
            StartValues.Modifiers.wheelDiameter * (wheelDiameter - 16)
        else
            StartValues.Modifiers.wheelDiameter * (16 - wheelDiameter)
    } else 0F

    // ---------------- Step 2/?: Apply wheel width ---------------- //


    return calculatedValue
}

