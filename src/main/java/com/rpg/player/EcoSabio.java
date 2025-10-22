package com.rpg.player;
import com.rpg.enemy.Enemigo;

public class EcoSabio extends Jugador {

    public EcoSabio(String nombre) {
        super(nombre);
        setFuerza(5);
        setDestreza(10);
        setInteligencia(15);
        setVoluntad(12);
    }

    // Habilidad especial del Sabio
    public void manipularRecuerdo(Enemigo enemigo) {
        int danio = getInteligencia() + 5;
        System.out.println(getNombre() + " usa Manipular Recuerdo sobre " + enemigo.getNombre() + " causando " + danio + " de daño!");
        enemigo.recibirDaño(danio);
    }
}
