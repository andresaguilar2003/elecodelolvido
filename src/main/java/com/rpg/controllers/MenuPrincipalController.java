package com.rpg.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button; // Importar Button
import com.rpg.player.Jugador;

public class MenuPrincipalController {

    @FXML
    private Button nuevoJuegoButton; // Ejemplo: referencia a tu botón

    @FXML
    private void nuevoJuego() {
        try {
            Jugador jugador = new Jugador("Arion");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rpg/ui/dialogo.fxml"));
            Scene dialogoScene = new Scene(loader.load());

            // Pasar el jugador al controlador de diálogo
            com.rpg.controllers.DialogoLioraController controller = loader.getController();
            controller.setJugador(jugador);

            // OBTENER EL STAGE usando el botón actual
            Stage stage = (Stage) nuevoJuegoButton.getScene().getWindow();
            
            stage.setScene(dialogoScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cargarPartida() {
        System.out.println("Funcionalidad de carga pendiente...");
    }

    @FXML
    private void salirJuego() {
        System.exit(0);
    }
}