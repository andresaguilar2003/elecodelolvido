// GameGUI.java (modificado)
package com.rpg.ui;

import java.util.Random;
import com.rpg.player.*;
import com.rpg.enemy.Enemigo;
import com.rpg.enemy.tipo.EspectroJefe;
import com.rpg.world.Zona;
import com.rpg.guardar.SaveManager;
import com.rpg.misiones.MisionManager;
import com.rpg.capitulo2.Capitulo2;

public class GameGUI {
    private Jugador jugador;
    private Zona bosque = new Zona("Bosque de los Susurros");
    private SaveManager saveManager = new SaveManager();
    private MisionManager misionManager = new MisionManager();
    private Random rand = new Random();
    private AcademiaGUI academiaGUI = new AcademiaGUI();
    
    // Estados del juego para la interfaz
    public enum GameState {
        MENU_PRINCIPAL, 
        ELECCION_CLASE, 
        EN_JUEGO, 
        BATALLA, 
        INVENTARIO, 
        MISIONES, 
        FIN_CAPITULO,
        ACADEMIA_DIALOGO  // Nuevo estado para el diálogo de la academia
    }
    
    private GameState estadoActual = GameState.MENU_PRINCIPAL;
    private String mensajeActual = "";
    private Enemigo enemigoActual;
    private AcademiaGUI.DialogoAcademia dialogoActual;
    
    public GameGUI() {
        iniciarJuego();
    }
    
    public void iniciarJuego() {
        estadoActual = GameState.MENU_PRINCIPAL;
        mensajeActual = "🌌 RÉPLICA: EL ECO DEL HÉROE 🌌\n\n" +
                       "Te despiertas dentro de una cámara brillante...\n" +
                       "Una voz te susurra: 'Tu eco aún no se ha formado... Elige tu reflejo.'";
    }
    
    // Métodos para la interfaz gráfica
    public void nuevaPartida() {
        estadoActual = GameState.ELECCION_CLASE;
        mensajeActual = "Elige tu tipo de Eco:";
    }
    
    public void cargarPartida() {
        jugador = saveManager.cargar();
        if (jugador != null) {
            estadoActual = GameState.EN_JUEGO;
            mensajeActual = "✅ Partida cargada correctamente.\n" +
                           "Bienvenido de nuevo, " + jugador.getNombre() + " — " + 
                           jugador.getClass().getSimpleName() + "\n\n" +
                           "Tu existencia es frágil... pero tu viaje comienza ahora.";
            // No entrar a academia automáticamente al cargar
        } else {
            mensajeActual = "❌ No se encontró partida guardada.";
        }
    }
    
    public void elegirClase(String nombre, int clase) {
        switch (clase) {
            case 1 -> jugador = new EcoGuerrero(nombre);
            case 2 -> jugador = new EcoPicaro(nombre);
            case 3 -> jugador = new EcoSabio(nombre);
            default -> {
                jugador = new EcoGuerrero(nombre);
            }
        }
        
        // En lugar de entrar directamente a la academia, mostramos el diálogo
        estadoActual = GameState.ACADEMIA_DIALOGO;
        entrarAcademia();
    }
    
    public void seleccionarOpcionDialogo(int indiceOpcion) {
        if (dialogoActual != null && indiceOpcion >= 0 && indiceOpcion < dialogoActual.getOpciones().size()) {
            AcademiaGUI.OpcionDialogo opcion = dialogoActual.getOpciones().get(indiceOpcion);
            opcion.getAccion().run();
            
            // Después de seleccionar la opción, continuar al juego normal
            estadoActual = GameState.EN_JUEGO;
            mensajeActual = "🏛️ El mentor asiente solemnemente.\n\n" +
                           "📜 Nueva misión asignada: Eco del Bosque\n" +
                           "💫 Viaja al Bosque de los Susurros y derrota al Espectro Jefe\n\n" +
                           "Tu reputación actual: " + jugador.getReputacion() + "\n" +
                           "(Luminoso > 50 | Neutral -50–50 | Oscuro < -50)\n\n" +
                           "Dirígete al Bosque de los Susurros...";
        }
    }
    
    public void buscarEnemigo() {
        if (jugador == null) return;
        
        if (rand.nextInt(10) == 0) {
            enemigoActual = new EspectroJefe();
        } else {
            enemigoActual = bosque.generarEnemigo();
        }
        
        estadoActual = GameState.BATALLA;
        mensajeActual = "🌲 ¡Un " + enemigoActual.getNombre() + " aparece!\n\n" +
                       "Preparate para el combate...\n\n" +
                       "Tu HP: " + jugador.getVida() + "\n" +
                       "HP del " + enemigoActual.getNombre() + ": " + enemigoActual.getVida();
    }
    
    public String atacarEnemigo() {
        if (enemigoActual == null || !enemigoActual.estaVivo()) {
            return "No hay enemigo para atacar.";
        }
        
        StringBuilder resultado = new StringBuilder();
        
        // El jugador ataca al enemigo
        int danioBase = jugador.getFuerza();
        resultado.append("⚔️ ").append(jugador.getNombre()).append(" ataca al ")
                .append(enemigoActual.getNombre()).append(" y le inflige ")
                .append(danioBase).append(" de daño!\n");
        
        enemigoActual.recibirDaño(danioBase);
        
        if (!enemigoActual.estaVivo()) {
            resultado.append("\n💀 Has derrotado al ").append(enemigoActual.getNombre()).append("!\n");
            
            if (enemigoActual instanceof EspectroJefe) {
                resultado.append("\n💫 Has derrotado al Espectro Jefe.\n");
                misionManager.completarMision("Eco del Bosque", jugador);
                estadoActual = GameState.FIN_CAPITULO;
                resultado.append("🌙 Fin del Capítulo 1 — 'El Eco Despierta' 🌙");
            } else {
                estadoActual = GameState.EN_JUEGO;
                resultado.append("Regresas al bosque...");
            }
        } else {
            // El enemigo contraataca
            resultado.append("\n");
            enemigoActual.atacar(jugador);
            resultado.append("🛡️ El ").append(enemigoActual.getNombre())
                    .append(" te ataca y te inflige ").append(enemigoActual.getAtaque()).append(" de daño!\n")
                    .append("Tu HP restante: ").append(jugador.getVida());
            
            if (!jugador.estaVivo()) {
                resultado.append("\n\n💀 Tu eco se desvanece en la eternidad...");
                estadoActual = GameState.MENU_PRINCIPAL;
            }
        }
        
        mensajeActual = resultado.toString();
        return mensajeActual;
    }
    
    public void mostrarMisiones() {
        estadoActual = GameState.MISIONES;
        mensajeActual = "🌀 MISIONES ACTUALES:\n\n" + 
                       "🌟 Misión Principal:\n" +
                       "- Eco del Bosque: Derrota al Espectro Jefe\n" +
                       "  (Busca enemigos en el bosque hasta que aparezca)\n\n" +
                       "Progreso: Busca enemigos en el bosque";
    }
    
    public void mostrarInventario() {
        estadoActual = GameState.INVENTARIO;
        StringBuilder sb = new StringBuilder("🎒 INVENTARIO:\n\n");
        if (jugador.getInventario().isEmpty()) {
            sb.append("Tu inventario está vacío.\n");
        } else {
            jugador.getInventario().forEach(it -> sb.append("- ").append(it.getNombre()).append("\n"));
        }
        mensajeActual = sb.toString();
    }
    
    public void guardarPartida() {
        if (jugador != null) {
            saveManager.guardar(jugador);
            mensajeActual = "✅ Partida guardada correctamente.";
        } else {
            mensajeActual = "❌ No hay partida para guardar.";
        }
    }
    
    public void continuarCapitulo2() {
        if (jugador != null) {
            estadoActual = GameState.EN_JUEGO;
            mensajeActual = "🎭 Iniciando Capítulo 2: 'Los Ecos de la Traición'...\n\n" +
                           "Próximamente disponible...";
        }
    }
    
    public void volverAlJuego() {
        if (jugador != null && jugador.estaVivo()) {
            estadoActual = GameState.EN_JUEGO;
            mensajeActual = "Regresas al Bosque de los Susurros...\n\n" +
                           "¿Qué deseas hacer?";
        }
    }
    
    // Getters para la interfaz
    public Jugador getJugador() {
        return jugador;
    }
    
    public GameState getEstadoActual() {
        return estadoActual;
    }
    
    public String getMensajeActual() {
        return mensajeActual;
    }
    
    public AcademiaGUI.DialogoAcademia getDialogoActual() {
        return dialogoActual;
    }
    
    public boolean isJugadorVivo() {
        return jugador != null && jugador.estaVivo();
    }
    
    // Métodos privados
    private void entrarAcademia() {
        dialogoActual = academiaGUI.entrar(jugador, misionManager);
        mensajeActual = dialogoActual.getTitulo() + "\n\n" + dialogoActual.getMensaje();
    }
}