package com.rpg.capitulo2.dialogos;

import java.util.Scanner;
import com.rpg.player.Jugador;

public class DialogoLiora {
    private Scanner sc = new Scanner(System.in);
    private Jugador jugador;
    private boolean primeraVez = true;

    public DialogoLiora(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciarConversacion() {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("           📖 ENCUENTRO CON LIORA");
        System.out.println("══════════════════════════════════════════════\n");
        
        if (primeraVez) {
            // Diálogo inicial dramático
            System.out.println("La Archivista Liora te recibe con mirada preocupada.");
            System.out.println("Sus manos tiemblan ligeramente mientras ordena pergaminos antiguos.");
            System.out.println("📖 Liora: '" + jugador.getNombre() + "... Me alegra verte con vida, pero temo traer noticias sombrías.'");
            System.out.println("📖 Liora: 'Alaric... desapareció anoche. Nadie sabe dónde está.'");
            System.out.println("📖 Liora: 'Lo único que dejó fue esto en su escritorio... su diario personal.'");
            System.out.println("📖 Liora: 'Debes ser cauteloso. Los ecos observan cada movimiento ahora.'");
            primeraVez = false;
        } else {
            System.out.println("📖 Liora: 'Has regresado... ¿Encontraste algo en el diario?'");
        }

        System.out.println("\n¿Cómo procedes?");
        System.out.println("1. 🔍 Preguntar qué sabe realmente la Academia");
        System.out.println("2. 🤫 Tomar el diario y marcharte en silencio");
        System.out.println("3. 🎭 Preguntar sobre 'Los Olvidados'");
        System.out.println("4. 💬 Cuestionar la desaparición de Alaric");

        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> {
                System.out.println("\n📖 Liora: *Baja la voz* 'La situación es... complicada.'");
                System.out.println("📖 Liora: 'No todos en el Consejo confiaban en Alaric. Algunos creían que estaba investigando cosas que deberían permanecer ocultas.'");
                System.out.println("📖 Liora: 'Hubo un incidente hace años... el \"Abismo Olvidado\". Alaric siempre insistía en que la versión oficial era mentira.'");
                System.out.println("📖 Liora: 'El Capitán Kael ha estado haciendo muchas preguntas... demasiadas.'");
                jugador.cambiarReputacion(+10); // +10 por buscar información
            }
            case 2 -> {
                System.out.println("\n📖 Liora: *Asiente solemnemente* 'La discreción es sabia en estos tiempos.'");
                System.out.println("📖 Liora: 'El diario puede contener respuestas, pero también peligros.'");
                System.out.println("📖 Liora: 'Alaric escribió en código algunas partes. Busca patrones en sus anotaciones.'");
                System.out.println("📖 Liora: 'Que los ecos te guíen, " + jugador.getNombre() + "...'");
                jugador.cambiarReputacion(-5); // -5 por ser demasiado reservado
            }
            case 3 -> {
                System.out.println("\n📖 Liora: *Se pone pálida* '¿Dónde escuchaste ese nombre?'");
                System.out.println("📖 Liora: 'Hablar de Los Olvidados es peligroso aquí. Son... disidentes. Réplicas que rechazaron el control de la Academia.'");
                System.out.println("📖 Liora: 'Alaric simpatizaba con su causa en secreto. Si buscas respuestas, quizás ellos puedan ayudarte.'");
                System.out.println("📖 Liora: 'Pero ten cuidado... una elección tiene consecuencias.'");
                jugador.cambiarReputacion(+15); // +15 por hacer la pregunta correcta
            }
            case 4 -> {
                System.out.println("\n📖 Liora: *Suspira profundamente* 'Su desaparición no fue accidental.'");
                System.out.println("📖 Liora: 'La noche que desapareció, hubo una reunión urgente del Consejo. Alaric se enfrentó al Archimago Theron.'");
                System.out.println("📖 Liora: 'Discutieron sobre ti, sobre todas las Réplicas. Alaric gritó \"¡No son herramientas!\" antes de salir furioso.'");
                System.out.println("📖 Liora: 'Esa fue la última vez que alguien lo vio.'");
                jugador.cambiarReputacion(+5); // +5 por indagar específicamente
            }
            default -> {
                System.out.println("\n📖 Liora: 'El tiempo apremia, " + jugador.getNombre() + ". Debes decidirte.'");
            }
        }

        // Diálogo adicional basado en reputación
        if (jugador.getReputacion() > 20) {
            System.out.println("\n📖 Liora: *En un susurro casi inaudible* 'Busca la estatua del héroe caído en los jardines. Hay un mensaje para ti allí.'");
        }

        System.out.println("\n📖 Liora: 'El diario de Alaric es tuyo. Que encuentres las respuestas que buscas.'");
        System.out.println("Has obtenido: 📓 Diario de Alaric");
    }

    public boolean haEntregadoDiario() {
        return !primeraVez;
    }
}