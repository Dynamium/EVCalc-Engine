@file:Suppress("unused")

package org.dynamium.evcalc.engine.core.tirepressure

import org.dynamium.evcalc.engine.api.calculation.tirepressure.RideSoftness
import org.dynamium.evcalc.engine.api.calculation.tirepressure.TireType
import org.dynamium.evcalc.engine.core.utils.math.round


private object StartPressure {
    const val tenInch = 2.5F
    const val fourteenInch = 3.1F
    const val sixteenInch = 2.8F
    const val eighteenInch = 2.5F
    const val twentyTwoInch = 1.5F
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
        10 -> StartPressure.tenInch
        14 -> StartPressure.fourteenInch
        15 -> ((StartPressure.fourteenInch + StartPressure.sixteenInch) / 2).round(2).toFloat()
        16 -> StartPressure.sixteenInch
        17 -> ((StartPressure.sixteenInch + StartPressure.eighteenInch) / 2).round(2).toFloat()
        18 -> StartPressure.eighteenInch
        22 -> StartPressure.twentyTwoInch
        else -> {
            if (wheelDiameter < 14) {

            }
            0F
        }
    }

    return calculatedValue
}
