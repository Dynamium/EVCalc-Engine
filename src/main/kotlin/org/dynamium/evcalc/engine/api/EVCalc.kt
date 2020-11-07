@file:Suppress("unused")

package org.dynamium.evcalc.engine.api

import org.dynamium.evcalc.engine.core.euc.universal.EucUniversalCalculation


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
        rideStyle: EucRideStyle = EucRideStyle.MEDIUM
    ): Int {
        return when (device) {
            DeviceModel.EUC_UNIVERSAL -> {
                EucUniversalCalculation.calculateMileage(riderWeight, batteryCapacity, airTemp, batteryCycles, speed, rideStyle)
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