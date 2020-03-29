package red

import UserType.MoneyUserType
import org.joda.money.Money

import java.math.RoundingMode

class HonorarioPorSuperficie extends Honorario {

    Money valorSuperficie

    @Override
    Money calcular(Proyecto proyecto) {
        valorSuperficie.multipliedBy(proyecto.superficieAConstruir, RoundingMode.HALF_UP)
    }

    static embedded  =  ['valorSuperficie']

    static mapping = {
        valorSuperficie type: MoneyUserType, column: 'VALOR_SUPERFICIE'
    }

    static constraints = {
        valorSuperficie nullable: false
    }

}
