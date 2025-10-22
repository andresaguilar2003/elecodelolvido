package com.rpg;

import com.rpg.player.Jugador;
import com.rpg.enemy.Enemigo;
import com.rpg.game.Batalla;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al RPG de texto!");
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        Jugador jugador = new Jugador(nombre);
        Enemigo enemigo = new Enemigo("Espectro del Pasado", 50, 8);

        Batalla batalla = new Batalla();
        batalla.iniciarCombate(jugador, enemigo);
    }
}
