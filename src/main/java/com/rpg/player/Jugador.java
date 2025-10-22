package com.rpg.player;

import com.rpg.enemy.Enemigo;
import com.rpg.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int vida;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int voluntad;

    // Sistema de progreso
    private int nivel;
    private int experiencia;
    private int experienciaParaSiguiente;

    // Inventario simple
    private List<Item> inventario = new ArrayList<>();
    private Item objetoEquipado = null;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vida = 100;
        this.fuerza = 10;
        this.destreza = 10;
        this.inteligencia = 10;
        this.voluntad = 10;
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaParaSiguiente = 50;
    }

    // getters
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getFuerza() { return fuerza; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }
    public int getVoluntad() { return voluntad; }
    public int getNivel() { return nivel; }
    public int getExperiencia() { return experiencia; }
    public int getExperienciaParaSiguiente() { return experienciaParaSiguiente; }
    public List<Item> getInventario() { return inventario; }
    public Item getObjetoEquipado() { return objetoEquipado; }

    // setters protegidos para subclases
    protected void setFuerza(int fuerza) { this.fuerza = fuerza; }
    protected void setDestreza(int destreza) { this.destreza = destreza; }
    protected void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }
    protected void setVoluntad(int voluntad) { this.voluntad = voluntad; }

    public boolean estaVivo() { return vida > 0; }

    public void recibirDaño(int dmg) {
        vida -= dmg;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > 100) vida = 100;
    }

    public void atacar(Enemigo enemigo) {
        int danioBase = fuerza;
        if (objetoEquipado != null && objetoEquipado.getBonusAtaque() > 0) {
            danioBase += objetoEquipado.getBonusAtaque();
        }
        System.out.println(nombre + " ataca a " + enemigo.getNombre() + " causando " + danioBase + " de daño.");
        enemigo.recibirDaño(danioBase);
    }

    public void ganarExperiencia(int xp) {
        experiencia += xp;
        System.out.println("Has ganado " + xp + " XP.");
        while (experiencia >= experienciaParaSiguiente) {
            experiencia -= experienciaParaSiguiente;
            nivelUp();
        }
    }

    private void nivelUp() {
        nivel++;
        fuerza += 3;
        destreza += 2;
        inteligencia += 2;
        voluntad += 2;
        vida = 100;
        experienciaParaSiguiente += 30; // escala
        System.out.println("¡Has subido a nivel " + nivel + "! Stats mejorados.");
    }

    // Inventario
    public void recogerItem(Item item) {
        inventario.add(item);
        System.out.println("Has recogido: " + item.getNombre());
    }

    public void usarItem(String nombre) {
        Item encontrado = null;
        for (Item it : inventario) {
            if (it.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = it;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("No tienes ese item.");
            return;
        }
        if (encontrado.isConsumible()) {
            curar(encontrado.getCuracion());
            inventario.remove(encontrado);
            System.out.println("Has usado " + encontrado.getNombre());
        } else {
            equipar(encontrado);
        }
    }

    public void equipar(Item item) {
        if (!inventario.contains(item)) {
            System.out.println("No posees ese objeto.");
            return;
        }
        objetoEquipado = item;
        System.out.println("Has equipado " + item.getNombre());
    }

    // Mostrar stats
    public void mostrarStats() {
        System.out.println("---- " + nombre + " ----");
        System.out.println("Nivel: " + nivel + " | XP: " + experiencia + "/" + experienciaParaSiguiente);
        System.out.println("Vida: " + vida);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Destreza: " + destreza);
        System.out.println("Inteligencia: " + inteligencia);
        System.out.println("Voluntad: " + voluntad);
        System.out.println("Equipado: " + (objetoEquipado == null ? "Nada" : objetoEquipado.getNombre()));
        System.out.println("------------------");
    }
}
