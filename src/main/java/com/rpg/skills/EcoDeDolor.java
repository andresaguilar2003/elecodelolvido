package com.rpg.skills;

import com.rpg.player.Jugador;
import com.rpg.enemy.Enemigo;

public class EcoDeDolor extends Habilidad {

    public EcoDeDolor() { super("Eco de Dolor"); }

    @Override
    public void usar(Jugador jugador, Enemigo enemigo) {
        int danio = jugador.getVoluntad() + 5;
        System.out.println(jugador.getNombre() + " usa " + getNombre() + " y causa " + danio + " de daño a " + enemigo.getNombre());
        enemigo.recibirDaño(danio);
    }
}
