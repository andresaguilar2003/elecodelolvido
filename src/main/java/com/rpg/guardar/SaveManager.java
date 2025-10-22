package com.rpg.guardar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rpg.player.Jugador;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveManager {
    private static final String SAVE_PATH = "savegame.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void guardar(Jugador jugador) {
        try (FileWriter writer = new FileWriter(SAVE_PATH)) {
            gson.toJson(jugador, writer);
            System.out.println("Juego guardado en " + new File(SAVE_PATH).getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error guardando juego: " + e.getMessage());
        }
    }

    public Jugador cargar() {
        try (FileReader reader = new FileReader(SAVE_PATH)) {
            Jugador j = gson.fromJson(reader, Jugador.class);
            System.out.println("Juego cargado.");
            return j;
        } catch (Exception e) {
            System.out.println("No se encontró partida guardada o error al cargar: " + e.getMessage());
            return null;
        }
    }
}
