package red

import Enums.RolTipo
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.security.InvalidParameterException

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

    void "al agregar un director de obra al equipo de construcción se agrega su rol"() {

        when:
        Miembro miembro = new Miembro()
        RolTipo rolTipo = RolTipo.DIRECTOR_DE_OBRA
        def rol = equipoDeConstruccion.agregarRol(miembro, rolTipo)

        then:
        equipoDeConstruccion.directorDeObra == rol
        !equipoDeConstruccion.rolDisponible(RolTipo.DIRECTOR_DE_OBRA)
        equipoDeConstruccion.rolDisponible(RolTipo.PROYECTISTA)
        equipoDeConstruccion.rolDisponible(RolTipo.CONSTRUCTOR)
    }

    void "al agregar un proyectista al equipo de construcción se agrega su rol"() {

        when:
        Miembro miembro = new Miembro()
        RolTipo rolTipo = RolTipo.PROYECTISTA
        def rol = equipoDeConstruccion.agregarRol(miembro, rolTipo)

        then:
        equipoDeConstruccion.proyectista == rol
        equipoDeConstruccion.rolDisponible(RolTipo.DIRECTOR_DE_OBRA)
        !equipoDeConstruccion.rolDisponible(RolTipo.PROYECTISTA)
        equipoDeConstruccion.rolDisponible(RolTipo.CONSTRUCTOR)
    }

    void "al agregar un constructor al equipo de construcción se agrega su rol"() {

        when:
        Miembro miembro = new Miembro()
        RolTipo rolTipo = RolTipo.CONSTRUCTOR
        def rol = equipoDeConstruccion.agregarRol(miembro, rolTipo)

        then:
        equipoDeConstruccion.constructor == rol
        equipoDeConstruccion.rolDisponible(RolTipo.DIRECTOR_DE_OBRA)
        equipoDeConstruccion.rolDisponible(RolTipo.PROYECTISTA)
        !equipoDeConstruccion.rolDisponible(RolTipo.CONSTRUCTOR)
    }


    void "al intentar agregar un comitente al equipo de construcción obtenemos un error"() {

        when:
        Miembro miembro = new Miembro()
        RolTipo rolTipo = RolTipo.COMITENTE
        equipoDeConstruccion.agregarRol(miembro, rolTipo)

        then:
        thrown(InvalidParameterException)
        equipoDeConstruccion.rolDisponible(RolTipo.DIRECTOR_DE_OBRA)
        equipoDeConstruccion.rolDisponible(RolTipo.PROYECTISTA)
        equipoDeConstruccion.rolDisponible(RolTipo.CONSTRUCTOR)
    }
}
