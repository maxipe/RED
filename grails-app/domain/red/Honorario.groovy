package red

import org.joda.money.Money

import java.time.LocalDateTime

abstract class Honorario {

    LocalDateTime fechaHoraCreacion

    abstract Money calcular(Proyecto proyecto)

    Honorario() {
        fechaHoraCreacion = LocalDateTime.now()
    }

    static constraints = {
    }

}
