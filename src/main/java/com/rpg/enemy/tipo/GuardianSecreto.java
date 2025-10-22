package com.rpg.enemy.tipo;

import com.rpg.enemy.Enemigo;

public class GuardianSecreto extends Enemigo {
    public GuardianSecreto() {
        super("Guardián del Secreto", 120, 18, 150);
    }

    @Override
    public void recibirDaño(int dmg) {
        int mitigado = (int) (dmg * 0.7);
        super.recibirDaño(mitigado);
        System.out.println("🛡️ El Guardián canaliza energía antigua, reduciendo el daño recibido.");
    }
}
