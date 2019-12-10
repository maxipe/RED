package red

import grails.testing.gorm.DomainUnitTest
import org.joda.money.Money
import spock.lang.Specification

class ProfesionalHabilitadoSpec extends Specification implements DomainUnitTest<ProfesionalHabilitado> {

    ProfesionalHabilitado profesionalHabilitado
    Proyecto proyecto

    def setup() {
        proyecto = new Proyecto()
        profesionalHabilitado = new ProfesionalHabilitado(miembro: new Miembro(desarrolloInmobiliario: new DesarrolloInmobiliario(proyecto: proyecto)))
    }

    def cleanup() {
    }

    void "cuando el costo total del proyecto es ARS 1.000.000 y el honorario del 6%"() {
        proyecto.costoTotalEstimado = Money.parse("ARS 1000000")
        profesionalHabilitado.honorario = new HonorarioPorCosto(porcentaje: 0.06)

        expect: "el honorario es ARS 60.000"
        profesionalHabilitado.calcularHonorario() == Money.parse("ARS 60000")
    }

    void "cuando el costo total del proyecto es USD 100.000 y el honorario del 4,5%"() {
        proyecto.costoTotalEstimado = Money.parse("USD 100000")
        profesionalHabilitado.honorario = new HonorarioPorCosto(porcentaje: 0.045)

        expect: "el honorario es USD 4.500"
        profesionalHabilitado.calcularHonorario() == Money.parse("USD 4500")
    }
}
