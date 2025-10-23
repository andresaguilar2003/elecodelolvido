package com.rpg.enemy;

import com.rpg.player.Jugador;
public class Enemigo {
    private String nombre;
    private int salud;
    private int ataque;
    private int experienciaBase;

    public Enemigo(String nombre, int salud, int ataque, int experienciaBase) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.experienciaBase = experienciaBase;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return salud; }
    public int getAtaque() { return ataque; }
    public int getExperienciaBase() { return experienciaBase; }
        public void setVida(int salud) {
        this.salud = Math.max(0, salud);
    }
    public void setAtaque(int ataque) {
        this.ataque = Math.max(0, ataque);
    }
    public boolean estaVivo() { return salud > 0; }

    public void recibirDaño(int dmg) {
        salud -= dmg;
        if(salud < 0) salud = 0;
    }

    public void atacar(Jugador jugador) {
        System.out.println(nombre + " ataca a " + jugador.getNombre() + " causando " + ataque + " de daño.");
        jugador.recibirDaño(ataque);
    }
}
