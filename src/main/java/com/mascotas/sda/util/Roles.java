package com.mascotas.sda.util;

import java.util.Arrays;
import java.util.List;

public enum Roles {
    ROL_ADMINISTRADOR(Arrays.asList(
        PermisosRol.READ_ALL_GRUPOS,
        PermisosRol.READ_ONE_GRUPOS,
        PermisosRol.CREATE_ONE_GRUPOS,
        PermisosRol.UPDATE_ONE_GRUPOS,
        PermisosRol.DISABLE_ONE_GRUPOS,

        PermisosRol.READ_ALL_ALUMNOS,
        PermisosRol.READ_ONE_ALUMNOS,
        PermisosRol.CREATE_ONE_ALUMNOS,
        PermisosRol.UPDATE_ONE_ALUMNOS,
        PermisosRol.DISABLE_ONE_ALUMNOS
    )), 
    ROLE_USUARIO(Arrays.asList(
        PermisosRol.READ_ALL_CONVOCATORIA,
        PermisosRol.READ_ONE_CONVOCATORIA,
        PermisosRol.CREATE_ONE_CONVOCATORIA,
        PermisosRol.UPDATE_ONE_CONVOCATORIA,
        PermisosRol.DISABLE_ONE_CONVOCATORIA
    ));

    private List<PermisosRol> permisos;

    Roles(List<PermisosRol> permisos) {
        this.permisos = permisos;
    }

    public void setPermisos(List<PermisosRol> permisos) {
        this.permisos = permisos;
    }
    
    public List<PermisosRol> getPermisos() {
        return permisos;
    }
}
