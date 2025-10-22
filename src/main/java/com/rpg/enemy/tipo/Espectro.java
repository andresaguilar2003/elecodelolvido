package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;

public class Espectro extends Enemigo {
    public Espectro(String nombre, int salud, int ataque) {
        super(nombre, salud, ataque, 25); // 25 XP base para espectros normales
    }
    
    public Espectro() {
        super("Espectro del Pasado", 40, 7, 25);
    }
}