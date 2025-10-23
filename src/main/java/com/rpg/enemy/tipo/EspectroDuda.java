package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;
import com.rpg.player.Jugador;

public class EspectroDuda extends Enemigo {

    private String nombreJugador;

    public EspectroDuda(String nombreJugador) {
        super("Espectro de la Duda", 100, 15,130);
        this.nombreJugador = nombreJugador;
    }

    @Override
    public void atacar(Jugador jugador) {
        System.out.println("👁️ El Espectro murmura con tu propia voz: '" + nombreJugador + "... nunca serás real.'");

        int tipo = (int) (Math.random() * 3);
        int dano;

        switch (tipo) {
            case 0 -> {
                dano = this.getAtaque();
                System.out.println("Te golpea con una réplica de tus propios movimientos. Daño: " + dano);
                jugador.recibirDaño(dano);
            }
            case 1 -> {
                dano = this.getAtaque() + 5;
                System.out.println("El Espectro canaliza tus miedos en un grito desgarrador. Daño: " + dano);
                jugador.recibirDaño(dano);
            }
            case 2 -> {
                System.out.println("El Espectro se desvanece un instante... y reaparece detrás de ti.");
                dano = this.getAtaque() - 3;
                jugador.recibirDaño(dano);
                this.setVida(this.getVida() + 10);
                System.out.println("El Espectro absorbe parte de tu energía. (+10 HP)");
            }
        }
    }
}
