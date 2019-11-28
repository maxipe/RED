package red

class AsignacionDeGasto {

    Gasto gasto

    boolean aceptada = false

    Miembro creador

    String descripcion

    static constraints = {
        gasto nullable: false
        creador nullable: false
        creador nullable: false
        descripcion blank: false
    }
}
