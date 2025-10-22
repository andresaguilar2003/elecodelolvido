package com.rpg.capitulo2.misiones;

import com.rpg.player.Jugador;

public class MisionCap2 {
    private boolean completada = false;

    public void asignarMision(Jugador jugador) {
        System.out.println("🧭 Objetivo: Investigar el Abismo Olvidado y encontrar a Alaric.");
        jugador.cambiarReputacion(+10);
    }

    public void completar(Jugador jugador) {
        completada = true;
        System.out.println("✨ Misión completada: 'Los Ecos de la Traición'");
        jugador.ganarExperiencia(300);
        jugador.cambiarReputacion(+15);
    }

    public boolean isCompletada() { return completada; }
}
