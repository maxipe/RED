package red

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PersonaSpec extends Specification implements DomainUnitTest<Persona> {

    Persona persona
    Miembro miembro

    def setup() {
        persona = new Persona(nombre: "Juan", apellido: "Pérez", dni: 123)
        miembro = new Miembro(persona: persona)
    }

    def cleanup() {
    }

    void "agregar membresia"() {

        when:
        persona.agregarMembresia(miembro)

        then:
        persona.membresias.contains(miembro)
    }

    void "agregar membresia repetida"() {

        when:
        persona.agregarMembresia(miembro)
        persona.agregarMembresia(miembro)

        then: "sigue habiendo una sola membresia"
        persona.membresias.size() == 1
    }
}
