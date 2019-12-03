package red

import UserType.MoneyUserType
import org.joda.money.Money

class Proyecto {

    BigDecimal superficieAConstruir
    Money costoTotal

    static embedded  =  ['costoTotal']

    static mapping = {
        costoTotal type: MoneyUserType, column: 'COSTO_TOTAL'
    }

    static constraints = {
    }
}
