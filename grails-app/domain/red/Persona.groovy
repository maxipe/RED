package red

class Persona {

    String nombre
    String apellido
    String dni

    Set membresias = []

    static hasMany = [
            membresias: Miembro
    ]

    DesarrolloInmobiliario crearDesarrolloInmobiliario(String nombre) {
        def desarrolloInmobiliario = new DesarrolloInmobiliario(nombre: nombre)

        desarrolloInmobiliario.asignarComitente(this)

        desarrolloInmobiliario
    }

    void agregarMembresia(Miembro miembro) {
        membresias.contains(miembro) ?: membresias.add(miembro)
    }

    static constraints = {
        nombre blank: false
        apellido blank: false
        dni blank: false, unique: true
    }
}
