package red

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MiembroSpec extends Specification implements DomainUnitTest<Miembro> {

    Miembro miembro
    Rol rol

    def setup() {
        miembro = new Miembro()
        rol = new Comitente()
    }

    def cleanup() {
    }

    void "agregar un rol"() {

        when:
        miembro.agregarRol(rol)

        then:
        miembro.roles.contains(rol)
    }

    void "agregar un rol repetido"() {

        when:
        miembro.agregarRol(rol)
        miembro.agregarRol(rol)

        then:
        miembro.roles.size() == 1
    }

    void "agregar roles distintos"() {

        when:
        miembro.agregarRol(rol)
        miembro.agregarRol(new Constructor())

        then:
        miembro.roles.size() == 2
    }
}
