// MainApp.java
package com.rpg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Corrección: usa getResourceAsStream para mejor compatibilidad
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rpg/ui/MainView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 1200, 800);
        
        // Corrección: CSS en la ubicación correcta
        try {
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el CSS: " + e.getMessage());
        }
        
        stage.setTitle("RÉPLICA: EL ECO DEL HÉROE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}