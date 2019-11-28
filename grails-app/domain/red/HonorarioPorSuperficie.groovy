package red

import org.joda.money.Money

import java.math.RoundingMode

class HonorarioPorSuperficie extends Honorario {

    Money valorSuperficie

    static embedded  =  ['valorSuperficie']

    @Override
    Money calcular(Proyecto proyecto) {
        valorSuperficie.multipliedBy(proyecto.superficieAConstruir, RoundingMode.HALF_UP)
    }

    static constraints = {
        valorSuperficie nullable: false
    }
}
