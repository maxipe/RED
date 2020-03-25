package red

import Enums.RolTipo

class Rol {

    DesarrolloInmobiliario desarrolloInmobiliario
    final RolTipo rolTipo = null

    static hasOne = [miembro: Miembro]

    static constraints = {
    }

}
