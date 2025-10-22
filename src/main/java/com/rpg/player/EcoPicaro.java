package com.rpg.player;
import com.rpg.enemy.Enemigo;

public class EcoPicaro extends Jugador {

    public EcoPicaro(String nombre) {
        super(nombre);
        setFuerza(8);
        setDestreza(15);
        setInteligencia(10);
        setVoluntad(10);
    }

    // Habilidad especial del Pícaro
    public void ataqueSorpresa(Enemigo enemigo) {
        int danio = getDestreza() + 5;
        System.out.println(getNombre() + " usa Ataque Sorpresa sobre " + enemigo.getNombre() + " causando " + danio + " de daño!");
        enemigo.recibirDaño(danio);
    }
}
