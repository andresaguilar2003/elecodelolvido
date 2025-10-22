package com.rpg.world;

import com.rpg.dialogo.Dialogo;
import com.rpg.dialogo.Dialogo.Choice;
import com.rpg.player.Jugador;
import java.util.List;

public class Academia {
    public void entrar(Jugador jugador) {
        System.out.println("\n🏛️ Entras en la Academia del Eco. El aire vibra con ecos antiguos...");
        Dialogo d = new Dialogo("Mentor", 
            "Has vuelto, " + jugador.getNombre() + ". ¿Qué propósito guía tu reflejo?",
            List.of(
                new Choice("🕊️ Busco proteger la armonía de este mundo.", v -> {
                    System.out.println("El mentor asiente: 'Tu eco brilla con pureza.'");
                    jugador.cambiarReputacion(+25);
                }),
                new Choice("⚔️ Haré lo que deba, aunque el mundo arda.", v -> {
                    System.out.println("El mentor te observa con temor... tu sombra crece.");
                    jugador.cambiarReputacion(-25);
                }),
                new Choice("💭 No lo sé... aún me busco a mí mismo.", v -> {
                    System.out.println("El mentor sonríe con comprensión. 'Esa duda te hará fuerte.'");
                    jugador.cambiarReputacion(0);
                })
            )
        );
        d.iniciar();

        System.out.println("\nTu reputación actual: " + jugador.getReputacion());
        System.out.println("(Luminoso > 50 | Neutral -50–50 | Oscuro < -50)");
        System.out.println("\nPresiona ENTER para volver al Bosque...");
        new java.util.Scanner(System.in).nextLine();
    }
}
