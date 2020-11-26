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
        logger.debug { "Calculated value is $calculatedValue" }

        return calculatedValue
    }

    private fun calculateOffset(attribute: OffsetApplierAttribute, rawValue: Int, currentCalculatedValue: Int): Int {
        var calculatedValue = 0
        when (attribute) {
            RIDER_WEIGHT -> {
                logger.debug { "Calculating rider weight" }
                calculatedValue = when {
                    rawValue > startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        logger.debug { "rawValue is $rawValue, startRiderWeight is $startRiderWeight" }
                        val val1 = getOffsetOfValues(rawValue, startRiderWeight) // Get the offset

                        logger.debug { "First offset is $val1" }
                        var tmpVal = val1

                        if (val1 > 12) while (tmpVal > 12) {
                            // Loop for making the offset less than 12
                            logger.debug { "Entered a loop" }
                            endValue += 7
                            logger.debug { "endValue is $endValue" }
                            tmpVal -= 12 // Add 7 km to returned value and subtract 12 kg from temporary variable
                            logger.debug { "tmpVal is $tmpVal" }
                        }

                        if (tmpVal == 0) 0 else { // If the temp variable is 0, return our result, but if not, continue the calculation
                            logger.debug { "tmpVal isn't 0, continuing" }
                            val val2 = CalculationTools.getPercentageOfOneValueFromAnother(tmpVal, 12) // Get percentage of temporary value from a constant
                            logger.debug { "val2 percentage value is $val2" }

                            val val3 = CalculationTools.getValueOfValueFromPercentage(7, val2) // Apply our percentage to get the end value
                            logger.debug { "val3 applied percentage is $val3" }

                            endValue += val3 // Add previous value to end variable

                            logger.debug { "end value is $endValue" }

                            logger.debug { "Inverted end value is " + -abs(endValue) }
                            -abs(endValue) // Return inverted number, so this offset will be subtracted from end value instead of added
                        }
                    }
                    rawValue < startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        val val1 = getOffsetOfValues(startRiderWeight, rawValue) // Get the offset
                        var tmpVal = val1

                        if (val1 > 12)
                            while (tmpVal > 12) {
                                // Loop for making the offset less than 12
                                endValue += 7
                                tmpVal -= 12 // Add 7 km to returned value and subtract 12 kg from temporary variable

                            }

                        if (tmpVal == 0) 0 else { // If the temp variable is 0, return our result, but if not, continue the calculation
                            val val2 = CalculationTools.getPercentageOfOneValueFromAnother(tmpVal, 12) // Get percentage of temporary value from a constant

                            val val3 = CalculationTools.getValueOfValueFromPercentage(7, val2) // Apply our percentage to get the end value
                            endValue += val3 // Add previous value to end variable

                            endValue // Return our result
                        }
                    }
                    else -> 0
                }
            }
            BATTERY_CAPACITY -> {
                calculatedValue = when {
                    // Mathematical actions for getting percentage of one number from another
                    rawValue < startBatteryCapacity -> {
                        logger.debug { "rawValue is $rawValue, currentCalculatedValue is $currentCalculatedValue" }

                        val val1 = rawValue * 100
                        logger.debug { val1 }
                        val val2 = val1 / startBatteryCapacity
                        logger.debug { val2 }
                        val val3 = 100 - val2
                        logger.debug { val3 }

                        val val4 = val3 * currentCalculatedValue
                        logger.debug { val4 }
                        val val5 = val4 / 100
                        logger.debug { val5 }
                        -abs(val5)
                    }
                    rawValue > startBatteryCapacity -> {
                        logger.debug { "rawValue is $rawValue, currentCalculatedValue is $currentCalculatedValue, startBatteryCapacity is $startBatteryCapacity" }

                        val val1 = rawValue * 100
                        logger.debug { "val1 = $val1" }
                        val val2 = val1 / startBatteryCapacity
                        logger.debug { "val2 = $val2" }
                        val val3 = val2 - 100
                        logger.debug { "val3 = $val3" }
                        val val4 = 100 - val3
                        logger.debug { "val4 = $val4" }

                        val val5 = val4 * currentCalculatedValue
                        logger.debug { "val5 = $val5" }
                        val val6 = val5 / 100
                        logger.debug { "val6 = $val6" }
                        val6
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
