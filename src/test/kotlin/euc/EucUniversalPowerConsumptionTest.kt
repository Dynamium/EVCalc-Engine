package euc

import io.kotest.core.spec.style.StringSpec
import org.dynamium.evcalc.engine.api.DeviceModel
import org.dynamium.evcalc.engine.api.EVCalc

class EucUniversalPowerConsumptionTest : StringSpec({
    val device = DeviceModel.EUC_UNIVERSAL

    "Basic calculation" {
        println(EVCalc.calculatePowerConsumption(81, 1554, 31, 100, 44, 49, device))
    }
})