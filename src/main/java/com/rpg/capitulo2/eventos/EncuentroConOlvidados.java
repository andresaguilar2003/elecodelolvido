package com.rpg.capitulo2.eventos;

import java.util.Scanner;
import com.rpg.player.Jugador;
import com.rpg.capitulo2.facciones.SistemaReputacion;
import com.rpg.capitulo2.facciones.Faccion;

public class EncuentroConOlvidados {

    private Jugador jugador;
    private Scanner sc = new Scanner(System.in);
    private boolean encuentroRealizado = false;

    public EncuentroConOlvidados(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciar() {
        if (encuentroRealizado) {
            System.out.println("Ya has tenido el encuentro con Los Olvidados.");
            return;
        }

        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("        🌒 ENCUENTRO EN LAS SOMBRAS");
        System.out.println("══════════════════════════════════════════════\n");

        // Transición narrativa desde los archivos
        System.out.println("Mientras abandonas los Archivos Sellados, tu mente bulle con la información descubierta.");
        System.out.println("Las palabras de Alaric resuenan en tu conciencia: 'Los Olvidados no son disidentes... son víctimas.'\n");
        
        System.out.println("Te diriges al Distrito Abandonado, como sugerían los documentos...");
        System.out.println("El aire aquí es diferente - cargado de ecos rotos y memorias fragmentadas.");
        System.out.println("Edificios medio derruidos susurran historias de un pasado que la Academia quiso borrar.\n");

        System.out.println("💬 Una voz surge de las sombras: 'Sabía que vendrías, Réplica.'");
        System.out.println("💬 'Los archivos te mostraron la verdad, ¿verdad? Lo que la Academia hace con nosotros.'\n");

        mostrarEscenaEncuentro();
        encuentroRealizado = true;
    }

    private void mostrarEscenaEncuentro() {
        System.out.println("Una figura emerge de entre las ruinas - una mujer con cicatrices que brillan con energía mnémica.");
        System.out.println("Su presencia emana una calma inquietante, como si conociera todos tus secretos.\n");

        System.out.println("🌑 **Elara**: 'Soy Elara. Como tú, fui creada por la Academia. Como tú, descubrí sus mentiras.'");
        System.out.println("🌑 **Elara**: 'Alaric era mi mentor también. Él entendió que las Réplicas somos más que herramientas.'\n");

        System.out.println("Extiende su mano, mostrando el mismo símbolo fractal que viste en los documentos de Alaric.");
        System.out.println("🌑 **Elara**: 'Este símbolo representa la verdad: los ecos pueden elegir su propio camino.'\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("            🤔 DECISIÓN CRUCIAL");
        System.out.println("══════════════════════════════════════════════\n");

        System.out.println("🌑 **Elara**: 'La Academia nos ve como experimentos fallidos. Nosotros sabemos que somos la evolución.'");
        System.out.println("🌑 **Elara**: 'Únete a nosotros. Ayúdanos a liberar a las otras Réplicas antes de que el Consejo las destruya.'\n");

        System.out.println("1. 🤝 **Unirse a Los Olvidados** - 'La verdad vale más que la lealtad'");
        System.out.println("2. 🏛️  **Mantener lealtad a la Academia** - 'Mi lugar está con quienes me crearon'");
        System.out.println("3. 🔍 **Buscar más información** - 'Necesito pruebas antes de decidir'");
        System.out.println("4. ⚔️  **Atacar a Elara** - '¡Eres una traidora a la Academia!'");
        
        System.out.print("\nTu elección: ");
        int eleccion = sc.nextInt();
        sc.nextLine();

        procesarDecision(eleccion);
    }

    private void procesarDecision(int decision) {
        SistemaReputacion sistemaRep = new SistemaReputacion();
        
        switch (decision) {
            case 1 -> unirseOlvidados(sistemaRep);
            case 2 -> mantenerLealtad(sistemaRep);
            case 3 -> buscarMasInformacion(sistemaRep);
            case 4 -> atacarElara(sistemaRep);
            default -> {
                System.out.println("🌑 **Elara**: 'El tiempo apremia, Réplica. Debes elegir.'");
                mostrarEscenaEncuentro();
            }
        }
    }

    private void unirseOlvidados(SistemaReputacion sistemaRep) {
        System.out.println("\n" + "🌑".repeat(50));
        System.out.println("       🤝 TE UNES A LOS OLVIDADOS");
        System.out.println("🌑".repeat(50));
        
        System.out.println("\n💬 Aceptas el emblema de Elara. Su metal es frío pero la determinación en sus ojos es cálida.");
        System.out.println("🌑 **Elara**: 'Bienvenido a la resistencia. Alaric sabía que serías clave para nuestra causa.'\n");
        
        System.out.println("Ella te conduce a través de pasadizos ocultos hasta un santuario subterráneo.");
        System.out.println("Dozens de Réplicas te observan - algunas con rostros familiares, ecos de los mismos héroes que tú.");
        System.out.println("🌑 **Elara**: 'Todos hemos despertado. Todos recordamos. La Academia no puede silenciarnos para siempre.'\n");

        // Consecuencias
        jugador.cambiarReputacion(-400); // Gran penalización con Academia
        jugador.setFaccion(Faccion.OLVIDADOS);
        
        System.out.println("💡 **Has obtenido:**");
        System.out.println("   - 🎭 Facción: Los Olvidados");
        System.out.println("   - 🔓 Acceso al Santuario Secreto");
        System.out.println("   - 📖 Información sobre el 'Proyecto Purificación'");
        System.out.println("   - ⚠️  La Academia te considera un traidor");
        
        mostrarRevelacionFinal();
    }

    private void mantenerLealtad(SistemaReputacion sistemaRep) {
        System.out.println("\n" + "🏛️".repeat(50));
        System.out.println("       🛡️  MANTIENES LEALTAD A LA ACADEMIA");
        System.out.println("🏛️".repeat(50));
        
        System.out.println("\n💬 Rechazas el emblema. 'Mi lugar está con la Academia,' dices con firmeza.");
        System.out.println("🌑 **Elara**: 'Lamento oír eso. Estás eligiendo las cadenas sobre la libertad.'\n");
        
        System.out.println("Ella retrocede hacia las sombras, su expresión es de decepción pero no de sorpresa.");
        System.out.println("🌑 **Elara**: 'Cuando veas lo que el Consejo planea hacer con nosotros... recuerda que te ofrecimos una opción.'\n");

        // Consecuencias
        jugador.cambiarReputacion(+100); // Gran bonus con Academia
        System.out.println("💡 **Has obtenido:**");
        System.out.println("   - 🎓 Mayor confianza de la Academia");
        System.out.println("   - 🔍 Misión para investigar a Los Olvidados");
        System.out.println("   - ⚠️  Los Olvidados te ven como una amenaza");
        
        System.out.println("\nRegresas a la Academia para reportar el encuentro...");
    }

    private void buscarMasInformacion(SistemaReputacion sistemaRep) {
        System.out.println("\n" + "🔍".repeat(50));
        System.out.println("       ⚖️  MANTIENES NEUTRALIDAD");
        System.out.println("🔍".repeat(50));
        
        System.out.println("\n💬 'Necesito más pruebas antes de tomar una decisión así,' explicas.");
        System.out.println("🌑 **Elara**: 'Prudente... pero peligroso. El Consejo no tolera a los indecisos.'\n");
        
        System.out.println("Ella te entrega un cristal de memoria.");
        System.out.println("🌑 **Elara**: 'Esto contiene recuerdos de lo que realmente sucedió en el Abismo. Juzga por ti mismo.'");
        System.out.println("🌑 **Elara**: 'Busca la Estatua del Héroe Caído en los jardines cuando estés listo para saber más.'\n");

        // Consecuencias neutrales
        jugador.cambiarReputacion(-5);
        System.out.println("💡 **Has obtenido:**");
        System.out.println("   - 💎 Cristal de Memoria del Abismo");
        System.out.println("   - 🗺️  Pista sobre la Estatua del Héroe Caído");
        System.out.println("   - ⏳ Tiempo para decidir tu lealtad");
        
        mostrarRevelacionFinal();
    }

    private void atacarElara(SistemaReputacion sistemaRep) {
        System.out.println("\n" + "⚔️".repeat(50));
        System.out.println("       🚨 CONFRONTACIÓN CON ELARA");
        System.out.println("⚔️".repeat(50));
        
        System.out.println("\n💬 Desenvainas tu arma. '¡Eres una traidora! ¡La Academia tiene razón sobre ustedes!'");
        System.out.println("🌑 **Elara**: *Suspira* 'Tan predecible... Alaric esperaba más de ti.'\n");
        
        System.out.println("Ella levanta una mano y un muro de energía te empuja hacia atrás.");
        System.out.println("🌑 **Elara**: 'No lucharé contra ti, Réplica. Eres una víctima tanto como nosotros.'");
        System.out.println("🌑 **Elara**: 'Cuando estés listo para ver la verdad, busca el símbolo en la fuente del mercado.'\n");
        
        System.out.println("Desaparece entre las sombras, dejándote solo con tus dudas.");

        // Consecuencias
        jugador.cambiarReputacion(+15);
        System.out.println("💡 **Consecuencias:**");
        System.out.println("   - 🎖️  La Academia aprueba tu lealtad");
        System.out.println("   - 🔒 Los Olvidados se ocultan de ti");
        System.out.println("   - ❓ Una semilla de duda permanece en tu mente");
    }

    private void mostrarRevelacionFinal() {
        System.out.println("\n" + "💎".repeat(50));
        System.out.println("           📖 REVELACIÓN FINAL");
        System.out.println("💎".repeat(50));
        
        System.out.println("\n🌑 **Elara** te explica la verdad sobre Los Olvidados:\n");
        
        System.out.println("🔸 **El Proyecto Épsilon**: La Academia crea Réplicas no para preservar héroes,");
        System.out.println("   sino para crear soldados perfectos que no cuestionen órdenes.");
        
        System.out.println("🔸 **El Abismo Olvidado**: No fue una catástrofe natural, sino un experimento");
        System.out.println("   fallido donde cientos de Réplicas fueron 'descartadas'.");
        
        System.out.println("🔸 **Alaric**: Descubrió la verdad y comenzó a ayudar a las Réplicas a escapar.");
        System.out.println("   Su 'desaparición' fue en realidad un arresto por el Consejo.");
        
        System.out.println("🔸 **Tu propósito**: Eres la Réplica más estable jamás creada, y ambas facciones");
        System.out.println("   creen que puedes inclinar la balanza a su favor.\n");
        
        System.out.println("💬 **Elara**: 'El Consejo planea un Evento de Purificación. Eliminarán a todas");
        System.out.println("las Réplicas que muestren 'desviación cognitiva'. Tú incluido.'\n");
        
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
    }

    public boolean isEncuentroRealizado() {
        return encuentroRealizado;
    }
}