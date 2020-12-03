@file:Suppress("unused")

package org.dynamium.evcalc.engine.api.calculation.tirepressure

import org.dynamium.evcalc.engine.api.EVCalc
import org.dynamium.evcalc.engine.api.ExperimentalEvcalcApi
import org.dynamium.evcalc.engine.core.tirepressure.calculateEucTirePressure

@ExperimentalEvcalcApi
fun EVCalc.calculateTirePressure(
    wheelDiameter: Int,
    riderWeight: Int,
    rideSoftness: RideSoftness,
    tireType: TireType
): Int {
    return calculateEucTirePressure(wheelDiameter, riderWeight, rideSoftness, tireType)
}