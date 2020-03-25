package red

import Enums.RolTipo
import grails.testing.gorm.DomainUnitTest
import red.invitaciones.PotencialIntegrante
import spock.lang.Specification

class DesarrolloInmobiliarioSpec extends Specification implements DomainUnitTest<DesarrolloInmobiliario> {

    DesarrolloInmobiliario desarrolloInmobiliario
    PotencialIntegrante potencialIntegrante

    def setup() {
        desarrolloInmobiliario = new DesarrolloInmobiliario()
        potencialIntegrante = new PotencialIntegrante(persona: new Persona(), rolTipo: RolTipo.DIRECTOR_DE_OBRA)
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

    void "asignar un miembro al equipo de construccion"() {

        when:
        desarrolloInmobiliario.aceptarPresupuestoDePotencialIntegrante(potencialIntegrante)

        then:
        desarrolloInmobiliario.equipoDeConstruccion.directorDeObra.miembro.persona == potencialIntegrante.persona
    }
}
