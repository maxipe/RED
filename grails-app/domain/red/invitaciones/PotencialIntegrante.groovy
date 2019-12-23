package red.invitaciones

import Enums.RolTipo
import red.Persona

class PotencialIntegrante {

    Persona persona
    RolTipo rolTipo

    PotencialIntegrante(Persona persona, RolTipo rolTipo) {
        this.persona = persona
        this.rolTipo = rolTipo
    }

    static constraints = {
        persona nullable: false
    }

    static belongsTo = [Invitacion]
}
