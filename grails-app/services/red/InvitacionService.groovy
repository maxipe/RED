package red

import grails.gorm.transactions.Transactional
import red.invitaciones.Invitacion

@Transactional
class InvitacionService {

    def aceptarPresupuesto(int invitacionId) {
        Invitacion invitacion = Invitacion.get(invitacionId)

        if (!invitacion.listaParaAceptar()) {
            throw new IllegalStateException("No se puede aceptar la invitación.")
        }

        invitacion.desarrolloInmobiliario.aceptarPresupuestoDePotencialIntegrante(invitacion.potencialIntegrante)
        invitacion.aceptar()
    }
}
