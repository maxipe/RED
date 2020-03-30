package red.invitaciones

import Enums.RolTipo
import red.DesarrolloInmobiliario
import red.Honorario
import red.Persona

import java.time.LocalDateTime

class Invitacion {

    private static final CantidadMaximaPresupuestos = 3

    LocalDateTime fechaHoraInvitacion
    DesarrolloInmobiliario desarrolloInmobiliario
    PotencialIntegrante potencialIntegrante
    Set<Honorario> presupuestosHonorarios

    boolean aceptada
    boolean rechazada
    boolean cerrada
    LocalDateTime fechaHoraCierre

    Invitacion(DesarrolloInmobiliario desarrolloInmobiliario, Persona personaInvitada, RolTipo rolTipo) {
        fechaHoraInvitacion = LocalDateTime.now()
        this.desarrolloInmobiliario = desarrolloInmobiliario
        potencialIntegrante = new PotencialIntegrante(personaInvitada, rolTipo)
        presupuestosHonorarios = new ArrayList<Honorario>()
        aceptada = false
        rechazada = false
        cerrada = false
    }

    def mismaPersonaRol(Persona persona, RolTipo rolTipo) {
        return potencialIntegrante.persona == persona && potencialIntegrante.rolTipo == rolTipo
    }

    def listaParaAceptar() {
        desarrolloInmobiliario && potencialIntegrante && presupuestosHonorarios.any() && estaAbierta()
    }

    def aceptar() {
        aceptada = true
        rechazada = false
        cerrar()
    }

    def rechazar() {
        aceptada = false
        rechazada = true
        if (presupuestosHonorarios.size() == CantidadMaximaPresupuestos)
            cerrar()
    }

    private def cerrar() {
        cerrada = true
        fechaHoraCierre = LocalDateTime.now()
    }

    def estaAbierta() {
        !cerrada
    }

    def estaAceptada() {
        cerrada && aceptada
    }

    def estaRechazada() {
        cerrada && !aceptada
    }

    static constraints = {
        fechaHoraInvitacion nullable: false
        fechaHoraCierre nullable: true
        desarrolloInmobiliario nullable: false
        potencialIntegrante nullable: false
    }

    static belongsTo = [desarrolloInmobiliario: DesarrolloInmobiliario]

    static hasMany = {
        presupuestosHonorarios: Honorario
    }

    def agregarPresupuestoHonorario(Honorario honorario) {
        if (aceptada)
            throw new IllegalStateException("No se puede agregar un presupuesto nuevo si ya se aceptó el anterior.")

        presupuestosHonorarios.add(honorario)
        rechazada = false
    }

    def obtenerPresupuestoHonorarioActual() {
        presupuestosHonorarios.max { it.fechaHoraCreacion }

    }

}
