package com.rpg.misiones;

public class Mision {

    public enum Estado {
        NO_INICIADA, EN_PROGRESO, COMPLETADA, FALLIDA
    }

    private String nombre;
    private String descripcion;
    private boolean moralBuena; // Si influye positivamente o negativamente en la moral
    private int recompensaXP;
    private Estado estado;

    public Mision(String nombre, String descripcion, boolean moralBuena, int recompensaXP) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.moralBuena = moralBuena;
        this.recompensaXP = recompensaXP;
        this.estado = Estado.NO_INICIADA;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Estado getEstado() { return estado; }

    public void iniciar() {
        if (estado == Estado.NO_INICIADA) {
            estado = Estado.EN_PROGRESO;
            System.out.println("\n🗺️ Nueva misión iniciada: " + nombre);
            System.out.println(descripcion);
        }
    }

    public void completar() {
        if (estado == Estado.EN_PROGRESO) {
            estado = Estado.COMPLETADA;
            System.out.println("\n✅ Misión completada: " + nombre);
        }
    }

    public boolean esMoralBuena() {
        return moralBuena;
    }

    public int getRecompensaXP() {
        return recompensaXP;
    }
}
