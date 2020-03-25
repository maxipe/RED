package red

import Enums.RolTipo
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import red.invitaciones.Invitacion
import spock.lang.Specification

class InvitacionServiceSpec extends Specification
        implements ServiceUnitTest<InvitacionService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [Invitacion, DesarrolloInmobiliario, Persona, HonorarioPorCosto] as Class[]
    }

    def desarrolloInmobiliario
    def invitacion
    def rolTipo = RolTipo.DIRECTOR_DE_OBRA

    def setup() {
        desarrolloInmobiliario = new DesarrolloInmobiliario()
        invitacion = new Invitacion(desarrolloInmobiliario, new Persona(nombre: "Juan", apellido: "Perez", dni: "36321123"), rolTipo)
        invitacion.presupuestoHonorario = new HonorarioPorCosto(porcentaje: 4.5)
        invitacion.save(flush: true)
    }

    def cleanup() {
    }

    void "Aceptar presupuesto"() {

        when:
        service.aceptarPresupuesto(1)
        EquipoDeConstruccion equipoDeConstruccion = Invitacion.get(1).desarrolloInmobiliario.equipoDeConstruccion

        then:
        !equipoDeConstruccion.rolDisponible(rolTipo)
        equipoDeConstruccion.rolDisponible(RolTipo.CONSTRUCTOR)
        equipoDeConstruccion.rolDisponible(RolTipo.PROYECTISTA)
    }
}
