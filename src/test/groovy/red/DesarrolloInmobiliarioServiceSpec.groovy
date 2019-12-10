package red

import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DesarrolloInmobiliarioServiceSpec extends Specification
        implements ServiceUnitTest<DesarrolloInmobiliarioService>, DomainUnitTest<Persona> {

    BigDecimal superficie = 180
    def direccion = "Av Siempreviva 742"
    def nombre = "Casa nueva"

    def setup() {
        new Persona(nombre: "Juan", apellido: "Perez", dni: "36321123").save(flush: true)
    }

    def cleanup() {
    }

    void "Inicio de proyecto"() {

        when: "cuando se ingresan los datos básicos del desarrollo inmobiliario"
        def desarrolloInmobiliario = service.crearProyecto(nombre, 1, superficie, direccion)

        then: "se da por iniciado el desarrollo"
        desarrolloInmobiliario.estaIniciado()
        desarrolloInmobiliario.terreno.superficie == superficie
        desarrolloInmobiliario.terreno.direccion == direccion
    }

    void "Proyecto sin iniciar por falta de terreno"() {

        when:
        def desarrolloInmobiliario = service.crearProyecto(nombre, 1, superficie, direccion)
        desarrolloInmobiliario.terreno = null

        then:
        !desarrolloInmobiliario.estaIniciado()
    }

    void "Actualizar datos terreno"() {

        when:
        service.crearProyecto(nombre, 1, superficie, direccion).save(flush: true)
        BigDecimal nuevaSuperficie = 181
        service.actualizarDatosTerreno(1, nuevaSuperficie, direccion).save(flush: true)
        def desarrolloInmobiliario = DesarrolloInmobiliario.get(1)

        then:
        desarrolloInmobiliario.terreno.superficie == nuevaSuperficie
        desarrolloInmobiliario.estaIniciado()

    }
}
