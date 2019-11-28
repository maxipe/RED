package red

class DesarrolloInmobiliario {

    String nombre
    Comitente comitente
    EquipoDeConstruccion equipoDeConstruccion
    Proyecto proyecto
    Terreno terreno


    static constraints = {
        nombre blank: false
        comitente nullable: false
        equipoDeConstruccion nullable: false
        proyecto nullable: false
        terreno nullable: false
    }
}
