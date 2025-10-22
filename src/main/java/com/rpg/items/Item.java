package com.rpg.items;

public class Item {
    private String nombre;
    private boolean consumible;
    private int curacion; // si es consumible
    private int bonusAtaque; // si es arma

    public Item(String nombre, boolean consumible, int curacion, int bonusAtaque) {
        this.nombre = nombre;
        this.consumible = consumible;
        this.curacion = curacion;
        this.bonusAtaque = bonusAtaque;
    }

    public String getNombre() { return nombre; }
    public boolean isConsumible() { return consumible; }
    public int getCuracion() { return curacion; }
    public int getBonusAtaque() { return bonusAtaque; }
}
