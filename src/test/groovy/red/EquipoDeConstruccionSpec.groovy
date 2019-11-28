package red

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class EquipoDeConstruccionSpec extends Specification implements DomainUnitTest<EquipoDeConstruccion> {

    EquipoDeConstruccion equipoDeConstruccion
    DirectorDeObra directorDeObra
    Proyectista proyectista
    Constructor constructor
    List<Miembro> miembros

    def setup() {
        equipoDeConstruccion = new EquipoDeConstruccion()

        directorDeObra = new DirectorDeObra()
        proyectista = new Proyectista()
        constructor = new Constructor()

        miembros = []
        3.times {miembros.add(new Miembro())}
    }

    def cleanup() {
    }


    void "obtener un solo miembro"() {

        when:
        directorDeObra.miembro = miembros.first()
        equipoDeConstruccion.directorDeObra = directorDeObra

        then:
        equipoDeConstruccion.obtenerMiembros().size() == 1
        equipoDeConstruccion.obtenerMiembros().contains(directorDeObra.miembro)
        equipoDeConstruccion.obtenerMiembros().contains(miembros.first())

    }

    void "obtener tres miembros"() {

        when:
        [directorDeObra, proyectista, constructor].eachWithIndex {it, i ->
            it.miembro = miembros.get(i)
        }

        equipoDeConstruccion.directorDeObra = directorDeObra
        equipoDeConstruccion.proyectista = proyectista
        equipoDeConstruccion.constructor = constructor

        then:
        equipoDeConstruccion.obtenerMiembros().size() == 3
        equipoDeConstruccion.obtenerMiembros().containsAll(miembros)
    }

    void "obtener 2 miembros por miembro con 2 roles"() {

        when:
        [directorDeObra, proyectista].eachWithIndex {it, i ->
            it.miembro = miembros.get(i)
        }
        constructor.miembro = miembros[0]

        equipoDeConstruccion.directorDeObra = directorDeObra
        equipoDeConstruccion.proyectista = proyectista
        equipoDeConstruccion.constructor = constructor

        then:
        equipoDeConstruccion.obtenerMiembros().size() == 2
        equipoDeConstruccion.obtenerMiembros().containsAll([miembros[0], miembros[1]])
    }

    void "el equipo esta incompleto"() {

        when:
        directorDeObra.miembro = miembros[0]
        equipoDeConstruccion.directorDeObra = directorDeObra

        then:
        !equipoDeConstruccion.equipoCompleto()
    }

    void "el equipo esta completo"() {

        when:
        [directorDeObra, proyectista, constructor].eachWithIndex {it, i ->
            it.miembro = miembros.get(i)
        }

        equipoDeConstruccion.directorDeObra = directorDeObra
        equipoDeConstruccion.proyectista = proyectista
        equipoDeConstruccion.constructor = constructor

        then:
        equipoDeConstruccion.equipoCompleto()
    }

    void "el equipo esta completo con miembro con 2 roles"() {

        when:
        [directorDeObra, proyectista].eachWithIndex {it, i ->
            it.miembro = miembros.get(i)
        }
        constructor.miembro = miembros[0]

        equipoDeConstruccion.directorDeObra = directorDeObra
        equipoDeConstruccion.proyectista = proyectista
        equipoDeConstruccion.constructor = constructor

        then:
        equipoDeConstruccion.equipoCompleto()
    }

}
