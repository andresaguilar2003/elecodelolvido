package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;

public class CorruptorMemoria extends Enemigo {
    public CorruptorMemoria() {
        super("Corruptor de Memoria", 70, 12, 60);
    }

    @Override
    public void atacar(com.rpg.player.Jugador jugador) {
        System.out.println("💀 El Corruptor distorsiona tus recuerdos...");
        int daño = getAtaque() + 3;
        jugador.recibirDaño(daño);
        System.out.println("Tu mente se oscurece. Pierdes " + daño + " puntos de salud.");
    }
}
