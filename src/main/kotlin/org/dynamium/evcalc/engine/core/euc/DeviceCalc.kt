package org.dynamium.evcalc.engine.core.euc

abstract class DeviceCalc {
    abstract fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int,
        batteryCycles: Int,
        speed: Int
    ): Int

    abstract fun calculatePowerConsumption(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int,
        batteryCycles: Int,
        speed: Int,
        distance: Int
    ): Int
}