@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType


/**
 * An internal object with start values for tire pressure calculation.
 *
 */
private object StartValues {
//    object Pressure {
//        const val tenInch = 2.5F
//        const val fourteenInch = 3.1F
//        const val sixteenInch = 2.8F
//        const val eighteenInch = 2.5F
//        const val twentyTwoInch = 1.5F
//    }

    /**
     * Start pressure. It is for 16" wheel.
     */
    const val pressure = 2.5F

    /**
     * Modifiers for making tire pressure for soft rides, offroading, or something in the middle.
     */
    object RideSoftnessModifiers {
        const val soft: Float = 0F
        const val medium: Float = 0F
        const val hard: Float = 0F
    }

    const val wheelDiameterModifier = 0.05F
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

    var calculatedValue: Float = 0F

    // ---------------- Step 1/?: Apply wheel diameter ---------------- //




    return calculatedValue
}
