package red

class Persona {

    String nombre
    String apellido
    String dni

    Set desarrollosInmobiliarios = []

    static hasMany = [
            desarrollosInmobiliarios: DesarrolloInmobiliario,
    ]


    static constraints = {
        nombre blank: false
        apellido blank: false
        dni blank: false, unique: true
    }
}
