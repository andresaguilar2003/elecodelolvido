package com.rpg.world;

import com.rpg.player.Jugador;
import com.rpg.dialogo.Dialogo;
import com.rpg.dialogo.Dialogo.Choice;

import java.util.List;

public class Academia {
    public void entrar(Jugador jugador) {
        System.out.println("Entraste en la Academia del Eco. Un mentor te observa.");
        Dialogo d = new Dialogo("Mentor", "Has regresado... ¿Con qué intención sigues?", List.of(
            new Choice("Protejo la Academia", v -> {
                System.out.println("Has jurado proteger la Academia. Tu reputación mejora.");
                // aquí podrías cambiar atributos o reputación
            }),
            new Choice("Investigar a la Academia", v -> {
                System.out.println("Decides investigar... algo no cuadra.");
                // activar nueva misión o cambiar bandera
            }),
            new Choice("Abandonar la Academia", v -> {
                System.out.println("Te marchas. El mundo te observa con recelo.");
            })
        ));
        d.run();
    }
}
