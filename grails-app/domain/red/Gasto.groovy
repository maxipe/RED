package red

import UserType.MoneyUserType
import org.joda.money.Money

class Gasto {

    Miembro responsable
    Money dinero

    static embedded = ['dinero']

    static mapping = {
        dinero type: MoneyUserType, column: 'DINERO'
    }

    static constraints = {
        dinero nullable: false
        responsable nullable: false
    }
}
