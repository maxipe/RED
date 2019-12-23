package red

import Enums.RolTipo
import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import org.joda.money.Money
import red.invitaciones.Invitacion
import spock.lang.Specification

class DesarrolloInmobiliarioServiceSpec extends Specification
        implements ServiceUnitTest<DesarrolloInmobiliarioService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [Persona, Honorario, HonorarioPorCosto, HonorarioPorSuperficie] as Class[]
    }

    BigDecimal superficie = 180
    def direccion = "Av Siempreviva 742"
    def nombre = "Casa nueva"
    def rolTipoProyectista = RolTipo.PROYECTISTA
    def rolTipoConstructor = RolTipo.CONSTRUCTOR
    def persona
    def honorarioPorSuperficie
    def honorarioPorCosto

    def setup() {
        persona = new Persona(nombre: "Juan", apellido: "Perez", dni: "36321123").save(flush: true)
        honorarioPorSuperficie = new HonorarioPorSuperficie(valorSuperficie: Money.parse("ARS 1500"))//.save(flush: true)
        honorarioPorCosto = new HonorarioPorCosto(porcentaje: 0.07)//.save(flush: true)
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
        service.crearProyecto(nombre, 1, superficie, direccion)
        BigDecimal nuevaSuperficie = 181
        service.actualizarDatosTerreno(1, nuevaSuperficie, direccion)
        def desarrolloInmobiliario = DesarrolloInmobiliario.get(1)

        then:
        desarrolloInmobiliario.terreno.superficie == nuevaSuperficie
        desarrolloInmobiliario.estaIniciado()

    }

    void "Invitar persona al equipo"() {

        when:
        service.crearProyecto(nombre, 1, superficie, direccion)
        service.realizarInvitacion(1, 1, rolTipoProyectista)

        then:
        DesarrolloInmobiliario.get(1).invitaciones.size() == 1
        DesarrolloInmobiliario.get(1).invitaciones.any { i -> i.mismaPersonaRol(persona, rolTipoProyectista) }
    }

    void "Invitado presenta presupuesto"() {

        when:
        service.crearProyecto(nombre, 1, superficie, direccion)
        service.realizarInvitacion(1, 1, rolTipoConstructor)
        service.presentarPresupuestoHonorario(1, honorarioPorSuperficie)

        then:
        DesarrolloInmobiliario.get(1).invitaciones.first().presupuestoHonorario == honorarioPorSuperficie
        Invitacion.get(1).presupuestoHonorario == honorarioPorSuperficie
    }

}
