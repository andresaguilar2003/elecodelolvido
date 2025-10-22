package com.rpg.dialogo;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Dialogo {
    public static class Choice {
        public final String texto;
        public final Consumer<Void> accion;

        public Choice(String texto, Consumer<Void> accion) {
            this.texto = texto;
            this.accion = accion;
        }
    }

    private final String npc;
    private final String mensaje;
    private final List<Choice> opciones;

    public Dialogo(String npc, String mensaje, List<Choice> opciones) {
        this.npc = npc;
        this.mensaje = mensaje;
        this.opciones = opciones;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + npc + ": \"" + mensaje + "\"");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i).texto);
        }

        int eleccion = -1;
        while (eleccion < 1 || eleccion > opciones.size()) {
            System.out.print("> Elige una opción: ");
            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) { eleccion = -1; }
        }

        opciones.get(eleccion - 1).accion.accept(null);
    }
}
