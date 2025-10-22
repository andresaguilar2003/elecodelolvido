package com.rpg.capitulo2;

import com.rpg.player.Jugador;
import com.rpg.capitulo2.dialogos.DialogoLiora;
import com.rpg.capitulo2.misiones.MisionCap2;

public class Capitulo2 {

    private Jugador jugador;
    private MisionCap2 mision;

    public Capitulo2(Jugador jugador) {
        this.jugador = jugador;
        this.mision = new MisionCap2();
    }

    public void iniciar() {
        System.out.println("\n🌙 CAPÍTULO 2 — Los Ecos de la Traición 🌙");
        System.out.println("Regresas a la Academia del Eco...");
        System.out.println("Pero algo no está bien. El mentor Alaric ha desaparecido.\n");

        new DialogoLiora(jugador).iniciarConversacion();

        System.out.println("\n📜 Nueva misión: Investiga el paradero de Alaric.");
        mision.asignarMision(jugador);
    }
}
