package com.rpg.player;

import com.rpg.enemy.Enemigo;

public class Jugador {
    private String nombre;
    private int salud;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int voluntad;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.fuerza = 10;
        this.destreza = 10;
        this.inteligencia = 10;
        this.voluntad = 10;
    }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getFuerza() { return fuerza; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }
    public int getVoluntad() { return voluntad; }

    protected void setFuerza(int fuerza) { this.fuerza = fuerza; }
    protected void setDestreza(int destreza) { this.destreza = destreza; }
    protected void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }
    protected void setVoluntad(int voluntad) { this.voluntad = voluntad; }


    public boolean estaVivo() { return salud > 0; }

    public void recibirDaño(int dmg) {
        salud -= dmg;
        if(salud < 0) salud = 0;
    }

    public void atacar(Enemigo enemigo) {
        int danio = fuerza;
        System.out.println(nombre + " ataca a " + enemigo.getNombre() + " causando " + danio + " de daño.");
        enemigo.recibirDaño(danio);
    }

    public void mostrarStats() {
        System.out.println("---- " + nombre + " ----");
        System.out.println("Salud: " + salud);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Destreza: " + destreza);
        System.out.println("Inteligencia: " + inteligencia);
        System.out.println("Voluntad: " + voluntad);
        System.out.println("------------------");
    }
}
