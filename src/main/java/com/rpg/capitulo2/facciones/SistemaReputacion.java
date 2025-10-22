package com.rpg.capitulo2.facciones;

import com.rpg.player.Jugador;

public class SistemaReputacion {

    private static final int UMBRAL_ACADEMIA = -20;   // si la reputación cae por debajo → Los Olvidados
    private static final int UMBRAL_OLVIDADOS = 30;   // si la reputación sube mucho → Academia

    public void actualizarFaccion(Jugador jugador) {
        // De momento no cambia automáticamente.
        System.out.println("🏛️ Facción actual: " + jugador.getFaccion().getNombre());
    }

    public void mostrarEstado(Jugador jugador) {
        System.out.println("\n📜 Reputación actual: " + jugador.getReputacion());
        System.out.println("🏛️ Facción: " + jugador.getFaccion().getNombre());
    }
}
