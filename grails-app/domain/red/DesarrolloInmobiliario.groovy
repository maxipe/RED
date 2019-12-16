package red

import Enums.RolTipo
import red.invitaciones.Invitacion

class DesarrolloInmobiliario {

    Set invitaciones

    String nombre
    Comitente comitente
    EquipoDeConstruccion equipoDeConstruccion
    Proyecto proyecto
    Terreno terreno

    DesarrolloInmobiliario() {
        invitaciones = new HashSet()
        equipoDeConstruccion = new EquipoDeConstruccion()
        proyecto = new Proyecto()
    }

    static hasMany = [
            invitaciones: Invitacion
    ]

    static constraints = {
        nombre blank: false
        comitente nullable: false
        equipoDeConstruccion nullable: false
        proyecto nullable: false
        terreno nullable: false
    }

    def obtenerMiembros() {
        Set miembros = []

        if (comitente)
            miembros.add(comitente.miembro)

        miembros.addAll(equipoDeConstruccion.obtenerMiembros())

        miembros
    }

    def asignarComitente(Persona persona) {
        Miembro miembro = asignarMiembro(persona)

        comitente = new Comitente(miembro: miembro)

        miembro.agregarRol(comitente)
    }

    private Miembro asignarMiembro(Persona persona) {
        // TODO: Resolver duda: Sin list no puedo hacer m.persona en el find.. Porque?
        List<Miembro> miembros = obtenerMiembros().asList()

        def miembro = miembros.find({ m -> m.persona == persona }) ?: new Miembro(persona: persona, desarrolloInmobiliario: this)
        persona.agregarMembresia(miembro)
        miembro
    }

    def crearTerreno(String direccion, BigDecimal superficie) {
        if (terreno) throw new IllegalStateException("Ya existe un terreno.")

        terreno = new Terreno(direccion: direccion, superficie: superficie)
    }

    void estaIniciado() {
        terreno && comitente && equipoDeConstruccion && nombre
    }

    def invitar(Persona personaInvitada, RolTipo rolTipo) {
        if (invitaciones.any {Invitacion i -> i.mismaPersonaRol(personaInvitada, rolTipo)} )
            throw new IllegalArgumentException("Esa persona ya fue invitada a ese rol")

        invitaciones.add(new Invitacion(this, personaInvitada, rolTipo))
    }
}
