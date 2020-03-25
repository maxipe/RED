package red

import Enums.RolTipo
import grails.gorm.transactions.Transactional
import red.invitaciones.Invitacion

@Transactional
class DesarrolloInmobiliarioService {

    def crearProyecto(String nombre, int personaId, BigDecimal superficieTerreno, String direccion) {
        def persona = Persona.get(personaId)
        def desarrolloInmobiliario = persona.crearDesarrolloInmobiliario(nombre)
        desarrolloInmobiliario.crearTerreno(direccion, superficieTerreno)

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
        def desarrolloInmobiliario = DesarrolloInmobiliario.find { it.invitaciones.any { { i -> i.id == invitacionId } } }

        Invitacion invitacion = desarrolloInmobiliario.invitaciones.find { i -> i.id == invitacionId }

        invitacion.presupuestoHonorario =  honorario
    }

}
