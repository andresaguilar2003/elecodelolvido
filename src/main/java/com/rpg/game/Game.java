package com.rpg.game;

import java.util.Scanner;
import java.util.Random;

import com.rpg.player.*;
import com.rpg.enemy.*;
import com.rpg.enemy.tipo.*;
import com.rpg.world.Zona;
import com.rpg.guardar.SaveManager;
import com.rpg.world.Academia;
import com.rpg.misiones.MisionManager;
import com.rpg.capitulo2.Capitulo2;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private Jugador jugador;
    private Zona bosque = new Zona("Bosque de los Susurros");
    private SaveManager saveManager = new SaveManager();
    private MisionManager misionManager = new MisionManager();

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

    private void entrarAcademia() {
        Academia academia = new Academia();
        academia.entrar(jugador, misionManager); // otorga misión principal
    }

    private void primeraMision() {
        System.out.println("──────────────────────────────────────────────");
        System.out.println("🌀 Misión: Sincronízate con tus primeros ecos.");
        System.out.println("Dirígete al Bosque de los Susurros...");
        System.out.println("──────────────────────────────────────────────\n");

        boolean continuar = true;
        Random rand = new Random();

        entrarAcademia();

        while (continuar && jugador.estaVivo()) {
            System.out.println("\nTe adentras más en el bosque...");
            System.out.println("══════════════════════════════════════════════");
            System.out.println("1. Buscar enemigos");
            System.out.println("2. Revisar misiones");
            System.out.println("3. Comprobar inventario");
            System.out.println("4. Guardar partida");
            System.out.println("5. Salir del juego");
            System.out.print("Elige una opción: ");

            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> {
                    Enemigo enemigo;
                    // Probabilidad del Espectro Jefe
                    if (rand.nextInt(10) == 0) enemigo = new EspectroJefe();
                    else enemigo = bosque.generarEnemigo();

                    System.out.println("🌲 ¡Un " + enemigo.getNombre() + " aparece!\n");
                    Batalla batalla = new Batalla();
                    batalla.iniciarCombate(jugador, enemigo);

                    if (!enemigo.estaVivo() && enemigo instanceof EspectroJefe) {
                        System.out.println("\n💫 Has derrotado al Espectro Jefe.");
                        misionManager.completarMision("Eco del Bosque", jugador);
                        continuar = false;
                        if (jugador.estaVivo()) {
                                System.out.println("\n🌙 Fin del Capítulo 1 — 'El Eco Despierta' 🌙");
                            } else {
                                System.out.println("\n💀 Tu eco se desvanece en la eternidad...");
                                return;
                        }
                        menuFinCapitulo1();
                    }
                }
                case 2 -> misionManager.mostrarMisiones();
                case 3 -> abrirInventario();
                case 4 -> {
                    saveManager.guardar(jugador);
                    System.out.println("✅ Partida guardada correctamente.");
                }
                case 5 -> {
                    System.out.println("👋 Cerrando el juego...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void menuFinCapitulo1() {
        System.out.println("\n" + "⭐".repeat(50));
        System.out.println("           🌙 CAPÍTULO 1 COMPLETADO 🌙");
        System.out.println("⭐".repeat(50));
        
        System.out.println("\n💫 Tu existencia se estabiliza... por ahora.");
        System.out.println("📈 Has alcanzado el nivel " + jugador.getNivel());
        System.out.println("🎖️  Reputación: " + jugador.getReputacion());
        System.out.println("\n¿Qué deseas hacer ahora?");
        
        boolean enMenu = true;
        
        while (enMenu) {
            System.out.println("\n══════════════════════════════════════════════");
            System.out.println("1. 💾 Guardar partida");
            System.out.println("2. 🚀 Continuar al Capítulo 2");
            System.out.println("3. 📊 Ver estadísticas completas");
            System.out.println("4. 🚪 Salir del juego");
            System.out.print("Elige una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1 -> {
                    saveManager.guardar(jugador);
                    System.out.println("✅ Partida guardada correctamente.");
                    System.out.println("📁 Progreso del Capítulo 1 guardado.");
                }
                case 2 -> {
                    System.out.println("\n🎭 Iniciando Capítulo 2: 'Los Ecos de la Traición'...");
                    iniciarCapitulo2(jugador);
                    enMenu = false;
                }
                case 3 -> mostrarEstadisticasCompletas();
                case 4 -> {
                    System.out.println("👋 ¡Hasta pronto, " + jugador.getNombre() + "!");
                    enMenu = false;
                    System.exit(0);
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
    }

    private void mostrarEstadisticasCompletas() {
        System.out.println("\n📊 ESTADÍSTICAS DE " + jugador.getNombre().toUpperCase());
        System.out.println("══════════════════════════════════════════════");
        System.out.println("🏷️  Clase: " + jugador.getClass().getSimpleName());
        System.out.println("⭐ Nivel: " + jugador.getNivel());
        System.out.println("❤️  HP: " + jugador.getVida());
        System.out.println("🎖️  Reputación: " + jugador.getReputacion());
        System.out.println("💪 Fuerza: " + jugador.getFuerza());
        System.out.println("🎯 Destreza: " + jugador.getDestreza());
        System.out.println("📚 Inteligencia: " + jugador.getInteligencia());
        System.out.println("✨ Voluntad: " + jugador.getVoluntad());
        System.out.println("🎒 Items en inventario: " + jugador.getInventario().size());
    }


    private void iniciarCapitulo2(Jugador jugador) {
        // Delegar al manager del capítulo 2
        Capitulo2 capitulo2 = new Capitulo2(jugador);
        capitulo2.iniciar();
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