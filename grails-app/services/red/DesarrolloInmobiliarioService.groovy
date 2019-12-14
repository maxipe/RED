package red

import grails.gorm.transactions.Transactional

@Transactional
class DesarrolloInmobiliarioService {

    def crearProyecto(String nombre, int personaId, BigDecimal superficieTerreno, String dirreccion) {
        def persona = Persona.get(personaId)
        def desarrolloInmobiliario = persona.crearDesarrolloInmobiliario(nombre)
        desarrolloInmobiliario.crearTerreno(dirreccion, superficieTerreno)

        desarrolloInmobiliario
    }

    def actualizarDatosTerreno(int desarrolloInmobiliarioId, BigDecimal superficieTerreno, String direccionTerreno) {
        def desarrolloInmobiliario = DesarrolloInmobiliario.get(desarrolloInmobiliarioId)
        desarrolloInmobiliario.terreno.with {
            superficie = superficieTerreno
            direccion = direccionTerreno
        }

        desarrolloInmobiliario
    }

}
