// AcademiaGUI.java
package com.rpg.ui;

import com.rpg.player.Jugador;
import com.rpg.misiones.MisionManager;
import com.rpg.misiones.Mision;
import java.util.ArrayList;
import java.util.List;

public class AcademiaGUI {
    
    public static class DialogoAcademia {
        private String titulo;
        private String mensaje;
        private List<OpcionDialogo> opciones;
        
        public DialogoAcademia(String titulo, String mensaje) {
            this.titulo = titulo;
            this.mensaje = mensaje;
            this.opciones = new ArrayList<>();
        }
        
        public void agregarOpcion(String texto, Runnable accion) {
            opciones.add(new OpcionDialogo(texto, accion));
        }
        
        public String getTitulo() { return titulo; }
        public String getMensaje() { return mensaje; }
        public List<OpcionDialogo> getOpciones() { return opciones; }
    }
    
    public static class OpcionDialogo {
        private String texto;
        private Runnable accion;
        
        public OpcionDialogo(String texto, Runnable accion) {
            this.texto = texto;
            this.accion = accion;
        }
        
        public String getTexto() { return texto; }
        public Runnable getAccion() { return accion; }
    }
    
    public DialogoAcademia entrar(Jugador jugador, MisionManager misionManager) {
        DialogoAcademia dialogo = new DialogoAcademia(
            "🏛️ Academia del Eco",
            "Has vuelto, " + jugador.getNombre() + ". ¿Qué propósito guía tu reflejo?\n\n" +
            "El aire vibra con ecos antiguos..."
        );
        
        // Agregar opciones de diálogo
        dialogo.agregarOpcion("🕊️ Busco proteger la armonía de este mundo.", () -> {
            jugador.cambiarReputacion(+25);
        });
        
        dialogo.agregarOpcion("⚔️ Haré lo que deba, aunque el mundo arda.", () -> {
            jugador.cambiarReputacion(-25);
        });
        
        dialogo.agregarOpcion("💭 No lo sé... aún me busco a mí mismo.", () -> {
            jugador.cambiarReputacion(0);
        });
        
        // Asignar misión principal
        Mision primeraMision = new Mision(
            "Eco del Bosque",
            "Viaja al Bosque de los Susurros y derrota al Espectro Jefe para recuperar un fragmento del pasado.",
            true,
            100
        );
        misionManager.asignarMision(primeraMision);
        
        return dialogo;
    }
}