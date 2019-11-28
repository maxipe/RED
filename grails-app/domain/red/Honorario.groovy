package red

import org.joda.money.Money

abstract class Honorario {

    abstract Money calcular(Proyecto proyecto)

    static constraints = {
    }

}
