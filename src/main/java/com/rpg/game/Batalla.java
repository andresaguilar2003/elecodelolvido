package com.rpg.game;

import java.util.Scanner;
import com.rpg.player.*;
import com.rpg.world.Zona;
import com.rpg.enemy.Enemigo;
import com.rpg.items.Item;

public class Batalla {
    private Scanner scanner = new Scanner(System.in);

    public void iniciarCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("⚔️ Comienza la batalla entre " + jugador.getNombre() + " y " + enemigo.getNombre() + "! ⚔️\n");

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            System.out.println("──────────────");
            System.out.println(jugador.getNombre() + " HP: " + jugador.getVida());
            System.out.println(enemigo.getNombre() + " HP: " + enemigo.getVida());
            System.out.println("──────────────");
            System.out.println("Elige una acción:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar habilidad especial");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1 -> jugador.atacar(enemigo);
                case 2 -> usarHabilidad(jugador, enemigo);
                default -> System.out.println("Acción no válida.");
            }

            if (enemigo.estaVivo()) {
                enemigo.atacar(jugador);
            }
            // dentro del bloque donde se detecta derrota del enemigo:
            if (!enemigo.estaVivo()) {
                System.out.println("\n✅ ¡Has derrotado a " + enemigo.getNombre() + "!");
                int xpGana = enemigo.getExperienciaBase(); // si Enemigo tiene nivel
                jugador.ganarExperiencia(xpGana);
                // botin
                Zona z = new Zona("temporal");
                Item botin = z.posibleBotin();
                if (botin != null) jugador.recogerItem(botin);
            }
        }
    }

    private void usarHabilidad(Jugador jugador, Enemigo enemigo) {
        if (jugador instanceof EcoGuerrero guerrero) {
            guerrero.golpeFeroz(enemigo);
        } else if (jugador instanceof EcoPicaro picaro) {
            picaro.ataqueSorpresa(enemigo);
        } else if (jugador instanceof EcoSabio sabio) {
            sabio.manipularRecuerdo(enemigo);
        } else {
            System.out.println("No tienes habilidades especiales disponibles.");
        }
    }
}
