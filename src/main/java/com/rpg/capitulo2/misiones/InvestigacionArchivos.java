package com.rpg.capitulo2.misiones;

import java.util.Scanner;
import com.rpg.player.*;
import com.rpg.enemy.Enemigo;
import com.rpg.enemy.tipo.GuardiaEco;
import com.rpg.game.Batalla;
import com.rpg.capitulo2.facciones.SistemaReputacion;

public class InvestigacionArchivos {

    private Jugador jugador;
    private Scanner sc = new Scanner(System.in);
    private boolean archivosInvestigados = false;

    public InvestigacionArchivos(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciar() {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("         📚 INVESTIGACIÓN EN LOS ARCHIVOS");
        System.out.println("══════════════════════════════════════════════\n");
        
        System.out.println("Caminas por los pasillos silenciosos de la Academia...");
        System.out.println("El aire huele a pergamino antiguo y polvo de siglos.");
        System.out.println("Al final del corredor, las imponentes puertas de roble de los Archivos Sellados se alzan ante ti.\n");
        
        System.out.println("💬 Dos Guardias del Eco custodian la entrada con mirada vigilante.");
        System.out.println("💬 Uno de ellos te reconoce: '¡Alto! Esta área está restringida, Réplica.'");
        System.out.println("💬 El otro añade: 'Solo el Consejo y los Archivistas Superiores tienen acceso.'\n");

        System.out.println("Presionas el diario de Alaric contra tu pecho... debes encontrar la verdad.\n");

        if (!archivosInvestigados) {
            elegirMetodoInfiltracion();
        } else {
            System.out.println("Ya has investigado los archivos. No sería prudente regresar ahora.");
        }
    }

    private void elegirMetodoInfiltracion() {
        System.out.println("¿Cómo procedes?");
        
        if (jugador instanceof EcoPicaro) {
            System.out.println("1. 🗡️  Usar el sigilo (Destreza) - Deslizarte entre sombras");
            System.out.println("2. 💬 Intentar engañar a los guardias (Persuasión)");
        } else if (jugador instanceof EcoSabio) {
            System.out.println("1. 🔮 Usar magia de ilusión (Inteligencia) - Manipular sus mentes");
            System.out.println("2. 📜 Mostrar una autorización falsa (Astucia)");
        } else if (jugador instanceof EcoGuerrero) {
            System.out.println("1. ⚔️  Enfrentamiento directo (Fuerza) - Abrirte paso por la fuerza");
            System.out.println("2. 🛡️  Intimidar a los guardias (Voluntad)");
        }
        
        System.out.println("3. 🔍 Buscar una entrada alternativa (Percepción)");
        System.out.print("Elige una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1 -> iniciarRutaClase();
            case 2 -> rutaAlternativa();
            case 3 -> rutaPercepcion();
            default -> {
                System.out.println("💬 Los guardias se impacientan: 'Decídete o aléjate.'");
                elegirMetodoInfiltracion();
            }
        }
    }

    private void iniciarRutaClase() {
        if (jugador instanceof EcoPicaro) {
            rutaSigilo();
        } else if (jugador instanceof EcoSabio) {
            rutaMagica();
        } else if (jugador instanceof EcoGuerrero) {
            rutaConfrontacion();
        }
    }

    private void rutaSigilo() {
        System.out.println("\n🗡️  Ruta del Pícaro — Sigilo y astucia");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Te deslizas entre las sombras como una brisa...");
        System.out.println("Los ecos de tus pasos se funden con el susurro del viento en los pasillos.");
        System.out.println("💬 Piensas: 'Alaric me enseñó estos pasadizos... ahora los uso para investigarlo.'\n");

        System.out.println("Realizas una tirada de destreza para colarte sin ser visto...");
        System.out.println("Presiona ENTER para intentar el sigilo...");
        sc.nextLine();

        int exito = (int) (Math.random() * 100);
        int bonusDestreza = jugador.getDestreza() * 3;
        int total = exito + bonusDestreza;

        System.out.println("🎯 Tirada de sigilo: " + exito + " + " + bonusDestreza + " (destreza) = " + total + "/100");

        if (total > 60) {
            System.out.println("\n✅ ¡Éxito! Te mueves como un espectro entre los guardias.");
            System.out.println("💬 Uno de los guardias bosteza: 'Juré haber visto algo moverse...'");
            System.out.println("💬 El otro responde: 'Son solo los ecos del pasado, colega.'\n");
            
            System.out.println("La cerradura cede ante tus habilidades y entras en los Archivos Sellados...");
            descubrirInformacion("sigilo");
            jugador.cambiarReputacion(+10);
        } else {
            System.out.println("\n❌ Tu pie golpea accidentalmente una armadura decorativa.");
            System.out.println("💬 ¡CLANG! El sonido resuena en el silencioso pasillo.");
            System.out.println("💬 Los guardias se alertan inmediatamente: '¡Intruso en los archivos!'\n");
            
            enfrentarConsecuencias("El sigilo falló");
        }
    }

    private void rutaMagica() {
        System.out.println("\n🔮 Ruta del Sabio — Manipulación arcana");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Cierras los ojos y concentras la energía mnémica...");
        System.out.println("💬 Susurras: 'Como Alaric me enseñó... los ecos obedecen a quien los comprende.'\n");

        System.out.println("Tejes un velo de ilusión alrededor de los guardias...");
        System.out.println("Presiona ENTER para lanzar el hechizo...");
        sc.nextLine();

        int exito = (int) (Math.random() * 100);
        int bonusInteligencia = jugador.getInteligencia() * 3;
        int total = exito + bonusInteligencia;

        System.out.println("🎯 Tirada de ilusión: " + exito + " + " + bonusInteligencia + " (inteligencia) = " + total + "/100");

        if (total > 70) {
            System.out.println("\n✨ ¡El hechizo funciona perfectamente!");
            System.out.println("💬 Los guardias miran a través de ti como si fueras parte del muro.");
            System.out.println("💬 Uno comenta: 'Qué extraño... sentí como si alguien importante pasara.'\n");
            
            System.out.println("Las puertas se abren solas ante tu presencia, reconociendo tu esencia arcana...");
            descubrirInformacion("magia");
            jugador.cambiarReputacion(+15);
        } else {
            System.out.println("\n💥 La ilusión se desvanece demasiado pronto.");
            System.out.println("💬 Los guardias se sacuden la confusión: '¡Hechicería prohibida!'");
            System.out.println("💬 '¡Ningún miembro del Consejo usaría magia así!', grita uno.\n");
            
            enfrentarConsecuencias("La magia falló");
        }
    }

    private void rutaConfrontacion() {
        System.out.println("\n⚔️  Ruta del Guerrero — Fuerza bruta");
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Tu mano se cierra alrededor del arma...");
        System.out.println("💬 Piensas: 'A veces la verdad necesita ser tomada por la fuerza.'\n");

        System.out.println("💬 Advierte a los guardias: 'Apártense. Lo que busco es más importante que sus órdenes.'");
        System.out.println("💬 Los guardias desenvainan: '¡Esta es tu última advertencia, Réplica!'\n");

        System.out.println("Presiona ENTER para comenzar el enfrentamiento...");
        sc.nextLine();

        GuardiaEco guardia = new GuardiaEco();
        System.out.println("🌊 ¡" + guardia.getNombre() + " se prepara para el combate!");
        new Batalla().iniciarCombate(jugador, guardia);

        if (jugador.estaVivo()) {
            System.out.println("\n💪 Derrotas a los guardias con determinación.");
            System.out.println("💬 Jadeas: 'Lo siento, pero la verdad no puede esperar.'");
            System.out.println("La puerta cede ante tu fuerza, las maderas crujen y se parten...");
            descubrirInformacion("fuerza");
            jugador.cambiarReputacion(-10); // Fuerza bruta es mal vista
        }
    }

    private void rutaAlternativa() {
        System.out.println("\n💬 Intentas convencer a los guardias...");
        System.out.println("💬 'Alaric me envió. Necesito revisar unos documentos urgentes.'");

        int persuasión = (int) (Math.random() * 100) + jugador.getVoluntad() * 2;
        System.out.println("🎯 Tirada de persuasión: " + persuasión + "/100");

        if (persuasión > 50) {
            System.out.println("\n✅ Los guardias dudan pero finalmente asienten.");
            System.out.println("💬 'Está bien... pero rápido. Y no toques los archivos del Abismo.'");
            System.out.println("Su mención del Abismo confirma que estás en el camino correcto...");
            descubrirInformacion("persuasión");
            jugador.cambiarReputacion(+5);
        } else {
            System.out.println("\n❌ Los guardias no se dejan convencer.");
            System.out.println("💬 'Sin la autorización del Consejo, no podemos dejarte pasar.'");
            enfrentarConsecuencias("La persuasión falló");
        }
    }

    private void rutaPercepcion() {
        System.out.println("\n🔍 Buscas una entrada alternativa...");
        System.out.println("Tus ojos escrutan cada detalle de la arquitectura.");

        int percepcion = (int) (Math.random() * 100) + (jugador.getDestreza() + jugador.getInteligencia());
        System.out.println("🎯 Tirada de percepción: " + percepcion + "/120");

        if (percepcion > 70) {
            System.out.println("\n✅ ¡Encuentras una rejilla de ventilación oculta!");
            System.out.println("💬 'Los arquitectos siempre dejan rutas de escape...', recuerdas.");
            System.out.println("Te deslizas por los conductos hasta llegar a los archivos...");
            descubrirInformacion("percepción");
            jugador.cambiarReputacion(+8);
        } else {
            System.out.println("\n❌ No encuentras ninguna entrada alternativa.");
            System.out.println("💬 Los guardias notan tu comportamiento sospechoso.");
            enfrentarConsecuencias("La búsqueda falló");
        }
    }

    private void descubrirInformacion(String metodo) {
        System.out.println("\n" + "📜".repeat(50));
        System.out.println("           💎 DESCUBRIMIENTO EN LOS ARCHIVOS");
        System.out.println("📜".repeat(50));
        
        System.out.println("\nEntre polvorientos estantes y tomas prohibidas, encuentras:");

        switch (metodo) {
            case "sigilo" -> {
                System.out.println("🔎 **Informe clasificado: 'Proyecto Épsilon'**");
                System.out.println("💬 Fragmento: '...las Réplicas muestran autonomía imprevista. Alaric advierte sobre consecuencias éticas...'");
                System.out.println("💬 '...el Abismo no fue sellado, fue contenido. Y se está debilitando.'");
            }
            case "magia" -> {
                System.out.println("🔎 **Tomo arcano: 'Manipulación Mnémica Avanzada'**");
                System.out.println("💬 Anotación de Alaric: 'El Consejo borró recuerdos enteros. ¿Qué más han alterado?'");
                System.out.println("💬 'Los Olvidados no son disidentes... son víctimas.'");
            }
            case "fuerza" -> {
                System.out.println("🔎 **Diario fragmentado de Alaric**");
                System.out.println("💬 'Hoy creé la Réplica más estable hasta ahora. Temo que el Consejo la use como arma.'");
                System.out.println("💬 'Si lees esto, busca a Elara. Ella sabe la verdad sobre mi desaparición.'");
            }
            default -> {
                System.out.println("🔎 **Documentos varios sobre el Abismo Olvidado**");
                System.out.println("💬 'Incidente del Abismo: versión oficial vs. testimonios suprimidos'");
                System.out.println("💬 'Alaric fue el único sobreviviente... y su testimonio fue alterado.'");
            }
        }

        System.out.println("\n💡 **Has obtenido información crucial sobre:**");
        System.out.println("   - El Proyecto Épsilon y su verdadero propósito");
        System.out.println("   - La manipulación de recuerdos por el Consejo");
        System.out.println("   - La próxima pista: buscar a Elara");
        
        archivosInvestigados = true;
        jugador.cambiarReputacion(+20); // Por obtener información importante
        
        System.out.println("\nPresiona ENTER para salir sigilosamente de los archivos...");
        sc.nextLine();
    }

    private void enfrentarConsecuencias(String razon) {
        System.out.println("🚨 CONSECUENCIAS POR FALLA: " + razon);
        System.out.println("──────────────────────────────────────────────");
        
        GuardiaEco guardia = new GuardiaEco();
        System.out.println("🌊 " + guardia.getNombre() + " te confronta: '¡Estás cometiendo traición!'");
        
        new Batalla().iniciarCombate(jugador, guardia);
        
        if (jugador.estaVivo()) {
            System.out.println("\n💬 Logras escapar, pero la Academia estará alerta.");
            jugador.cambiarReputacion(-15);
        }
    }

    public boolean isArchivosInvestigados() {
        return archivosInvestigados;
    }
}