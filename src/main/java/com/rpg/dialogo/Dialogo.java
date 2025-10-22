package com.rpg.dialogo;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Dialogo {
    public static class Choice {
        public final String text;
        public final Consumer<Void> action; // lo que ocurre si se elige

        public Choice(String text, Consumer<Void> action) {
            this.text = text;
            this.action = action;
        }
    }

    private String npcName;
    private String line;
    private List<Choice> choices;

    public Dialogo(String npcName, String line, List<Choice> choices) {
        this.npcName = npcName;
        this.line = line;
        this.choices = choices;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(npcName + ": \"" + line + "\"");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i).text);
        }
        int opt = 0;
        while (opt < 1 || opt > choices.size()) {
            System.out.print("Elige: ");
            try {
                opt = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) { opt = 0; }
        }
        choices.get(opt - 1).action.accept(null);
    }
}
