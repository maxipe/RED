package red.invitaciones

import Enums.RolTipo
import grails.testing.gorm.DomainUnitTest
import red.DesarrolloInmobiliario
import red.HonorarioPorCosto
import red.Persona
import spock.lang.Specification

class InvitacionSpec extends Specification implements DomainUnitTest<Invitacion> {

    Invitacion invitacion

    def setup() {
        invitacion = new Invitacion(new DesarrolloInmobiliario(), new Persona(), RolTipo.DIRECTOR_DE_OBRA)
    }

    def cleanup() {
    }

    void "invitacion lista para aceptar"() {

        when:
        invitacion.presupuestoHonorario = new HonorarioPorCosto(porcentaje: 3.4)

        then:
        invitacion.listaParaAceptar()
        invitacion.estaAbierta()
        !invitacion.estaAceptada()
        !invitacion.estaRechazada()
    }

    void "invitacion no lista para aceptar"() {

        expect:
        !invitacion.listaParaAceptar()
        invitacion.estaAbierta()
        !invitacion.estaAceptada()
        !invitacion.estaRechazada()
    }


    void "invitacion aceptada"() {

        when:
        invitacion.presupuestoHonorario = new HonorarioPorCosto(porcentaje: 3.4)
        invitacion.aceptar()

        then:
        !invitacion.listaParaAceptar()
        !invitacion.estaAbierta()
        invitacion.estaAceptada()
        !invitacion.estaRechazada()
    }

    void "invitacion rechazada"() {

        when:
        invitacion.presupuestoHonorario = new HonorarioPorCosto(porcentaje: 3.4)
        invitacion.rechazar()

        then:
        !invitacion.listaParaAceptar()
        !invitacion.estaAbierta()
        !invitacion.estaAceptada()
        invitacion.estaRechazada()
    }
}
