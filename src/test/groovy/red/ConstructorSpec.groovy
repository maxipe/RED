package red

import grails.testing.gorm.DomainUnitTest
import org.joda.money.Money
import spock.lang.Specification

class ConstructorSpec extends Specification implements DomainUnitTest<Constructor> {

    Constructor constructor
    Proyecto proyecto

    def setup() {
        proyecto = new Proyecto()
        constructor = new Constructor(miembro: new Miembro(desarrolloInmobiliario: new DesarrolloInmobiliario(proyecto: proyecto)))
    }

    def cleanup() {
    }

    void "cuando la superficie es 10 m2 y el honorario ARS 5.500 por m2"() {
            proyecto.superficieAConstruir = 10
            constructor.honorario = new HonorarioPorSuperficie(valorSuperficie: Money.parse("ARS 5500"))
        expect:"el honorario es ARS 55.000"
            constructor.calcularHonorario() == Money.parse("ARS 55000")
    }

    void "cuando la superficie es 150,33 m2 y el honorario ARS 3.300,50 por m2"() {
        proyecto.superficieAConstruir = 150.33
        constructor.honorario = new HonorarioPorSuperficie(valorSuperficie: Money.parse("ARS 3300.50"))

        expect:"el honorario es ARS 496164,17"
        constructor.calcularHonorario() == Money.parse("ARS 496164.17")
    }
}
