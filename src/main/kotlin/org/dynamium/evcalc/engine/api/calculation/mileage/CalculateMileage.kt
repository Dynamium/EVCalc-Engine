@file:Suppress("unused")

package org.dynamium.evcalc.engine.api.calculation.mileage

import org.dynamium.evcalc.engine.api.EVCalc
import org.dynamium.evcalc.engine.api.calculation.DeviceModel
import org.dynamium.evcalc.engine.core.euc.universal.EucUniversalCalculation

/**
 * A function to calculate mileage on one charge.
 *
 * @param riderWeight Indicates the weight of a rider
 * @param batteryCapacity Indicates an internal device's battery capacity.
 * @param airTemp Indicates the outer air temperature.
 * @param batteryCycles Indicates how many times the internal battery was cycled through charge-discharge process
 * @param speed Indicates, well, the device's average speed. I actually tried to make variable names very easy to understand, idk why d you even read this, everything is obvious
 * @param device Indicates, well, the device. Uses `DeviceModel` enum class.
 *
 * @return An approximate mileage
 */
fun EVCalc.calculateMileage(
    device: DeviceModel,
    riderWeight: Int,
    batteryCapacity: Int,
    airTemp: Int = 28,
    batteryCycles: Int = 100,
    speed: Int,
    batteryPercentage: Int = 100
): Int {
    return when (device) {
        DeviceModel.EUC_UNIVERSAL ->
            EucUniversalCalculation.calculateMileage(
                riderWeight,
                batteryCapacity,
                airTemp,
                batteryCycles,
                speed,
                batteryPercentage
            )
        else -> throw NotImplementedError(
            "EVCalc API Error: not implemented. \n" +
                    "If this behaviour is unexpected, please report this error on " +
                    "GitHub Issues https://github.com/Dynamium/EVCalc/issues with all needed data."
        )
    }
}