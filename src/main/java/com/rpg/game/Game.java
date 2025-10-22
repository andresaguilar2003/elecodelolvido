package com.rpg.game;

import java.util.Scanner;
import java.util.Random;

import com.rpg.player.*;
import com.rpg.enemy.*;
import com.rpg.enemy.tipo.*;
import com.rpg.world.Zona;
import com.rpg.guardar.SaveManager;
import com.rpg.world.Academia;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private Jugador jugador;
    private Zona bosque = new Zona("Bosque de los Susurros");
    private SaveManager saveManager = new SaveManager();

    public void iniciar() {
        System.out.println("══════════════════════════════════════════════");
        System.out.println("           🌌 RÉPLICA: EL ECO DEL HÉROE 🌌");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("\nTe despiertas dentro de una cámara brillante...");
        System.out.println("Una voz te susurra: 'Tu eco aún no se ha formado... Elige tu reflejo.'\n");

        // Menú principal con opción de cargar partida
        System.out.println("1. Nueva Partida");
        System.out.println("2. Cargar Partida");
        int opcionInicial = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcionInicial == 2) {
            jugador = saveManager.cargar();
            if (jugador != null) {
                System.out.println("✅ Partida cargada correctamente.");
                System.out.println("Bienvenido de nuevo, " + jugador.getNombre() + " — " + jugador.getClass().getSimpleName());
            } else {
                System.out.println("❌ No se encontró partida guardada. Iniciando nueva partida...");
                elegirClase();
            }
        } else {
            elegirClase();
        }

        if (jugador != null) {
            System.out.println("\nBienvenido, " + jugador.getNombre() + " — " + jugador.getClass().getSimpleName());
            System.out.println("Tu existencia es frágil... pero tu viaje comienza ahora.\n");
            primeraMision();
        }
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
                // EN EL LOOP DE PRIMERA MISION, DESPUÉS DE COMBATE:
                System.out.println("\n══════════════════════════════════════════════");
                System.out.println("Opciones:");
                System.out.println("1. Seguir explorando");
                System.out.println("2. Volver a la Academia");
                System.out.println("3. Abrir inventario");
                System.out.println("4. Guardar partida");
                System.out.print("Elige una opción: ");
                
                int op = scanner.nextInt(); 
                scanner.nextLine(); // limpiar buffer
                
                if (op == 2) {
                    continuar = false;
                    System.out.println("\n🏛️ Regresas a la Academia... algo ha cambiado en ti.");
                    new Academia().entrar(jugador);
                } else if (op == 3) {
                    abrirInventario();
                } else if (op == 4) {
                    saveManager.guardar(jugador);
                    System.out.println("✅ Partida guardada correctamente.");
                } else if (op != 1) {
                    System.out.println("Opción no válida, continuando exploración...");
                }
            }
        }

        if (jugador.estaVivo()) {
            System.out.println("\n🌙 Fin de la versión demo: Capítulo 1 — El Despertar 🌙");
        }
    }

    private void abrirInventario() {
        System.out.println("\n🎒 INVENTARIO:");
        if (jugador.getInventario().isEmpty()) {
            System.out.println("Tu inventario está vacío.");
        } else {
            jugador.getInventario().forEach(it -> System.out.println("- " + it.getNombre()));
            
            System.out.print("\n¿Usar qué item? (escribe nombre o ENTER para cancelar): ");
            String itemNombre = scanner.nextLine();
            
            if (!itemNombre.isBlank()) {
                // SOLO LLAMAR AL MÉTODO SIN CAPTURAR RETORNO
                jugador.usarItem(itemNombre);
                // El método ya muestra mensajes internamente
            } else {
                System.out.println("Operación cancelada.");
            }
        }
        
        System.out.println("\nPresiona ENTER para continuar...");
        scanner.nextLine();
    }
}