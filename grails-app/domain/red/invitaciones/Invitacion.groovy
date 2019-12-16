package red.invitaciones

import Enums.RolTipo
import red.DesarrolloInmobiliario
import red.Persona

import java.time.LocalDateTime

class Invitacion {

    LocalDateTime fechaHoraInvitacion
    DesarrolloInmobiliario desarrolloInmobiliario
    PotencialIntegrante potencialIntegrante

    Invitacion(DesarrolloInmobiliario desarrolloInmobiliario, Persona personaInvitada, RolTipo rolTipo) {
        fechaHoraInvitacion = LocalDateTime.now()
        this.desarrolloInmobiliario = desarrolloInmobiliario
        this.potencialIntegrante = new PotencialIntegrante(personaInvitada, rolTipo)
    }

    def mismaPersonaRol(Persona persona, RolTipo rolTipo) {
        return potencialIntegrante.persona == persona && potencialIntegrante.rolTipo == rolTipo
    }

    static constraints = {
        fechaHoraInvitacion nullable: false
        desarrolloInmobiliario nullable: false
        potencialIntegrante nullable: false
    }
}
