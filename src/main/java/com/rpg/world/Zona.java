package com.rpg.world;

import com.rpg.enemy.Enemigo;
import com.rpg.enemy.tipo.Espectro;
import com.rpg.enemy.tipo.EspectroJefe;
import com.rpg.items.Item;

import java.util.Random;

public class Zona {
    private String nombre;
    private Random rand = new Random();

    public Zona(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    public Enemigo generarEnemigo() {
        int n = rand.nextInt(100);
        if (n < 70) {
            return new Espectro("Espectro del Pasado", 40, 7);
        } else {
            return new EspectroJefe("Guardia Ilusorio", 80, 12);
        }
    }

    public Item posibleBotin() {
        int n = rand.nextInt(100);
        if (n < 50) return new Item("Pocion Pequena", true, 25, 0);
        if (n < 80) return new Item("Daga Replica", false, 0, 3);
        return null;
    }
}
