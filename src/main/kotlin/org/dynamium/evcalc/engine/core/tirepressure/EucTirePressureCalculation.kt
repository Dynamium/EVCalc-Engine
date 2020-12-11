@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType
import org.dynamium.evcalc.engine.core.utils.math.round


private object StartValues {
    object Pressure {
        const val tenInch = 2.5F
        const val fourteenInch = 3.1F
        const val sixteenInch = 2.8F
        const val eighteenInch = 2.5F
        const val twentyTwoInch = 1.5F
    }

}

fun calculateEucTirePressure(
    wheelDiameter: Int,
    wheelWidth: Float,
    riderWeight: Int,
    rideSoftness: RideSoftness,
    tireType: TireType
): Float {
    var calculatedValue = 0F

    // ---------------- Step 1/?: Apply wheel diameter ---------------- //

    calculatedValue = when (wheelDiameter) {
        10 -> StartValues.Pressure.tenInch
        14 -> StartValues.Pressure.fourteenInch
        15 -> ((StartValues.Pressure.fourteenInch + StartValues.Pressure.sixteenInch) / 2).round(2).toFloat()
        16 -> StartValues.Pressure.sixteenInch
        17 -> ((StartValues.Pressure.sixteenInch + StartValues.Pressure.eighteenInch) / 2).round(2).toFloat()
        18 -> StartValues.Pressure.eighteenInch
        22 -> StartValues.Pressure.twentyTwoInch
        else -> {
            if (wheelDiameter < 14) {

            }
            0F
        }
    }

    return calculatedValue
}
