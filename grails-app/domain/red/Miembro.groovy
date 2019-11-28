package red

class Miembro {

    Persona persona
    DesarrolloInmobiliario desarrolloInmobiliario

    Set asignacionesDeGasto = []

    Set roles = []

    static hasMany = [
            asignacionesDeGasto: AsignacionDeGasto,
            roles: Rol
    ]

    static constraints = {
        persona nullable: false
        desarrolloInmobiliario nullable: false
    }
}
