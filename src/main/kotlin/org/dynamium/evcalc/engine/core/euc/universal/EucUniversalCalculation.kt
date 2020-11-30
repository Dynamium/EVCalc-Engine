package org.dynamium.evcalc.engine.core.euc.universalimport mu.KotlinLoggingimport org.dynamium.evcalc.engine.core.DeviceCalcimport kotlin.math.abs/* * EVCalc Universal Calculation * * This code calculates values that are universal for any unicycle. * For any documentation about inner workings of the universal calculation visit Wiki on GitHub. */// Start constantsprivate const val startRiderWeight = 75private const val startBatteryCapacity = 1555private const val startAirTemperature = 25private const val startBatteryCycles = 100private const val startSpeed = 35private const val startMileage = 88val logger = KotlinLogging.logger{}internal object EucUniversalCalculation : DeviceCalc() {    override fun calculateMileage(        riderWeight: Int,        batteryCapacity: Int,        airTemp: Int,        batteryCycles: Int,        speed: Int,        batteryPercentage: Int    ): Int {        var calculatedValue = startMileage        // ---------------- Preparation step: check if battery capacity and speed aren't 0 ---------------- //        when {            batteryCapacity == 0 -> return 0            speed == 0 -> return 0        }        // ---------------- Step 1/6: tweak end value with battery capacity ---------------- //        calculatedValue += when {            batteryCapacity < startBatteryCapacity ->                -abs(((100 - ((batteryCapacity * 100) / startBatteryCapacity)) * calculatedValue) / 100)            batteryCapacity > startBatteryCapacity ->                (calculatedValue * (((batteryCapacity - startBatteryCapacity).toFloat() / batteryCapacity * 100) / 100)).toInt()            else -> 0        }        // -------------------------- Step 2/6: Apply rider weight ------------------------- //        calculatedValue += when {            riderWeight > startRiderWeight -> {                if (riderWeight - startRiderWeight > 12) {                    var tmp = riderWeight - startRiderWeight                    var endValue = 0                    while (tmp > 12) {                        tmp -= 12                        endValue += 7                    }                    (endValue + (((riderWeight - startRiderWeight).toFloat() / 12 * 100) * 7 / 100)).toInt()                } else {                    -abs((((riderWeight - startRiderWeight).toFloat() / 12 * 100) * 7 / 100).toInt())                }            }            riderWeight < startRiderWeight -> {                if (startRiderWeight - riderWeight > 12) {                    var tmp = startRiderWeight - riderWeight                    var endValue = 0                    while (tmp > 12) {                        tmp -= 12                        endValue += 7                    }                    (endValue + (((startRiderWeight - riderWeight).toFloat() / 12 * 100) * 7 / 100)).toInt()                } else {                    -abs((((startRiderWeight - riderWeight).toFloat() / 12 * 100) * 7 / 100).toInt())                }            }            else -> 0        }        // ----------------------- Step 3/6: Apply air temperature ------------------------- //        calculatedValue += when {            airTemp > startAirTemperature -> if (airTemp >= 36) -abs(airTemp - 36) else airTemp - startAirTemperature            airTemp < startAirTemperature -> startAirTemperature - airTemp            else -> 0        }        // ----------------------- Step 4/6: Apply battery cycles -------------------------- //        calculatedValue += when {            batteryCycles > startBatteryCycles -> {                var tmp = batteryCycles                var endValue = 0                while (tmp > 100) {                    if (tmp <= 800 || tmp <= 500 || tmp <= 200) {                        tmp -= 100                        endValue += 2                    }                    if (tmp <= 700 || tmp <= 600 || tmp <= 400 || tmp <= 300) {                        tmp -= 100                        endValue += 3                    }                }                -abs(endValue)            }            else -> 0        }        // ---------------------------- Step 5/6: Apply speed ------------------------------ //        calculatedValue += when {            speed > startSpeed -> {                if (speed - startSpeed > 10) {                    var tmp = speed - startSpeed                    var endValue = 0                    while (tmp > 10) {                        tmp -= 10                        endValue += 18                    }                    (endValue + (((speed - startSpeed).toFloat() / 10 * 100) * 18 / 100)).toInt()                } else {                    -abs((((speed - startSpeed).toFloat() / 10 * 100) * 18 / 100).toInt())                }            }            speed < startSpeed -> {                if (startSpeed - speed > 10) {                    var tmp = startSpeed - speed                    var endValue = 0                    while (tmp > 10) {                        tmp -= 10                        endValue += 18                    }                    (endValue + (((startSpeed - speed).toFloat() / 10 * 100) * 18 / 100)).toInt()                } else {                    -abs((((startSpeed - speed).toFloat() / 10 * 100) * 8 / 100).toInt())                }            }            else -> 0        }        // -------- Step 6/6: Apply current battery percentage and return the result -------- //        return (calculatedValue * (batteryPercentage.toFloat() / 100F)).toInt()    }}