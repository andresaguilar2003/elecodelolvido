package com.rpg.capitulo2.facciones;

import com.rpg.player.Jugador;

public class DesbloqueoHabilidades {

    public void verificarDesbloqueo(Jugador jugador) {
        switch (jugador.getFaccion()) {
            case ACADEMIA -> desbloquearAcademia(jugador);
            case OLVIDADOS -> desbloquearOlvidados(jugador);
            default -> System.out.println("\n⚙️ No perteneces a ninguna facción, las habilidades superiores permanecen selladas.");
        }
    }

    private void desbloquearAcademia(Jugador jugador) {
        System.out.println("\n🔵 La Academia te concede acceso a la 'Luz de Eco'.");
        System.out.println("✨ Nueva habilidad: 'Resonancia Arcana' — aumenta el daño mágico un 25%.");
        jugador.aprenderHabilidad("Resonancia Arcana");
    }

    private void desbloquearOlvidados(Jugador jugador) {
        System.out.println("\n🔴 Los Olvidados te bendicen con el poder del Abismo.");
        System.out.println("🔥 Nueva habilidad: 'Sombra del Recuerdo' — tus ataques físicos drenan vida del enemigo.");
        jugador.aprenderHabilidad("Sombra del Recuerdo");
    }
}
