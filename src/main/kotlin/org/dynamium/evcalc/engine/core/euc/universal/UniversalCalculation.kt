package org.dynamium.evcalc.engine.core.euc.universal

import mu.KotlinLogging
import org.dynamium.evcalc.engine.api.EucRideStyle
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
private val startAirTemperature = listOf(20, 30)
private val startAirTemperatureStart = startAirTemperature[0]
private val startAirTemperatureEnd = startAirTemperature[1]
private const val startBatteryCycles = 100
private const val startSpeed = 35
private const val startMileage = 88

// Multipliers
private const val riderWeightOffsetMultiplier = 2
private const val batteryCapacityOffsetMultiplier = 15
private const val airTempOffset = 1
private const val batteryCyclesOffset = 100
private const val speedOffset = 1

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
        calculatedValue.applyOffset(RIDER_WEIGHT, calculatedValue)

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

    @Deprecated(
        level = DeprecationLevel.WARNING,
        message = "Use extension function .applyOffset instead.",
        replaceWith = ReplaceWith(expression = ".applyOffset")
    )
    private fun calculateOffset(attribute: OffsetApplierAttribute, rawValue: Int, currentCalculatedValue: Int): Int {
        var calculatedValue = 0
        when (attribute) {
            RIDER_WEIGHT -> {
                calculatedValue = when {
                    rawValue > startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        val val1 = rawValue - startRiderWeight // Get the offset
                        var calculatedOffset = val1 // Helper variable

                        if (val1 > 12) { // If offset is greater than 12

                            while (calculatedOffset > 12) { // Loop for making the offset less than 12

                                endValue += 7 // Add 7 km to returned value
                                calculatedOffset -= 12 // Subtract 12 kg from helper variable
                            }
                        }

                        if (calculatedOffset == 0) {
                            endValue // If the helper variable is 0, return our result
                        } else { // But if not, continue the calculation
                            val val2 = calculatedOffset / 12 * 100 // Get percentage of one value from another

                            val val3 = 7 * val2 / 100 // Apply our percentage to get the end value

                            endValue = -abs(val3) // Convert our number to negative, so the end value of the whole calculation will be subtracted instead of added

                            endValue // Return our final result
                        }
                    }
                    rawValue < startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        val val1 = startRiderWeight - rawValue // Get the offset
                        var calculatedOffset = val1 // Helper variable

                        if (val1 > 12) { // If offset is greater than 12

                            while (calculatedOffset > 12) { // Loop for making the offset less than 12

                                endValue += 7 // Add 7 km to returned value
                                calculatedOffset -= 12 // Subtract 12 kg from helper variable
                            }
                        }

                        if (calculatedOffset == 0) {
                            endValue // If the helper variable is 0, return our result
                        } else { // But if not, continue the calculation
                            val val2 = calculatedOffset / 12 * 100 // Get percentage of one value from another

                            val val3 = 7 * val2 / 100 // Apply our percentage to get the end value

                            endValue = val3 // Assign our result to the result variable

                            endValue // Return our final result
                        }
                    }
                    else -> {
                        0
                    }
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
            }
            BATTERY_CYCLES -> {
            }
            SPEED -> {
            }
        }
        return calculatedValue
    }


    /**
     * An extension function for applying offsets.
     *
     * @param attribute Indicates what offset you want to calculate. Uses OffsetApplierAttribute enum class to indicate that.
     * @param currentCalculatedValue Specifies current calculated value, obviously.
     */
    private fun Int.applyOffset(attribute: OffsetApplierAttribute, currentCalculatedValue: Int) {
        val calculatedValue: Int
        when (attribute) {
            BATTERY_CAPACITY -> TODO()
            RIDER_WEIGHT -> {
                calculatedValue = when {
                    this > startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        val val1 = getOffsetOfValues(startRiderWeight, this) // Get the offset
                        var tmpVal = val1

                        if (val1 > 12)
                            while (tmpVal > 12)  // Loop for making the offset less than 12
                                endValue += 7; tmpVal -= 12 // Add 7 km to returned value and subtract 12 kg from temporary variable

                        if (tmpVal == 0) 0 else { // If the temp variable is 0, return our result, but if not, continue the calculation
                            val val2 = CalculationTools.getPercentageOfOneValueFromAnother(tmpVal, 12) // Get percentage of temporary value from a constant

                            val val3 = CalculationTools.getValueOfValueFromPercentage(7, val2) // Apply our percentage to get the end value
                            endValue += val3 // Add previous value to end variable

                            -abs(endValue) // Return inverted number, so this offset will be subtracted from end value instead of added
                        }
                    }
                    this < startRiderWeight -> {
                        var endValue = 0 // Create our end value

                        val val1 = getOffsetOfValues(this, startRiderWeight) // Get the offset
                        var tmpVal = val1

                        if (val1 > 12)
                            while (tmpVal > 12)  // Loop for making the offset less than 12
                                endValue += 7; tmpVal -= 12 // Add 7 km to returned value and subtract 12 kg from temporary variable

                        if (tmpVal == 0) 0 else { // If the temp variable is 0, return our result, but if not, continue the calculation
                            val val2 = CalculationTools.getPercentageOfOneValueFromAnother(tmpVal, 12) // Get percentage of temporary value from a constant

                            val val3 = CalculationTools.getValueOfValueFromPercentage(7, val2) // Apply our percentage to get the end value
                            endValue += val3 // Add previous value to end variable

                            -abs(endValue) // Return inverted number, so this offset will be subtracted from end value instead of added
                        }
                    }
                    else -> 0
                }
                this + calculatedValue // Apply our results to the end value
            }
            AIR_TEMP -> TODO()
            BATTERY_CYCLES -> TODO()
            SPEED -> TODO()
        }
    }
}
