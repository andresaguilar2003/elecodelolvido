package com.rpg.skills;

import com.rpg.player.Jugador;
import com.rpg.enemy.Enemigo;

public abstract class Habilidad {
    private String nombre;

    public Habilidad(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    public abstract void usar(Jugador jugador, Enemigo enemigo);
}
