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
    fun calculateMileage(riderWeight: Int, batteryCapacity: Int, airTemp: Int, batteryCycles: Int, speed: Int): Int {
        var calculatedValue = startMileage

        logger.debug {
            "Called calculateMileage function"
        }

        // ---------------- Step 1/5: tweak end value with battery capacity ---------------- //
        logger.debug { "Step 1/5: tweak end value with battery capacity" }
        calculatedValue = when {
            batteryCapacity > startBatteryCapacity -> { // If battery capacity is greater than the start value
                + calculateOffset(Attribute.BATTERY_CAPACITY, batteryCapacity, calculatedValue) // Subtract the offset
            }
            batteryCapacity < startBatteryCapacity -> { // If battery capacity is less than start value
                - calculateOffset(Attribute.BATTERY_CAPACITY, batteryCapacity, calculatedValue) // Add the offset to the end value
            }
            else -> calculatedValue
        }

        // ---------------- Step 2/5: Apply rider weight ---------------- //
        logger.debug { "Step 2/5: Apply rider weight" }
        calculatedValue = when {
            riderWeight > startRiderWeight -> { // If rider weight is greater than start value
                - calculateOffset(Attribute.RIDER_WEIGHT, riderWeight, calculatedValue) // Subtract the offset
            }
            riderWeight < startRiderWeight -> { // If rider weight is less than the start value
                + calculateOffset(Attribute.RIDER_WEIGHT, riderWeight, calculatedValue) // Add the offset
            }
            else -> calculatedValue
        }

        // ---------------- Step 3/5: Apply air temperature ---------------- //
        logger.debug { "Step 3/5: Apply air temperature" }
        calculatedValue = when {
            airTemp > startAirTemperatureEnd -> {
                - calculateOffset(Attribute.AIR_TEMP, airTemp, calculatedValue)
            }
            airTemp < startAirTemperatureStart -> {
                - calculateOffset(Attribute.AIR_TEMP, airTemp, calculatedValue)
            }
            else -> {
                calculatedValue
            }
        }

        // ---------------- Step 4/5: Apply battery cycles ---------------- //
        logger.debug { "Step 3/5: Apply battery cycles" }

        return calculatedValue
    }

    private fun calculateOffset(attribute: Attribute, rawValue: Int, currentCalculatedValue: Int): Int {
        var calculatedValue = 0
        when (attribute) {
            Attribute.RIDER_WEIGHT -> {

            }
            Attribute.BATTERY_CAPACITY -> {

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
