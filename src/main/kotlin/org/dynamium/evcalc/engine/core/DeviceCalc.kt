package org.dynamium.evcalc.engine.core

abstract class DeviceCalc {
    abstract fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int,
        batteryCycles: Int,
        speed: Int,
        batteryPercentage: Int
    ): Int
}