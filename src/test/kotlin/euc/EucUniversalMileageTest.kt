package euc

import executeAndMeasureTimeInMillis
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.ints.shouldNotBeLessThanOrEqual
import io.kotest.matchers.longs.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import org.dynamium.evcalc.engine.api.calculation.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc
import org.dynamium.evcalc.engine.api.calculation.mileage.calculateMileage

class EucUniversalMileageTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL
    "Returned value needs to pe positive" {
        val calculatedValue = EVCalc.calculateMileage(device, 81, 1554, 31, 100, 44)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBeGreaterThan 0
    }

    "When battery capacity is 0, the result needs to be 0" {
        val calculatedValue = EVCalc.calculateMileage(device, 75, 0, 31, 100, 36)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBe 0
    }

    "When speed is 0, returned value must be 0" {
        val calculatedValue = EVCalc.calculateMileage(device, 75, 1800, 31, 100, 0)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBe 0
    }

    "When battery capacity is greater than 100, value needs to be greater than 0" {
        val calculatedValue = EVCalc.calculateMileage(device, 75, 101, 31, 100, 36)

        println("Returned value is $calculatedValue")
        calculatedValue shouldNotBeLessThanOrEqual 0
    }

    "When start values are passed as the arguments, returned value needs to be 88"{
        val calculatedValue = EVCalc.calculateMileage(device, 75, 1555, 25, 100, 35)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBe 88
    }

    "If all values are totally real, returned value should be normal too" {
        val calculatedValue = EVCalc.calculateMileage(device, 75, 1554, 31, 100, 36)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBeGreaterThan 80
        calculatedValue shouldBeLessThan 95
    }

    "Battery percentage needs to actually work" {
        val calculatedValue = EVCalc.calculateMileage(device, 75, 1554, 31, 100, 36, 64)

        println("Returned value is $calculatedValue")
        calculatedValue shouldBeGreaterThan 30
    }

    "Calculation speed needs to be lower than 0.1".config(invocations = 5) {
        val (_, duration) = executeAndMeasureTimeInMillis {
            EVCalc.calculateMileage(device, 60, 1556, 31, 400, 45)
        }
        duration shouldNotBeGreaterThan 100
        println("Execution duration is $duration")
    }
})