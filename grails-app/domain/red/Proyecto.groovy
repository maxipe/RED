package red

import org.joda.money.Money

class Proyecto {

    BigDecimal superficieAConstruir
    Money costoTotal

    static embedded  =  ['costoTotal']

    static constraints = {
    }
}
