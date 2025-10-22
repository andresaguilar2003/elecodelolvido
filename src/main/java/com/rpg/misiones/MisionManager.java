package com.rpg.misiones;

import com.rpg.player.Jugador;
import java.util.*;

public class MisionManager {

    private List<Mision> misionesActivas = new ArrayList<>();

    public void asignarMision(Mision mision) {
        misionesActivas.add(mision);
        mision.iniciar();
    }

    public void completarMision(String nombre, Jugador jugador) {
        for (Mision m : misionesActivas) {
            if (m.getNombre().equalsIgnoreCase(nombre) && m.getEstado() == Mision.Estado.EN_PROGRESO) {
                m.completar();

                jugador.ganarExperiencia(m.getRecompensaXP());
                if (m.esMoralBuena()) jugador.cambiarReputacion(+10);
                else jugador.cambiarReputacion(-10);

                System.out.println("🎁 Recompensa: +" + m.getRecompensaXP() + " XP");
                System.out.println("✨ Tu reputación ha cambiado según tus acciones.");
                return;
            }
        }
        System.out.println("⚠️ No se encontró una misión activa con ese nombre o ya fue completada.");
    }

    public void mostrarMisiones() {
        System.out.println("\n📜 Misiones actuales:");
        if (misionesActivas.isEmpty()) {
            System.out.println("No tienes misiones activas.");
        } else {
            for (Mision m : misionesActivas) {
                System.out.println("- " + m.getNombre() + " [" + m.getEstado() + "]");
            }
        }
    }
}
