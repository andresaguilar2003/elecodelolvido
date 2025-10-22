package com.rpg.player;
import com.rpg.enemy.Enemigo;

public class EcoGuerrero extends Jugador {

    public EcoGuerrero(String nombre) {
        super(nombre);
        setFuerza(15);
        setDestreza(10);
        setInteligencia(5);
        setVoluntad(12);
    }

    // Habilidad especial del Guerrero
    public void golpeFeroz(Enemigo enemigo) {
        int danio = getFuerza() + 5; // ataque más potente
        System.out.println(getNombre() + " usa Golpe Feroz sobre " + enemigo.getNombre() + " causando " + danio + " de daño!");
        enemigo.recibirDaño(danio);
    }
}
