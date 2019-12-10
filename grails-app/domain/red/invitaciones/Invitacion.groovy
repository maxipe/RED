package red.invitaciones

import red.DesarrolloInmobiliario

class Invitacion {

    DesarrolloInmobiliario desarrolloInmobiliario
    PotencialIntegrante potencialIntegrante


    static constraints = {
        desarrolloInmobiliario nullable: false
        potencialIntegrante nullable: false
    }
}
