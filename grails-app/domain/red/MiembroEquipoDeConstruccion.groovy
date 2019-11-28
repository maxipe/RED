package red

import org.joda.money.Money

class MiembroEquipoDeConstruccion extends Rol {

    Honorario honorario

    Money calcularHonorario() {
        honorario.calcular(miembro.desarrolloInmobiliario.proyecto)
    }

    static constraints = {
    }
}
