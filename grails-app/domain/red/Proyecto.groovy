package red

import UserType.MoneyUserType
import org.joda.money.Money

class Proyecto {

    BigDecimal superficieAConstruir
    Money costoTotalEstimado

    static embedded  =  ['costoTotalEstimado']

    static mapping = {
        costoTotalEstimado type: MoneyUserType, column: 'COSTO_TOTAL_ESTIMADO'
    }

    static constraints = {
        superficieAConstruir nullable: true
        costoTotalEstimado nullable: true
    }
}
