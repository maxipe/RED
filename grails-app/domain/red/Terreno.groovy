package red

import UserType.MoneyUserType
import org.joda.money.Money

class Terreno {

    String direccion
    BigDecimal superficie
    Money costo

    static embedded  =  ['costo']

    static mapping = {
        costo type: MoneyUserType, column: 'COSTO'
    }

    static constraints = {
        direccion blank: false
        costo nullable: true
    }

    static belongsTo = [DesarrolloInmobiliario]
}
