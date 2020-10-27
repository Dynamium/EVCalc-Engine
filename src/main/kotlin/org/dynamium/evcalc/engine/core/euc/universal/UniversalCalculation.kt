package org.dynamium.evcalc.engine.core.euc.universal

import mu.KotlinLogging
import org.dynamium.evcalc.engine.core.euc.Attribute

/*
 * EVCalc Universal Calculation
 *
 * This code calculates values that are universal for any unicycle.
 * For any documentation about inner workings of the universal calculation visit Wiki on GitHub.
 */

// Start constants
private const val startRiderWeight = 75
private const val startBatteryCapacity = 1555
private const val startAirTemperatureStart = 20
private const val startAirTemperatureEnd = 30
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

internal object UniversalCalculation {
    fun calculateMileage(
        riderWeight: Int,
        batteryCapacity: Int,
        airTemp: Int,
        batteryCycles: Int,
        speed: Int
    ): Int {
        var calculatedValue = startMileage

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
            attribute = Attribute.BATTERY_CAPACITY,
            rawValue = batteryCapacity,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 2/5: Apply rider weight ---------------- //
        logger.debug { "Step 2/5: Apply rider weight" }
        calculatedValue += calculateOffset(
            attribute = Attribute.RIDER_WEIGHT,
            rawValue = riderWeight,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 3/5: Apply air temperature ---------------- //
        logger.debug { "Step 3/5: Apply air temperature" }
        calculatedValue += calculateOffset(
            attribute = Attribute.AIR_TEMP,
            rawValue = airTemp,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 4/5: Apply battery cycles ---------------- //
        logger.debug { "Step 4/5: Apply battery cycles" }
        calculatedValue += calculateOffset(
            attribute = Attribute.BATTERY_CYCLES,
            rawValue = batteryCycles,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        // ---------------- Step 5/5: Apply speed ---------------- //
        logger.debug { "Step 5/5: Apply speed" }
        calculatedValue += calculateOffset(
            attribute = Attribute.SPEED,
            rawValue = speed,
            currentCalculatedValue = calculatedValue
        )
        logger.debug { "Calculated value is $calculatedValue" }

        return calculatedValue
    }

    private fun calculateOffset(attribute: Attribute, rawValue: Int, currentCalculatedValue: Int): Int {
        var calculatedValue = 0
        when (attribute) {
            Attribute.RIDER_WEIGHT -> {

            }
            Attribute.BATTERY_CAPACITY -> {
                calculatedValue = when {
                    rawValue < startBatteryCapacity -> {
                        val val1 = rawValue / currentCalculatedValue
                        val val2 = val1 * 100
                        val2
                    }
                    rawValue > startBatteryCapacity -> {
                        val val1 = rawValue / currentCalculatedValue
                        val val2 = val1 * 100
                        val2
                    }
                    else -> {
                        0
                    }
                }
            }
            Attribute.AIR_TEMP -> {

            }
            Attribute.BATTERY_CYCLES -> {

            }
            Attribute.SPEED -> {

            }
        }
        return calculatedValue
    }
}
