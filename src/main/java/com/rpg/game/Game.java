package com.rpg.game;

import java.util.Scanner;
import java.util.Random;

import com.rpg.player.*;
import com.rpg.enemy.*;
import com.rpg.enemy.tipo.*;
import com.rpg.world.Zona;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private Jugador jugador;
    private Zona bosque = new Zona("Bosque de los Susurros");

    public void iniciar() {
        System.out.println("══════════════════════════════════════════════");
        System.out.println("           🌌 RÉPLICA: EL ECO DEL HÉROE 🌌");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("\nTe despiertas dentro de una cámara brillante...");
        System.out.println("Una voz te susurra: 'Tu eco aún no se ha formado... Elige tu reflejo.'\n");

        elegirClase();
        System.out.println("\nBienvenido, " + jugador.getNombre() + " — " + jugador.getClass().getSimpleName());
        System.out.println("Tu existencia es frágil... pero tu viaje comienza ahora.\n");

        primeraMision();
    }

    private void elegirClase() {
        System.out.println("Elige tu tipo de Eco:");
        System.out.println("1. Eco del Guerrero ⚔️");
        System.out.println("2. Eco del Pícaro 🗡️");
        System.out.println("3. Eco del Sabio 🔮");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        System.out.print("Elige un nombre para tu Réplica: ");
        String nombre = scanner.nextLine();

        switch (opcion) {
            case 1 -> jugador = new EcoGuerrero(nombre);
            case 2 -> jugador = new EcoPicaro(nombre);
            case 3 -> jugador = new EcoSabio(nombre);
            default -> {
                System.out.println("Opción no válida. Serás un Guerrero por defecto.");
                jugador = new EcoGuerrero(nombre);
            }
        }
    }

    private void primeraMision() {
        System.out.println("──────────────────────────────────────────────");
        System.out.println("🌀 Misión: Sincronízate con tus primeros ecos.");
        System.out.println("Dirígete al Bosque de los Susurros...");
        System.out.println("──────────────────────────────────────────────\n");

        boolean continuar = true;
        Random rand = new Random();

        while (continuar && jugador.estaVivo()) {
            System.out.println("Te adentras más en el bosque...");
            Enemigo enemigo = bosque.generarEnemigo();
            System.out.println("¡Un " + enemigo.getNombre() + " aparece!\n");

            Batalla batalla = new Batalla();
            batalla.iniciarCombate(jugador, enemigo);

            if (!jugador.estaVivo()) {
                System.out.println("\n💀 Tu eco se ha desvanecido...");
                continuar = false;
            } else {
                System.out.println("\nHas sobrevivido al combate. ¿Deseas continuar?");
                System.out.println("1. Seguir explorando");
                System.out.println("2. Volver a la Academia");

                int opcion = scanner.nextInt();
                if (opcion == 2) {
                    continuar = false;
                    System.out.println("\n🏛️ Regresas a la Academia... algo ha cambiado en ti.");
                }
            }
        }

        System.out.println("\n🌙 Fin de la versión demo: Capítulo 1 — El Despertar 🌙");
    }
}
