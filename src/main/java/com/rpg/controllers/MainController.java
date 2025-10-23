package com.rpg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.rpg.ui.AcademiaGUI;
import com.rpg.ui.GameGUI;
import com.rpg.ui.TextAnimator;

public class MainController {
    
    @FXML private TextArea textArea;
    @FXML private VBox buttonContainer;
    @FXML private Label statusLabel;
    @FXML private Label playerInfoLabel;
    @FXML private StackPane imageContainer;
    
    private GameGUI gameGUI;
    private TextAnimator textAnimator;
    private ImageView characterImageView;
    
    @FXML
    public void initialize() {
        gameGUI = new GameGUI();
        textAnimator = new TextAnimator(textArea);
        
        // Configurar ImageView para personajes
        characterImageView = new ImageView();
        characterImageView.setFitWidth(200);
        characterImageView.setFitHeight(200);
        characterImageView.setPreserveRatio(true);
        characterImageView.setVisible(false);
        
        // Añadir ImageView al contenedor
        imageContainer.getChildren().add(characterImageView);
        
        // Permitir saltar animación haciendo clic
        textArea.setOnMouseClicked(e -> {
            if (textAnimator.isAnimating()) {
                textAnimator.skipAnimation();
            }
        });
        
        actualizarInterfazCompleta();
    }
    
    private void actualizarInterfazCompleta() {
        actualizarImagenPersonaje();
        actualizarEstadoJugador();
        actualizarBotones(); // Los botones aparecen inmediatamente
        
        String mensaje = gameGUI.getMensajeActual();
        textAnimator.animateText(mensaje, 30, () -> {
            // Callback cuando termina la animación - no necesitamos hacer nada extra
        });
    }
    
    private void actualizarImagenPersonaje() {
        characterImageView.setVisible(true);
        
        switch (gameGUI.getEstadoActual()) {
            case ACADEMIA_DIALOGO:
                cargarImagen("/images/aliados/Alaric.jpg");
                break;
            case BATALLA:
                if (gameGUI.getEnemigoActual() != null) {
                    cargarImagenEnemigo(gameGUI.getEnemigoActual().getNombre());
                } else {
                    characterImageView.setVisible(false);
                }
                break;
            case MENU_PRINCIPAL:
                cargarImagen("/images/portal.png");
                break;
            default:
                characterImageView.setVisible(false);
        }
    }
    
    private void cargarImagen(String ruta) {
        try {
            Image image = new Image(getClass().getResourceAsStream(ruta));
            characterImageView.setImage(image);
            characterImageView.setVisible(true);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
            characterImageView.setVisible(false);
        }
    }
    
    private void cargarImagenEnemigo(String nombreEnemigo) {
        String ruta = "/images/enemies/" + nombreEnemigo.toLowerCase().replace(" ", "_") + ".jpg";
        cargarImagen(ruta);
    }
    
    // En MainController.java - actualiza el método actualizarBotones()
            private void actualizarBotones() {
        buttonContainer.getChildren().clear();
        
        switch (gameGUI.getEstadoActual()) {
            case MENU_PRINCIPAL:
                crearBoton("Nueva Partida", () -> {
                    gameGUI.nuevaPartida();
                    actualizarInterfazCompleta();
                });
                crearBoton("Cargar Partida", () -> {
                    gameGUI.cargarPartida();
                    actualizarInterfazCompleta();
                });
                break;
                
            case ELECCION_CLASE:
                mostrarSeleccionClase();
                break;
                
            case ACADEMIA_DIALOGO:
                mostrarDialogoAcademia();
                break;
                
            case EN_JUEGO:
                crearBoton("Buscar enemigos", () -> {
                    gameGUI.buscarEnemigo();
                    actualizarInterfazCompleta();
                });
                crearBoton("Revisar misiones", () -> {
                    gameGUI.mostrarMisiones();
                    actualizarInterfazCompleta();
                });
                crearBoton("Comprobar inventario", () -> {
                    gameGUI.mostrarInventario();
                    actualizarInterfazCompleta();
                });
                crearBoton("Guardar partida", () -> {
                    gameGUI.guardarPartida();
                    actualizarInterfazCompleta();
                });
                break;
                
            case BATALLA:
                crearBoton("Atacar", () -> {
                    gameGUI.atacarEnemigo();
                    actualizarInterfazCompleta();
                });
                crearBoton("Huir", () -> {
                    gameGUI.volverAlJuego();
                    actualizarInterfazCompleta();
                });
                break;
                
            case FIN_CAPITULO:
                crearBoton("Continuar al Capítulo 2", () -> {
                    gameGUI.continuarCapitulo2();
                    actualizarInterfazCompleta();
                });
                crearBoton("Guardar partida", () -> {
                    gameGUI.guardarPartida();
                    actualizarInterfazCompleta();
                });
                break;
                
            case MISIONES:
            case INVENTARIO:
                crearBoton("Volver al juego", () -> {
                    gameGUI.volverAlJuego();
                    actualizarInterfazCompleta();
                });
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
                    actualizarInterfazCompleta();
                });
                index++;
            }
        }
    }
    
    private void crearBoton(String texto, Runnable accion) {
        Button button = new Button(texto);
        button.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-min-width: 200px; -fx-cursor: hand;");
        button.setOnAction(e -> accion.run());
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #ff6b81; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-min-width: 200px; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-min-width: 200px; -fx-cursor: hand;"));
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
        label.setStyle("-fx-text-fill: #e94560; -fx-font-size: 16px; -fx-font-weight: bold;");
        buttonContainer.getChildren().add(label);
        
        crearBoton("⚔️ Eco del Guerrero", () -> {
            gameGUI.elegirClase(nombre, 1);
            actualizarInterfazCompleta();
        });
        
        crearBoton("🗡️ Eco del Pícaro", () -> {
            gameGUI.elegirClase(nombre, 2);
            actualizarInterfazCompleta();
        });
        
        crearBoton("🔮 Eco del Sabio", () -> {
            gameGUI.elegirClase(nombre, 3);
            actualizarInterfazCompleta();
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