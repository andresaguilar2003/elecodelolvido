// MainController.java (actualizado)
package com.rpg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;

import com.rpg.ui.AcademiaGUI;
import com.rpg.ui.GameGUI;
import com.rpg.ui.TextAnimator;

public class MainController {
    
    @FXML private TextArea textArea;
    @FXML private VBox buttonContainer;
    @FXML private Label statusLabel;
    @FXML private Label playerInfoLabel;
    @FXML private StackPane imageContainer;
    @FXML private VBox playerStatsContainer; // Nuevo contenedor para stats del jugador
    
    private GameGUI gameGUI;
    private TextAnimator textAnimator;
    private ImageView characterImageView;
    private ImageView playerAvatarView; // Nueva ImageView para el avatar del jugador
    
    @FXML
    public void initialize() {
        gameGUI = new GameGUI();
        textAnimator = new TextAnimator(textArea);
        
        // Configurar ImageView para personajes - MÁS GRANDE
        characterImageView = new ImageView();
        characterImageView.setFitWidth(350);
        characterImageView.setFitHeight(350);
        characterImageView.setPreserveRatio(true);
        characterImageView.setVisible(false);
        
        // Configurar ImageView para el avatar del jugador (siempre visible)
        playerAvatarView = new ImageView();
        playerAvatarView.setFitWidth(120);
        playerAvatarView.setFitHeight(120);
        playerAvatarView.setPreserveRatio(true);
        playerAvatarView.setVisible(false);
        
        // Añadir ImageViews al contenedor
        imageContainer.getChildren().add(characterImageView);
        
        // Configurar el contenedor de stats del jugador
        configurarPlayerStatsContainer();
        
        // Permitir saltar animación haciendo clic
        textArea.setOnMouseClicked(e -> {
            if (textAnimator.isAnimating()) {
                textAnimator.skipAnimation();
            }
        });
        
        actualizarInterfazCompleta();
    }

    private void configurarPlayerStatsContainer() {
        // Inicializar el contenedor con un diseño básico
        playerStatsContainer.setAlignment(Pos.TOP_CENTER);
        playerStatsContainer.setStyle("-fx-padding: 15px; -fx-background-color: #16213e; -fx-border-color: #e94560; -fx-border-width: 2px; -fx-border-radius: 10px;");
        
        // Crear contenido inicial del contenedor
        Label tituloInicial = new Label("Crea tu personaje");
        tituloInicial.setStyle("-fx-text-fill: #e94560; -fx-font-weight: bold; -fx-font-size: 16px;");
        
        Label instruccion = new Label("Selecciona 'Nueva Partida' para comenzar");
        instruccion.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-wrap-text: true;");
        instruccion.setMaxWidth(200);
        instruccion.setAlignment(Pos.CENTER);
        
        playerStatsContainer.getChildren().addAll(tituloInicial, instruccion);
    }

    private void actualizarInterfazCompleta() {
        actualizarImagenPersonaje();
        actualizarAvatarJugador();
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
                cargarImagen("/images/aliados/Alaric.jpg", characterImageView);
                break;
            case BATALLA:
                if (gameGUI.getEnemigoActual() != null) {
                    cargarImagenEnemigo(gameGUI.getEnemigoActual().getNombre(), characterImageView);
                } else {
                    characterImageView.setVisible(false);
                }
                break;
            case MENU_PRINCIPAL:
                cargarImagen("/images/portal.png", characterImageView);
                break;
            case ELECCION_CLASE:
                characterImageView.setVisible(false);
                break;
            default:
                characterImageView.setVisible(false);
        }
    }
    
    private void actualizarAvatarJugador() {
        if (gameGUI.getJugador() != null) {
            playerAvatarView.setVisible(true);
            String rutaImagen = obtenerRutaAvatarJugador();
            cargarImagen(rutaImagen, playerAvatarView);
            actualizarPlayerStatsContainer(); // Actualizar stats cuando hay jugador
        } else {
            playerAvatarView.setVisible(false);
            // Mostrar mensaje de crear personaje cuando no hay jugador
            if (gameGUI.getEstadoActual() == GameGUI.GameState.MENU_PRINCIPAL) {
                playerStatsContainer.getChildren().clear();
                Label titulo = new Label("Crea tu personaje");
                titulo.setStyle("-fx-text-fill: #e94560; -fx-font-weight: bold; -fx-font-size: 16px;");
                
                Label instruccion = new Label("Selecciona 'Nueva Partida' para comenzar");
                instruccion.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-wrap-text: true;");
                instruccion.setMaxWidth(200);
                instruccion.setAlignment(Pos.CENTER);
                
                playerStatsContainer.getChildren().addAll(titulo, instruccion);
            }
        }
    }
    
    private String obtenerRutaAvatarJugador() {
        if (gameGUI.getJugador() instanceof com.rpg.player.EcoGuerrero) {
            return "/images/aliados/EcoGuerrero.jpg";
        } else if (gameGUI.getJugador() instanceof com.rpg.player.EcoPicaro) {
            return "/images/aliados/EcoPicaro.jpg";
        } else if (gameGUI.getJugador() instanceof com.rpg.player.EcoSabio) {
            return "/images/aliados/EcoSabio.jpg";
        } else {
            return "/images/aliados/EcoGuerrero.jpg";
        }
    }

    private void cargarImagen(String ruta, ImageView imageView) {
        try {
            Image image = new Image(getClass().getResourceAsStream(ruta));
            imageView.setImage(image);
            imageView.setVisible(true);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
            imageView.setVisible(false);
        }
    }
    
    private void cargarImagenEnemigo(String nombreEnemigo, ImageView imageView) {
        String ruta = "/images/enemies/" + nombreEnemigo.toLowerCase().replace(" ", "_") + ".jpg";
        cargarImagen(ruta, imageView);
    }
    
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
                crearBoton("Ataque Especial", () -> {
                    gameGUI.usarHabilidadEspecial();
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
                mostrarSeleccionClaseConImagenes(nombre.trim());
            }
        });
    }
    
        private void mostrarSeleccionClaseConImagenes(String nombre) {
        buttonContainer.getChildren().clear();
        
        Label label = new Label("Elige tu tipo de Eco para " + nombre + ":");
        label.setStyle("-fx-text-fill: #e94560; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");
        buttonContainer.getChildren().add(label);
        
        HBox clasesContainer = new HBox(20);
        clasesContainer.setAlignment(Pos.CENTER);
        clasesContainer.setStyle("-fx-padding: 20px;");
        
        VBox guerreroBox = crearOpcionClase(
            "⚔️ Eco del Guerrero", 
            "/images/aliados/EcoGuerrero.jpg", 
            nombre, 
            1,
            "Fuerza bruta y resistencia\n- Alta fuerza\n- Buena defensa\n- Habilidades de combate cuerpo a cuerpo"
        );
        
        VBox picaroBox = crearOpcionClase(
            "🗡️ Eco del Pícaro", 
            "/images/aliados/EcoPicaro.jpg", 
            nombre, 
            2,
            "Sigilo y precisión\n- Alta destreza\n- Ataques críticos\n- Habilidades de evasión"
        );
        
        VBox sabioBox = crearOpcionClase(
            "🔮 Eco del Sabio", 
            "/images/aliados/EcoSabio.jpg", 
            nombre, 
            3,
            "Conocimiento y magia\n- Alta inteligencia\n- Poderes mágicos\n- Habilidades de apoyo"
        );
        
        clasesContainer.getChildren().addAll(guerreroBox, picaroBox, sabioBox);
        buttonContainer.getChildren().add(clasesContainer);
    }

    
        private VBox crearOpcionClase(String nombreClase, String rutaImagen, String nombreJugador, int tipoClase, String descripcion) {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-padding: 15px; -fx-border-color: #e94560; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-color: #16213e;");
        
        ImageView imagenClase = new ImageView();
        imagenClase.setFitWidth(180);
        imagenClase.setFitHeight(180);
        imagenClase.setPreserveRatio(true);
        
        try {
            Image image = new Image(getClass().getResourceAsStream(rutaImagen));
            imagenClase.setImage(image);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
            imagenClase.setStyle("-fx-background-color: #0f3460; -fx-min-width: 180; -fx-min-height: 180;");
        }
        
        Button boton = new Button(nombreClase);
        boton.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 15px; -fx-min-width: 180px; -fx-cursor: hand;");
        boton.setOnAction(e -> {
            gameGUI.elegirClase(nombreJugador, tipoClase);
            actualizarInterfazCompleta();
        });
        
        boton.setOnMouseEntered(e -> {
            boton.setStyle("-fx-background-color: #ff6b81; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 15px; -fx-min-width: 180px; -fx-cursor: hand;");
            container.setStyle("-fx-padding: 15px; -fx-border-color: #ff6b81; -fx-border-width: 3px; -fx-border-radius: 10px; -fx-background-color: #1a2b4a;");
        });
        
        boton.setOnMouseExited(e -> {
            boton.setStyle("-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 15px; -fx-min-width: 180px; -fx-cursor: hand;");
            container.setStyle("-fx-padding: 15px; -fx-border-color: #e94560; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-color: #16213e;");
        });
        
        Label descLabel = new Label(descripcion);
        descLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12px; -fx-text-alignment: center; -fx-wrap-text: true;");
        descLabel.setMaxWidth(180);
        descLabel.setAlignment(Pos.CENTER);
        
        container.getChildren().addAll(imagenClase, boton, descLabel);
        return container;
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
    

    private void actualizarPlayerStatsContainer() {
        if (playerStatsContainer != null && gameGUI.getJugador() != null) {
            playerStatsContainer.getChildren().clear();
            
            VBox statsBox = new VBox(8);
            statsBox.setAlignment(Pos.TOP_CENTER);
            statsBox.setStyle("-fx-padding: 15px;");
            
            Label titulo = new Label("Tus Estadísticas");
            titulo.setStyle("-fx-text-fill: #e94560; -fx-font-weight: bold; -fx-font-size: 16px;");
            
            // Avatar del jugador - más grande para el panel de stats
            ImageView statsAvatar = new ImageView();
            statsAvatar.setFitWidth(300);
            statsAvatar.setFitHeight(300);
            statsAvatar.setPreserveRatio(true);
            
            String rutaAvatar = obtenerRutaAvatarJugador();
            cargarImagen(rutaAvatar, statsAvatar);
            statsAvatar.setStyle("-fx-border-color: #e94560; -fx-border-width: 2px; -fx-border-radius: 5px;");
            
            VBox avatarBox = new VBox(5);
            avatarBox.setAlignment(Pos.CENTER);
            avatarBox.getChildren().add(statsAvatar);
            
            // Estadísticas
            VBox statsInfo = new VBox(3);
            statsInfo.setAlignment(Pos.CENTER_LEFT);
            
            Label nombre = new Label("Nombre: " + gameGUI.getJugador().getNombre());
            nombre.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label clase = new Label("Clase: " + gameGUI.getJugador().getClass().getSimpleName().replace("Eco", "Eco "));
            clase.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label nivel = new Label("Nivel: " + gameGUI.getJugador().getNivel());
            nivel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label hp = new Label("HP: " + gameGUI.getJugador().getVida());
            hp.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label fuerza = new Label("Fuerza: " + gameGUI.getJugador().getFuerza());
            fuerza.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label destreza = new Label("Destreza: " + gameGUI.getJugador().getDestreza());
            destreza.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            Label inteligencia = new Label("Inteligencia: " + gameGUI.getJugador().getInteligencia());
            inteligencia.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            
            statsInfo.getChildren().addAll(nombre, clase, nivel, hp, fuerza, destreza, inteligencia);
            statsBox.getChildren().addAll(titulo, avatarBox, statsInfo);
            playerStatsContainer.getChildren().add(statsBox);
        } else if (playerStatsContainer != null) {
            // Mensaje cuando no hay jugador
            playerStatsContainer.getChildren().clear();
            Label titulo = new Label("Crea tu personaje");
            titulo.setStyle("-fx-text-fill: #e94560; -fx-font-weight: bold; -fx-font-size: 16px;");
            
            Label instruccion = new Label("Selecciona 'Nueva Partida' para comenzar");
            instruccion.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-wrap-text: true;");
            instruccion.setMaxWidth(200);
            instruccion.setAlignment(Pos.CENTER);
            
            playerStatsContainer.getChildren().addAll(titulo, instruccion);
        }
    }
}