package euc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc

class EucUniversalPowerConsumptionTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL

    "Basic calculation" {
        val result = EVCalc.calculatePowerConsumption(81, 1554, 31, 100, 44, 49, device)

        result shouldBeGreaterThan 60
        result shouldBeLessThan 80
    }
})