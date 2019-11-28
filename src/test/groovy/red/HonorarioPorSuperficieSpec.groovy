package red

import grails.testing.gorm.DomainUnitTest
import org.joda.money.Money
import spock.lang.Specification

class HonorarioPorSuperficieSpec extends Specification implements DomainUnitTest<HonorarioPorSuperficie> {

    def setup() {
    }

    def cleanup() {
    }

    void "cuando los metros a construir son 100 m2 y el honorario de ARS 30 por m2"() {
        HonorarioPorSuperficie honorario = new HonorarioPorSuperficie(valorSuperficie: Money.parse("ARS 30"))

        expect: "el honorario es ARS 3000"
        honorario.calcular(new Proyecto(superficieAConstruir: 100)) == Money.parse("ARS 3000")
    }
}
