package org.dynamium.evcalc.engine.core.euc.universal

import mu.KotlinLogging
import org.dynamium.evcalc.engine.core.euc.OffsetApplierAttribute
import org.dynamium.evcalc.engine.core.euc.OffsetApplierAttribute.*
import org.dynamium.evcalc.engine.core.tools.CalculationTools
import org.dynamium.evcalc.engine.core.tools.CalculationTools.getOffsetOfValues
import kotlin.math.abs

/*
 * EVCalc Universal Calculation
 *
 * This code calculates values that are universal for any unicycle.
 * For any documentation about inner workings of the universal calculation visit Wiki on GitHub.
 */

// Start constants
private const val startRiderWeight = 75
private const val startBatteryCapacity = 1555
private const val startAirTemperature = 25
private const val startBatteryCycles = 100
private const val startSpeed = 35
private const val startMileage = 88

private val logger = KotlinLogging.logger {}

internal object EucUniversalCalculation {
    fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int,
        batteryCycles: Int,
        speed: Int,
    ): Int {
        var calculatedValue = startMileage

        logger.debug { "Received values are: riderWeight = $riderWeight, batteryCapacity = $batteryCapacity, airTemp = $airTemp, batteryCycles = $batteryCycles, speed = $speed" }

        logger.debug {
            "Called calculateMileage function"
        }

        // ---------------- Preparation step: check if battery capacity and speed aren't 0 ---------------- //
        logger.debug { "Preparation step: check if battery capacity and speed aren't 0" }
        when {
            batteryCapacity == 0 -> {
                logger.debug { "Battery capacity is 0, returning 0" }
                return 0
            }
            speed == 0 -> {
                logger.debug { "Speed is 0, returning 0" }
                return 0
            }
        }
        logger.debug { "Battery capacity and speed aren't 0, proceeding" }

        // ---------------- Step 1/5: tweak end value with battery capacity ---------------- //
        logger.debug { "Step 1/5: tweak end value with battery capacity" }
        calculatedValue += calculateOffset(
            attribute = BATTERY_CAPACITY,
            rawValue = batteryCapacity,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 2/5: Apply rider weight ---------------- //
        logger.debug { "Step 2/5: Apply rider weight" }
        calculatedValue += calculateOffset(RIDER_WEIGHT, riderWeight, calculatedValue)

        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 3/5: Apply air temperature ---------------- //
        logger.debug { "Step 3/5: Apply air temperature" }
        calculatedValue += calculateOffset(
            attribute = AIR_TEMP,
            rawValue = airTemp,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 4/5: Apply battery cycles ---------------- //
        logger.debug { "Step 4/5: Apply battery cycles" }
        calculatedValue += calculateOffset(
            attribute = BATTERY_CYCLES,
            rawValue = batteryCycles,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 5/5: Apply speed ---------------- //
        logger.debug { "Step 5/5: Apply speed" }
        calculatedValue += calculateOffset(
            attribute = SPEED,
            rawValue = speed,
            currentCalculatedValue = calculatedValue
        )
        logger.debug {
            "Offset is ${
                calculateOffset(
                    attribute = SPEED,
                    rawValue = speed,
                    currentCalculatedValue = calculatedValue
                )
            }"
        }
        logger.debug { "Calculated value is $calculatedValue" }

        return calculatedValue
    }

    private fun calculateOffset(attribute: OffsetApplierAttribute, rawValue: Int, currentCalculatedValue: Int): Int {
        var calculatedValue = 0
        when (attribute) {
            RIDER_WEIGHT -> {
                calculatedValue = when {
                    rawValue > startRiderWeight -> {
                        if (rawValue - startRiderWeight > 12) {
                            var tmp = rawValue - startRiderWeight
                            var endValue = 0
                            while (tmp > 12) {
                                tmp -= 12
                                endValue += 7
                            }
                            (endValue + (((rawValue - startRiderWeight).toFloat() / 12 * 100) * 7 / 100)).toInt()
                        } else {
                            -abs((((rawValue - startRiderWeight).toFloat() / 12 * 100) * 7 / 100).toInt())
                        }
                    }
                    rawValue < startRiderWeight -> {
                        if (startRiderWeight - rawValue > 12) {
                            var tmp = startRiderWeight - rawValue
                            var endValue = 0
                            while (tmp > 12) {
                                tmp -= 12
                                endValue += 7
                            }
                            (endValue + (((startRiderWeight - rawValue).toFloat() / 12 * 100) * 7 / 100)).toInt()
                        } else {
                            -abs((((startRiderWeight - rawValue).toFloat() / 12 * 100) * 7 / 100).toInt())
                        }
                    }
                    else -> 0
                }
            }
            BATTERY_CAPACITY -> {
                calculatedValue = when {
                    // Mathematical actions for getting percentage of one number from another
                    rawValue < startBatteryCapacity -> {
                        logger.debug { -abs((currentCalculatedValue * (((startBatteryCapacity - rawValue).toFloat() / rawValue * 100) / 100)).toInt()) }
                        -abs((currentCalculatedValue * (((startBatteryCapacity - rawValue).toFloat() / rawValue * 100) / 100)).toInt())
                    }
                    rawValue > startBatteryCapacity -> {
                        (currentCalculatedValue * (((rawValue - startBatteryCapacity).toFloat() / rawValue * 100) / 100)).toInt()
                    }
                    else -> {
                        0
                    }
                }
            }
            AIR_TEMP -> {
                calculatedValue = when {
                    rawValue > startAirTemperature -> {
                        if (rawValue >= 36) {
                            @Suppress("UnusedUnaryOperator")
                            -abs(rawValue - 36)
                        } else {
                            rawValue - startAirTemperature
                        }
                    }
                    rawValue < startAirTemperature -> {
                        startAirTemperature - rawValue
                    }
                    else -> 0
                }
            }
            BATTERY_CYCLES -> {
                calculatedValue = when {
                    rawValue > startBatteryCycles -> {
                        var tmp = rawValue
                        var endValue = 0
                        while (tmp > 100) {

                            if (tmp <= 800 || tmp <= 500 || tmp <= 200) {
                                tmp -= 100
                                endValue += 2
                            }

                            if (tmp <= 700 || tmp <= 600 || tmp <= 400 || tmp <= 300) {
                                tmp -= 100
                                endValue += 3
                            }
                        }
                        -abs(endValue)
                    }
                    else -> 0
                }
            }
            SPEED -> {
                // TODO: Make implementation for speed
            }
        }
        return calculatedValue
    }
}
