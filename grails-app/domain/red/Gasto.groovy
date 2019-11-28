package red

import org.joda.money.Money

class Gasto {

    Miembro responsable
    Money dinero

    static embedded  =  ['dinero']

    static constraints = {
        dinero nullable: false
        responsable nullable: false
    }
}
