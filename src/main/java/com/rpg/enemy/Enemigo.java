package com.rpg.enemy;

import com.rpg.player.Jugador;
public class Enemigo {
    private String nombre;
    private int salud;
    private int ataque;

    public Enemigo(String nombre, int salud, int ataque) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
    }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getAtaque() { return ataque; }

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
