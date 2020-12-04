@file:Suppress("unused")

package org.dynamium.evcalc.engine.api.calculation.tirepressure

import org.dynamium.evcalc.engine.api.EVCalc
import org.dynamium.evcalc.engine.api.ExperimentalEvcalcApi
import org.dynamium.evcalc.engine.core.tirepressure.calculateEucTirePressure

@ExperimentalEvcalcApi
fun EVCalc.calculateTirePressure(
    wheelDiameter: Int,
    wheelWidth: Float,
    riderWeight: Int,
    rideSoftness: RideSoftness,
    tireType: TireType
): Float {
    return calculateEucTirePressure(wheelDiameter, wheelWidth, riderWeight, rideSoftness, tireType)
}