package com.rpg.game;

import com.rpg.player.Jugador;
import com.rpg.enemy.Enemigo;
import java.util.Scanner;

public class Batalla {
    private Scanner scanner = new Scanner(System.in);

    public void iniciarCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("¡Comienza el combate contra " + enemigo.getNombre() + "!\n");

        while(jugador.estaVivo() && enemigo.estaVivo()) {
            System.out.println("Tu salud: " + jugador.getSalud() + " | " + enemigo.getNombre() + " salud: " + enemigo.getSalud());
            System.out.println("Elige acción: [1] Atacar [2] Mostrar stats");
            String opcion = scanner.nextLine();

            if(opcion.equals("1")) {
                jugador.atacar(enemigo);
            } else if(opcion.equals("2")) {
                jugador.mostrarStats();
                continue;
            } else {
                System.out.println("Opción inválida.");
                continue;
            }

            if(enemigo.estaVivo()) {
                enemigo.atacar(jugador);
            }
        }

        if(jugador.estaVivo()) {
            System.out.println("\n¡Has derrotado a " + enemigo.getNombre() + "!");
        } else {
            System.out.println("\nHas sido derrotado...");
        }
    }
}
