package com.rpg.capitulo2;

import java.util.Scanner;
import com.rpg.player.Jugador;
import com.rpg.capitulo2.dialogos.DialogoLiora;
import com.rpg.capitulo2.misiones.InvestigacionArchivos;
import com.rpg.capitulo2.misiones.MisionCap2;
import com.rpg.capitulo2.eventos.EncuentroConOlvidados;
import com.rpg.capitulo2.misiones.MisionAbismoOlvidado;

public class Capitulo2 {

    private Jugador jugador;
    private MisionCap2 mision;
    private InvestigacionArchivos investigacionArchivos;
    private EncuentroConOlvidados encuentroOlvidados;
    private MisionAbismoOlvidado misionAbismoOlvidado;
    private Scanner sc;

    public Capitulo2(Jugador jugador) {
        this.jugador = jugador;
        this.mision = new MisionCap2();
        this.investigacionArchivos = new InvestigacionArchivos(jugador);
        this.encuentroOlvidados = new EncuentroConOlvidados(jugador);
        this.misionAbismoOlvidado = new MisionAbismoOlvidado(jugador);
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("\n" + "🌙".repeat(60));
        System.out.println("          CAPÍTULO 2 — LOS ECOS DE LA TRAICIÓN");
        System.out.println("🌙".repeat(60));
        
        System.out.println("\nRegresas a la Academia del Eco tras estabilizar tu existencia...");
        System.out.println("Pero el ambiente ha cambiado. Susurros recorren los pasillos.");
        System.out.println("Algo no está bien. El mentor Alaric ha desaparecido.\n");

        // Fase 1: Diálogo con Liora
        System.out.println("══════════════════════════════════════════════");
        System.out.println("             FASE 1: LA DESAPARICIÓN");
        System.out.println("══════════════════════════════════════════════");
        new DialogoLiora(jugador).iniciarConversacion();

        // Fase 2: Investigación en Archivos
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("           FASE 2: ARCHIVOS PROHIBIDOS");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("\n📜 **Nueva misión**: Investiga los archivos sellados");
        System.out.println("   para encontrar pistas sobre Alaric y sus investigaciones.\n");
        
        mision.asignarMision(jugador);
        investigacionArchivos.iniciar();

        // Fase 3: Encuentro con Los Olvidados (CONEXIÓN)
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("         FASE 3: DECISIÓN DE FACCIÓN");
        System.out.println("══════════════════════════════════════════════");
        
        conectarConEncuentroOlvidados();
        

        // Fase 4: Conclusión del Capítulo
        misionAbismoOlvidado.iniciarMision();
        concluirCapitulo();
    }

    private void conectarConEncuentroOlvidados() {
        // Verificar si completó la investigación y si no ha tenido el encuentro
        if (investigacionArchivos.isArchivosInvestigados() && 
            !encuentroOlvidados.isEncuentroRealizado()) {
            
            System.out.println("\n💭 Con la información de los archivos fresca en tu mente...");
            System.out.println("💭 Las palabras de Alaric resuenan: 'Los Olvidados no son disidentes... son víctimas.'");
            System.out.println("💭 Recuerdas la mención de Elara en los documentos.\n");
            
            System.out.println("El Distrito Abandonado te llama desde las sombras de la ciudad...");
            System.out.println("Allí podrías encontrar respuestas, pero también peligros.\n");

            boolean decisionTomada = false;
            while (!decisionTomada) {
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. 🏃‍♂️ Ir al Distrito Abandonado ahora");
                System.out.println("2. 🏛️  Regresar a la Academia primero");
                System.out.println("3. 💭 Meditar sobre lo descubierto");
                System.out.print("Elige una opción: ");

                int opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.println("\n🏃‍♂️ Te diriges al Distrito Abandonado, determinado a encontrar la verdad...");
                        encuentroOlvidados.iniciar();
                        decisionTomada = true;
                    }
                    case 2 -> {
                        System.out.println("\n🏛️  Regresas a la Academia. Quizás haya más pistas aquí...");
                        System.out.println("💬 Un guardia te mira con suspicacia: '¿Encontraste lo que buscabas en los archivos?'");
                        System.out.println("La presión aumenta. Quizás deberías haber ido al Distrito...");
                        // Pequeña penalización por indecisión
                        jugador.cambiarReputacion(-5);
                        decisionTomada = true;
                        // Podrías ofrecer otra oportunidad más tarde
                        ofrecerSegundaOportunidad();
                    }
                    case 3 -> {
                        System.out.println("\n💭 Te tomas un momento para reflexionar...");
                        System.out.println("💭 'La Academia me creó, pero me miente. Los Olvidados me ofrecen verdad, pero son enemigos de mi creadores.'");
                        System.out.println("💭 Tu existencia misma está en juego en esta decisión.\n");
                        // No sale del bucle, permite elegir again
                    }
                    default -> System.out.println("❌ Opción no válida. Debes decidirte.\n");
                }
            }
        } else if (!investigacionArchivos.isArchivosInvestigados()) {
            System.out.println("\n🔒 Necesitas investigar los archivos primero para obtener pistas sobre Los Olvidados.");
        } else {
            System.out.println("\n✅ Ya te has encontrado con Los Olvidados. El camino está trazado.");
        }
    }

    private void ofrecerSegundaOportunidad() {
        System.out.println("\n💭 Pasadas unas horas, la inquietud te carcome...");
        System.out.println("💭 Los documentos de Alaric parecen arder en tu mente.");
        System.out.println("💭 ¿Realmente puedes ignorar lo que descubriste?\n");

        System.out.println("¿Quieres reconsiderar y dirigirte al Distrito Abandonado? (s/n)");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("\n🏃‍♂️ Esta vez no lo dudas. Corres hacia el Distrito Abandonado...");
            encuentroOlvidados.iniciar();
        } else {
            System.out.println("\n🏛️  Permaneces en la Academia. Has elegido tu bando... por ahora.");
            System.out.println("💡 La lealtad tiene su precio, pero la traición también.");
        }
    }

    private void concluirCapitulo() {
        System.out.println("\n" + "⭐".repeat(60));
        System.out.println("          CONCLUSIÓN DEL CAPÍTULO 2");
        System.out.println("⭐".repeat(60));
        
        System.out.println("\n💫 Has descubierto verdades que sacuden los cimientos de tu existencia:");
        System.out.println("   - La Academia oculta el verdadero propósito del Proyecto Épsilon");
        System.out.println("   - Alaric fue silenciado por revelar secretos peligrosos");
        System.out.println("   - Los Olvidados no son los villanos que te hicieron creer");
        
        // Mostrar estado actual del jugador
        System.out.println("\n📊 **Tu situación actual:**");
        System.out.println("   🎭 Facción: " + jugador.getFaccion().getNombre());
        System.out.println("   🎖️  Reputación: " + jugador.getReputacion());
        System.out.println("   ⭐ Nivel: " + jugador.getNivel());
        
        if (encuentroOlvidados.isEncuentroRealizado()) {
            System.out.println("   🔓 Has contactado con Los Olvidados");
        } else {
            System.out.println("   🔒 Mantienes distancia de Los Olvidados");
        }

        System.out.println("\n🌌 **Lo que viene:**");
        System.out.println("   El Consejo de la Academia se prepara para el 'Evento de Purificación'");
        System.out.println("   Los Olvidados se movilizan para proteger a las Réplicas");
        System.out.println("   Tu elección determinará el destino de todos los ecos...\n");

        System.out.println("Presiona ENTER para continuar hacia el Capítulo 3...");
        sc.nextLine();
    }

    // Métodos para acceder desde otras clases si es necesario
    public InvestigacionArchivos getInvestigacionArchivos() {
        return investigacionArchivos;
    }

    public EncuentroConOlvidados getEncuentroOlvidados() {
        return encuentroOlvidados;
    }

    public boolean isCapituloCompletado() {
        return investigacionArchivos.isArchivosInvestigados() && 
               encuentroOlvidados.isEncuentroRealizado();
    }
}