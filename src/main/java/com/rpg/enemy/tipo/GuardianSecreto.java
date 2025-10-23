package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;
import com.rpg.player.Jugador;

public class GuardianSecreto extends Enemigo {

    private boolean faseFinal = false;

    public GuardianSecreto() {
        super("Guardián del Secreto", 160, 20,300);
    }

    @Override
    public void atacar(Jugador jugador) {
        if (this.getVida() < 60 && !faseFinal) {
            faseFinal = true;
            System.out.println("⚡ El Guardián ruge y se cubre de runas ardientes: '¡LA VERDAD NO SERÁ REVELADA!'");
            this.setAtaque(this.getAtaque() + 10);
            return;
        }

        int tipo = (int) (Math.random() * 3);
        int dano;

        switch (tipo) {
            case 0 -> {
                dano = this.getAtaque();
                System.out.println("🔱 Golpe de Eco: una onda de energía impacta directamente en tu mente. Daño: " + dano);
                jugador.recibirDaño(dano);
            }
            case 1 -> {
                dano = this.getAtaque() + 8;
                System.out.println("🔥 El Guardián canaliza una runa del Abismo y lanza un torrente de fuego. Daño: " + dano);
                jugador.recibirDaño(dano);
            }
            case 2 -> {
                System.out.println("🛡️ El Guardián se cubre en una barrera etérea. Recupera 20 de vida.");
                this.setVida(this.getVida() + 20);
            }
        }
    }
}
