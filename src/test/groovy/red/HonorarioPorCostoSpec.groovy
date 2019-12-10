package red

import grails.testing.gorm.DomainUnitTest
import org.joda.money.Money
import spock.lang.Specification

class HonorarioPorCostoSpec extends Specification implements DomainUnitTest<HonorarioPorCosto> {

    def setup() {
    }

    def cleanup() {
    }

    void "cuando el costo total del proyecto es ARS 100 y el honorario del 3%"() {
        HonorarioPorCosto honorario = new HonorarioPorCosto(porcentaje: 0.03)

        expect: "el honorario es ARS 3"
        honorario.calcular(new Proyecto(costoTotalEstimado: Money.parse("ARS 100"))) == Money.parse("ARS 3")
    }

}
