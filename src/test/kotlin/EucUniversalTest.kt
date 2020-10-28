import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc

class EucUniversalTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL
    "Returned value needs to pe positive" {
        val riderWeight = 76
        val batteryCapacity = 1556
        val calculatedValue = EVCalc.calculateMileage(76, 1556, 31, 100, 36, device)
        if (riderWeight < 150) {
            calculatedValue.shouldBeLessThan(150)
        }
        if (batteryCapacity > 1800) {
            calculatedValue.shouldBeGreaterThan(50)
        }
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
        val calculatedValue = EVCalc.calculateMileage(75, 100, 31, 100, 36, device)
        calculatedValue.shouldNotBe(0)
    }
})