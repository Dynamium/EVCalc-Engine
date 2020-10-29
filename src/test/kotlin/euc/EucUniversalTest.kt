package euc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldNotBeLessThan
import io.kotest.matchers.ints.shouldNotBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc
                                    
class EucUniversalTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL
    "Returned value needs to pe positive" {
        val calculatedValue = EVCalc.calculateMileage(76, 1600, 31, 100, 36, device)
        calculatedValue.shouldBeGreaterThan(-1)
    }

    "When battery capacity is 0, the result needs to be 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 0, 31, 100, 36, device)
        calculatedValue.shouldBe(0)
    }

    "When speed is 0, returned value must be 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 1800, 31, 100, 0, device)
        calculatedValue.shouldBe(0)
    }

    "When battery capacity is greater than 100, value needs to be greater than 0" {
        val calculatedValue = EVCalc.calculateMileage(75, 101, 31, 100, 36, device)
        calculatedValue.shouldNotBeLessThanOrEqual(0)
    }

    "When start values are passed as the arguments, returned value needs to be 88"{
        val calculatedValue = EVCalc.calculateMileage(75, 1555, 25, 100, 35, device)
        calculatedValue.shouldBe(88)
    }
})