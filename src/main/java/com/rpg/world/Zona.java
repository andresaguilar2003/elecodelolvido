package com.rpg.world;

import com.rpg.enemy.Enemigo;
import com.rpg.enemy.tipo.EspectroJefe;
import com.rpg.enemy.Enemigo;
import java.util.Random;

public class Zona {
    private String nombre;

    public Zona(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    public Enemigo generarEnemigo() {
        Random rand = new Random();
        if(rand.nextBoolean()) {
            return new Enemigo("Espectro del Pasado", 50, 8);
        } else {
            return new EspectroJefe();
        }
    }
}
