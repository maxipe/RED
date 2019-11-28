package red

class EquipoDeConstruccion {

    DirectorDeObra directorDeObra
    Proyectista proyectista
    Constructor constructor

    static constraints = {
        directorDeObra nullable: false
        proyectista nullable: false
        constructor nullable: false
    }

    def obtenerMiembros() {
        Set miembros = []

        [proyectista, directorDeObra, constructor].findAll().each { miembros.add(it.miembro) }

        miembros
    }
}
