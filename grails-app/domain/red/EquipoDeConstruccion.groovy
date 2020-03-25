package red

import Enums.RolTipo
import Factories.RolFactory
import red.invitaciones.PotencialIntegrante

import java.security.InvalidParameterException

class EquipoDeConstruccion {

    DirectorDeObra directorDeObra
    Proyectista proyectista
    Constructor constructor

    static constraints = {
        directorDeObra nullable: true
        proyectista nullable: true
        constructor nullable: true
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

    boolean rolDisponible(RolTipo rolTipo) {
        rolTipo == RolTipo.DIRECTOR_DE_OBRA ?
                !directorDeObra :
        rolTipo == RolTipo.PROYECTISTA ?
                !proyectista :
        rolTipo == RolTipo.CONSTRUCTOR ?
                !constructor :
        false
    }

    MiembroEquipoDeConstruccion agregarRol(Miembro miembro, RolTipo rolTipo) {
        def rol = RolFactory.obtenerRol(rolTipo)
        miembro.agregarRol(rol)

        asignarRol(rol)

        rol as MiembroEquipoDeConstruccion
    }

    private asignarRol(DirectorDeObra directorDeObra) {
        this.directorDeObra = directorDeObra
    }

    private asignarRol(Proyectista proyectista) {
        this.proyectista = proyectista
    }

    private asignarRol(Constructor constructor) {
        this.constructor = constructor
    }

    private static asignarRol(rol) {
        throw new InvalidParameterException("Ese no es un rol válido para un equipo de construcción.")
    }

}
