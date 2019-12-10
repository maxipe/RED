package red

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DesarrolloInmobiliarioSpec extends Specification implements DomainUnitTest<DesarrolloInmobiliario> {

    DesarrolloInmobiliario desarrolloInmobiliario
    def setup() {
        desarrolloInmobiliario = new DesarrolloInmobiliario()
    }

    def cleanup() {
    }

    void "crear terreno"() {

        when:
        desarrolloInmobiliario.crearTerreno("Av Siempreviva 742", 180)

        then:
        desarrolloInmobiliario.terreno
    }

    void "crear terreno en un desarrollo con terreno"() {

        when:
        desarrolloInmobiliario.crearTerreno("Av Siempreviva 742", 180)
        desarrolloInmobiliario.crearTerreno("Calle Falsa 123", 100)

        then:
        thrown(IllegalStateException)

    }
}
