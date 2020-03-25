package red.invitaciones

import Enums.RolTipo
import red.DesarrolloInmobiliario
import red.Honorario
import red.Persona

import java.time.LocalDateTime

class Invitacion {

    LocalDateTime fechaHoraInvitacion
    DesarrolloInmobiliario desarrolloInmobiliario
    PotencialIntegrante potencialIntegrante
    Honorario presupuestoHonorario
    boolean aceptada
    boolean cerrada
    LocalDateTime fechaHoraCierre

    Invitacion(DesarrolloInmobiliario desarrolloInmobiliario, Persona personaInvitada, RolTipo rolTipo) {
        fechaHoraInvitacion = LocalDateTime.now()
        this.desarrolloInmobiliario = desarrolloInmobiliario
        this.potencialIntegrante = new PotencialIntegrante(personaInvitada, rolTipo)
    }

    def mismaPersonaRol(Persona persona, RolTipo rolTipo) {
        return potencialIntegrante.persona == persona && potencialIntegrante.rolTipo == rolTipo
    }

    def listaParaAceptar() {
        desarrolloInmobiliario && potencialIntegrante && presupuestoHonorario && estaAbierta()
    }

    def aceptar() {
        aceptada = true
        cerrar()
    }

    def rechazar() {
        aceptada = false
        cerrar()
    }

    private def cerrar() {
        cerrada = true
        fechaHoraCierre = LocalDateTime.now()
    }

    def estaAbierta() {
        !cerrada
    }

    def estaAceptada() {
        cerrada && aceptada
    }

    def estaRechazada() {
        cerrada && !aceptada
    }

    static constraints = {
        fechaHoraInvitacion nullable: false
        fechaHoraCierre nullable: true
        desarrolloInmobiliario nullable: false
        potencialIntegrante nullable: false
        presupuestoHonorario nullable: true
    }

    static belongsTo = [desarrolloInmobiliario: DesarrolloInmobiliario]

    static mapping = {
        presupuestoHonorario cascade: 'save-update'
    }
}
