package red

import Enums.RolTipo
import org.joda.money.Money

class MiembroEquipoDeConstruccion extends Rol {

    Honorario honorario
    final RolTipo rolTipo = RolTipo.MIEMBRO_EQUIPO_DE_CONSTRUCCION

    Money calcularHonorario() {
        honorario.calcular(miembro.desarrolloInmobiliario.proyecto)
    }

    static constraints = {
    }
}
