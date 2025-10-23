package com.rpg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import com.rpg.player.Jugador;
import com.rpg.capitulo2.dialogos.DialogoLiora;

public class DialogoLioraController {
    @FXML private TextArea textoDialogo;
    @FXML private Button opcion1;
    @FXML private Button opcion2;
    @FXML private Button opcion3;

    private Jugador jugador;
    private DialogoLiora dialogoLiora;

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        this.dialogoLiora = new DialogoLiora(jugador);
        iniciarDialogo();
    }

    private void iniciarDialogo() {
        textoDialogo.setText("Liora: 'Ah, " + jugador.getNombre() + "... has regresado a la Academia.'");
        opcion1.setText("Preguntar por Alaric");
        opcion2.setText("Preguntar por Los Olvidados");
        opcion3.setText("Guardar silencio");

        opcion1.setOnAction(e -> mostrarRespuesta("Alaric desapareció... la Academia guarda secretos."));
        opcion2.setOnAction(e -> mostrarRespuesta("Los Olvidados... son los que se rebelaron al Eco."));
        opcion3.setOnAction(e -> mostrarRespuesta("Liora te observa con desconfianza."));
    }

    private void mostrarRespuesta(String texto) {
        textoDialogo.setText(texto);
        jugador.ganarExperiencia(25);
    }
}
