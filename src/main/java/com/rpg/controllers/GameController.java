// GameController.java
package com.rpg.controllers;

import com.rpg.player.Jugador;

public interface GameController {
    void iniciarJuego();
    void elegirClase(String nombre, int clase);
    void realizarAccion(int opcion);
    Jugador getJugador();
    String getEstadoJuego();
    void guardarPartida();
    void cargarPartida();
}