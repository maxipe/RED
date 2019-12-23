package red

import Enums.RolTipo
import grails.gorm.transactions.Transactional
import org.joda.money.Money
import red.invitaciones.Invitacion

@Transactional
class DesarrolloInmobiliarioService {

    def crearProyecto(String nombre, int personaId, BigDecimal superficieTerreno, String dirreccion) {
        def persona = Persona.get(personaId)
        def desarrolloInmobiliario = persona.crearDesarrolloInmobiliario(nombre)
        desarrolloInmobiliario.crearTerreno(dirreccion, superficieTerreno)

        desarrolloInmobiliario.comitente.miembro.save()
        desarrolloInmobiliario.comitente.save()
        desarrolloInmobiliario.save()
    }

    def actualizarDatosTerreno(int desarrolloInmobiliarioId, BigDecimal superficieTerreno, String direccionTerreno) {
        def desarrolloInmobiliario = DesarrolloInmobiliario.get(desarrolloInmobiliarioId)
        desarrolloInmobiliario.terreno.with {
            superficie = superficieTerreno
            direccion = direccionTerreno
        }

        desarrolloInmobiliario
    }

    def realizarInvitacion(int desarrolloInmobiliarioId, int personaInvitadaId, RolTipo rolTipo) {
        def desarrolloInmobiliario = DesarrolloInmobiliario.get(desarrolloInmobiliarioId)
        def personaInvitada = Persona.get(personaInvitadaId)

        desarrolloInmobiliario.invitar(personaInvitada, rolTipo)
        desarrolloInmobiliario.save()
    }

    def presentarPresupuestoHonorario(int invitacionId, Honorario honorario) {
        def invitacion = Invitacion.get(invitacionId)

        invitacion.presupuestoHonorario = honorario
    }

}
