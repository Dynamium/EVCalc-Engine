package euc

import executeAndMeasureTimeInMillis
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldNotBeLessThanOrEqual
import io.kotest.matchers.longs.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc

class EucUniversaMileageTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL
    "Returned value needs to pe positive" {
        val calculatedValue = EVCalc.calculateMileage(81, 1554, 31, 100, 44, device)
        calculatedValue shouldBeGreaterThan 0
    }

    "When battery capacity is 0, the result needs to be 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 0, 31, 100, 36, device)
        calculatedValue shouldBe 0
    }

    "When speed is 0, returned value must be 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 1800, 31, 100, 0, device)
        calculatedValue shouldBe 0
    }

    "When battery capacity is greater than 100, value needs to be greater than 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 101, 31, 100, 36, device)
        calculatedValue shouldNotBeLessThanOrEqual 0
    }

    "When start values are passed as the arguments, returned value needs to be 88"{
        val calculatedValue = EVCalc.calculateMileage(75, 1555, 25, 100, 35, device)
        calculatedValue shouldBe 88
    }

    "If all values are totally real, returned value should be normal too" {
        val calculatedValue = EVCalc.calculateMileage(60, 1600, 31, 100, 36, device)
        calculatedValue shouldBeGreaterThan 80
        calculatedValue shouldBeLessThan 90
    }

    "Calculation speed needs to be lower than 0.1".config(invocations = 5) {
        val (_, duration) = executeAndMeasureTimeInMillis {
            EVCalc.calculateMileage(60, 1556, 31, 400, 45, device)
        }
        duration shouldNotBeGreaterThan 100
        println("Execution duration is $duration")
    }
})