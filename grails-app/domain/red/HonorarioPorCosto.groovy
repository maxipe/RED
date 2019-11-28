package red

import org.joda.money.Money

import java.math.RoundingMode

class HonorarioPorCosto extends Honorario {

    BigDecimal porcentaje

    @Override
    Money calcular(Proyecto proyecto) {
        proyecto.costoTotal.multipliedBy(porcentaje, RoundingMode.HALF_UP)
    }

    static constraints = {
        porcentaje nullable: false
    }

}
