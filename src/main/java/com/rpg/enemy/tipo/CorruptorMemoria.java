package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;
import com.rpg.player.Jugador;

public class CorruptorMemoria extends Enemigo {

    public CorruptorMemoria() {
        super("Corruptor de Memoria", 80, 12,150);
    }

    @Override
    public void atacar(Jugador jugador) {
        int tipoAtaque = (int) (Math.random() * 3);

        switch (tipoAtaque) {
            case 0 -> {
                System.out.println("🌀 El Corruptor proyecta recuerdos falsos. Tu mente duele.");
                int dano = this.getAtaque() + 3;
                jugador.recibirDaño(dano);
                System.out.println("Recibes " + dano + " de daño mental.");
            }
            case 1 -> {
                System.out.println("💫 El Corruptor drena tu fuerza vital.");
                int dano = this.getAtaque() - 2;
                jugador.recibirDaño(dano);
                this.setVida(this.getVida() + 5);
                System.out.println("El enemigo se cura 5 puntos de salud.");
            }
            case 2 -> {
                System.out.println("🔮 El Corruptor susurra mentiras: tu precisión disminuye temporalmente.");
                // Podrías aplicar un “estado” de confusión si agregamos ese sistema luego
                jugador.recibirDaño(this.getAtaque());
            }
        }
    }
}
