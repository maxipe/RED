package red

import Enums.RolTipo
import red.invitaciones.Invitacion
import red.invitaciones.PotencialIntegrante

import java.security.InvalidParameterException

class DesarrolloInmobiliario {

    Set<Invitacion> invitaciones

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

    static mapping = {
        comitente cascade: 'save-update'
    }

    static hasMany = [
            invitaciones: Invitacion
    ]

    static hasOne = [
            comitente: Comitente
    ]

    static constraints = {
        nombre blank: false
        comitente nullable: false
        equipoDeConstruccion nullable: false
        proyecto nullable: false
        terreno nullable: false
    }

    def obtenerMiembros() {
        Set<Miembro> miembros = []

        if (comitente)
            miembros.add(comitente.miembro)

        miembros.addAll(equipoDeConstruccion.obtenerMiembros())

        miembros
    }

    def asignarComitente(Persona persona) {
        Miembro miembro = asignarMiembro(persona)

        comitente = new Comitente(miembro: miembro)

        comitente.desarrolloInmobiliario = this
        miembro.agregarRol(comitente)
    }

    def aceptarPresupuestoDePotencialIntegrante(PotencialIntegrante potencialIntegrante) {
        Persona persona = potencialIntegrante.persona
        RolTipo rolTipo = potencialIntegrante.rolTipo

        if (!equipoDeConstruccion.rolDisponible(rolTipo))
            throw new InvalidParameterException("Ese rol ya está ocupado.")

        Miembro miembro = asignarMiembro(persona)

        def miembroEquipoDeConstruccion = equipoDeConstruccion.agregarRol(miembro, rolTipo)
        miembroEquipoDeConstruccion.miembro = miembro

        miembroEquipoDeConstruccion.desarrolloInmobiliario = this
        miembro.agregarRol(miembroEquipoDeConstruccion)
    }

    private Miembro asignarMiembro(Persona persona) {
        def miembro = obtenerMiembros().find({ m -> m.persona == persona }) ?: new Miembro(persona: persona, desarrolloInmobiliario: this)
        persona.agregarMembresia(miembro)
        miembro
    }

    def crearTerreno(String direccion, BigDecimal superficie) {
        if (terreno) throw new IllegalStateException("Ya existe un terreno.")

        terreno = new Terreno(direccion: direccion, superficie: superficie)
    }

    def estaIniciado() {
        terreno && comitente && equipoDeConstruccion && nombre
    }

    def invitar(Persona personaInvitada, RolTipo rolTipo) {
        if (invitaciones.any {Invitacion i -> i.mismaPersonaRol(personaInvitada, rolTipo)} )
            throw new IllegalArgumentException("Esa persona ya fue invitada a ese rol")

        def invitacion = new Invitacion(this, personaInvitada, rolTipo)
        invitaciones.add(invitacion)

        invitacion
    }
}
