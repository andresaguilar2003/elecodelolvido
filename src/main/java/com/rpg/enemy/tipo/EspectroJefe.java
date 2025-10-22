package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;

public class EspectroJefe extends Enemigo {
    public EspectroJefe(String nombre, int salud, int ataque) {
        super(nombre, salud, ataque, 75); // 75 XP base para jefes
    }
    
    public EspectroJefe() {
        super("Espectro del Pasado - Jefe", 80, 12, 75);
    }
}