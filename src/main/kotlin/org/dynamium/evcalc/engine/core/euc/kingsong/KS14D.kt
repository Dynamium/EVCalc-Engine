package org.dynamium.evcalc.engine.core.euc.kingsong

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

internal object KS14D {

    fun calculateMileage(riderWeight: Int, batteryCapacity: Int, airTemp: Int, batteryCycles: Int, speed: Int): Int {
        var calculatedValue = startMileage


        return calculatedValue
    }

    private fun calculateOffset(attribute: Attribute, rawValue: Int): Int {
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
