package red

import Enums.RolTipo
import grails.gorm.transactions.Transactional
import org.joda.money.Money
import red.invitaciones.Invitacion

class PruebaController {

    def service = new DesarrolloInmobiliarioService()

    @Transactional
    def index() {
        new Persona(nombre: "Juan", apellido: "Perez", dni: "36321123").save(flush: true)
        render "Hello"
    }

    def index2() {
        BigDecimal superficie = 180
        def direccion = "Av Siempreviva 742"
        def nombre = "Casa nueva"

        service.crearProyecto(nombre, 1, superficie, direccion)
        render "Hello 2"
    }

    def index3() {
        def rolTipoConstructor = RolTipo.CONSTRUCTOR
        service.realizarInvitacion(1, 1, rolTipoConstructor)
        render "Hello 3"
    }

    def index4() {
        def honorarioPorSuperficie = new HonorarioPorSuperficie(valorSuperficie: Money.parse("ARS 1500"))
        service.presentarPresupuestoHonorario(1, honorarioPorSuperficie)
        render "Hello 4"
    }

    def index5() {
        assert DesarrolloInmobiliario.get(1).invitaciones.first().presupuestoHonorario != null
        assert Invitacion.get(1).presupuestoHonorario != null
        render "Hello 5"
    }
}
