package com.rpg.capitulo2.facciones;

public enum Faccion {
    ACADEMIA("Academia del Eco"),
    OLVIDADOS("Los Olvidados"),
    NEUTRAL("Sin afiliación");

    private final String nombre;

    Faccion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
