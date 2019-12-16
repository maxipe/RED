package Factories

import Enums.RolTipo
import red.Comitente
import red.Constructor
import red.DirectorDeObra
import red.MiembroEquipoDeConstruccion
import red.ProfesionalHabilitado
import red.Proyectista
import red.Rol

class RolFactory {

    private static Map equivalencias = [
            (RolTipo.COMITENTE): Comitente,
            (RolTipo.MIEMBRO_EQUIPO_DE_CONSTRUCCION): MiembroEquipoDeConstruccion,
            (RolTipo.PROFESIONAL_HABILITADO): ProfesionalHabilitado,
            (RolTipo.DIRECTOR_DE_OBRA): DirectorDeObra,
            (RolTipo.PROYECTISTA): Proyectista,
            (RolTipo.CONSTRUCTOR): Constructor
    ]

    static Rol obtenerRol(RolTipo rolTipo)
    {
        equivalencias[rolTipo]
    }
}
