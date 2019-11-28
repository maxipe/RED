package red

class EquipoDeConstruccion {

    DirectorDeObra directorDeObra
    Proyectista proyectista
    Constructor constructor

    static constraints = {
    }

    def obtenerMiembros() {
        Set miembros = []

        obtenerRoles().each { miembros.add(it.miembro) }

        miembros
    }

    def obtenerRoles() {
        [proyectista, directorDeObra, constructor].findAll()
    }

    def equipoCompleto() {
        obtenerRoles().size() == 3
    }
}
