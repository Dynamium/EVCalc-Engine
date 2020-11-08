@file:Suppress("unused")

package org.dynamium.evcalc.engine.api

import org.dynamium.evcalc.engine.core.euc.universal.EucUniversalCalculation


/*
 * EVCalc Calculation Engine
 *
 * This is the main API File. For more info visit Wiki on GitHub.
 */

/**
 * A main entry point for EVCalc Engine API.
 */
object EVCalc {

    /**
     * A function to calculate mileage on one charge.
     *
     * @param riderWeight Indicates the weight of a rider
     * @param batteryCapacity Indicates an internal device's battery capacity.
     * @param airTemp Indicates the outer air temperature.
     * @param batteryCycles Indicates how many times the internal battery was cycled through charge-discharge process
     * @param speed Indicates, well, the device's average speed. I actually tried to make variable names very easy to understand, idk why d you even read this, everything is obvious
     * @param device Indicates, well, the device. Uses `DeviceModel` enum class.
     */
    fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int = 28,
        batteryCycles: Int = 100,
        speed: Int,
        device: DeviceModel
    ): Int {
        return when (device) {
            DeviceModel.EUC_UNIVERSAL -> {
                EucUniversalCalculation.calculateMileage(riderWeight, batteryCapacity, airTemp, batteryCycles, speed)
            }
            DeviceModel.KINGSONG_KS14SMD -> {
                TODO("Make API implementation for KingSong KS-14SMD")
            }
            else -> {
                throw NotImplementedError(
                    "EVCalc API Error: not implemented. \n" +
                            "If this behaviour is unexpected, please report this error on " +
                            "GitHub Issues https://github.com/Dynamium/EVCalc/issues with all needed data."
                )
            }
        }
    }
}