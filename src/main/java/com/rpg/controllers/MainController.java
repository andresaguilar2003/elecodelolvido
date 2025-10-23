package com.rpg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import com.rpg.ui.AcademiaGUI;
import com.rpg.ui.GameGUI;

public class MainController {
    
    @FXML private TextArea textArea;
    @FXML private VBox buttonContainer;
    @FXML private Label statusLabel;
    @FXML private Label playerInfoLabel;
    
    private GameGUI gameGUI;
    
    @FXML
    public void initialize() {
        gameGUI = new GameGUI();
        actualizarInterfaz();
    }
    
    private void actualizarInterfaz() {
        textArea.setText(gameGUI.getMensajeActual());
        actualizarBotones();
        actualizarEstadoJugador();
    }
    
    // En MainController.java - actualiza el método actualizarBotones()
    private void actualizarBotones() {
        buttonContainer.getChildren().clear();
        
        switch (gameGUI.getEstadoActual()) {
            case MENU_PRINCIPAL:
                crearBoton("Nueva Partida", () -> gameGUI.nuevaPartida());
                crearBoton("Cargar Partida", () -> gameGUI.cargarPartida());
                break;
                
            case ELECCION_CLASE:
                mostrarSeleccionClase();
                break;
                
            case ACADEMIA_DIALOGO:
                mostrarDialogoAcademia();
                break;
                
            case EN_JUEGO:
                crearBoton("Buscar enemigos", () -> gameGUI.buscarEnemigo());
                crearBoton("Revisar misiones", () -> gameGUI.mostrarMisiones());
                crearBoton("Comprobar inventario", () -> gameGUI.mostrarInventario());
                crearBoton("Guardar partida", () -> {
                    gameGUI.guardarPartida();
                    actualizarInterfaz();
                });
                break;
                
            case BATALLA:
                crearBoton("Atacar", () -> gameGUI.atacarEnemigo());
                crearBoton("Huir", () -> gameGUI.volverAlJuego());
                break;
                
            case FIN_CAPITULO:
                crearBoton("Continuar al Capítulo 2", () -> gameGUI.continuarCapitulo2());
                crearBoton("Guardar partida", () -> {
                    gameGUI.guardarPartida();
                    actualizarInterfaz();
                });
                break;
                
            case MISIONES:
            case INVENTARIO:
                crearBoton("Volver al juego", () -> gameGUI.volverAlJuego());
                break;
        }
        
        crearBoton("Salir", () -> System.exit(0));
    }

    private void mostrarDialogoAcademia() {
        AcademiaGUI.DialogoAcademia dialogo = gameGUI.getDialogoActual();
        if (dialogo != null) {
            int index = 0;
            for (AcademiaGUI.OpcionDialogo opcion : dialogo.getOpciones()) {
                final int opcionIndex = index;
                crearBoton(opcion.getTexto(), () -> {
                    gameGUI.seleccionarOpcionDialogo(opcionIndex);
                    actualizarInterfaz();
                });
                index++;
            }
        }
    }
    
    private void crearBoton(String texto, Runnable accion) {
        Button button = new Button(texto);
        button.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
        button.setOnAction(e -> {
            accion.run();
            actualizarInterfaz();
        });
        buttonContainer.getChildren().add(button);
    }
    
    private void mostrarSeleccionClase() {
        buttonContainer.getChildren().clear();
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Crear Personaje");
        dialog.setHeaderText("Elige un nombre para tu Réplica");
        dialog.setContentText("Nombre:");
        
        dialog.showAndWait().ifPresent(nombre -> {
            if (nombre != null && !nombre.trim().isEmpty()) {
                crearBotonesClase(nombre.trim());
            }
        });
    }
    
    private void crearBotonesClase(String nombre) {
        buttonContainer.getChildren().clear();
        
        Label label = new Label("Elige tu tipo de Eco para " + nombre + ":");
        label.setStyle("-fx-text-fill: #e94560; -fx-font-size: 16px;");
        buttonContainer.getChildren().add(label);
        
        crearBoton("⚔️ Eco del Guerrero", () -> {
            gameGUI.elegirClase(nombre, 1);
            actualizarInterfaz();
        });
        
        crearBoton("🗡️ Eco del Pícaro", () -> {
            gameGUI.elegirClase(nombre, 2);
            actualizarInterfaz();
        });
        
        crearBoton("🔮 Eco del Sabio", () -> {
            gameGUI.elegirClase(nombre, 3);
            actualizarInterfaz();
        });
    }
    
    private void actualizarEstadoJugador() {
        if (gameGUI.getJugador() != null) {
            playerInfoLabel.setText("Jugador: " + gameGUI.getJugador().getNombre() + 
                                   " | Nivel: " + gameGUI.getJugador().getNivel() +
                                   " | HP: " + gameGUI.getJugador().getVida());
        } else {
            playerInfoLabel.setText("Jugador: -");
        }
        
        statusLabel.setText("Estado: " + gameGUI.getEstadoActual().toString());
    }
}