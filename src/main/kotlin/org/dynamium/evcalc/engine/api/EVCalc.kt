@file:Suppress("unused")

package org.dynamium.evcalc.engine.api

import org.dynamium.evcalc.engine.core.euc.universal.UniversalCalculation


/*
 * EVCalc Calculation Engine
 *
 * This is the main API File. For more info visit Wiki on GitHub.
 */

object EVCalc {
    fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int = 28,
        batteryCycles: Int = 100,
        speed: Int,
        device: DeviceModel,
        rideStyle: EucRideStyle
    ): Int {
        val calculatedValue: Int
        when (device) {
            DeviceModel.EUC_UNIVERSAL -> {
                calculatedValue =
                    UniversalCalculation.calculateMileage(riderWeight, batteryCapacity, airTemp, batteryCycles, speed)
            }
            else -> {
                throw NotImplementedError(
                    "EVCalc API Error: not implemented. \n" +
                            "If this behaviour is unexpected, please report this error on " +
                            "GitHub Issues https://github.com/Dynamium/EVCalc/issues with all needed data."
                )
            }
        }
        return calculatedValue
    }
}