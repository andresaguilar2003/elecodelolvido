package com.rpg.capitulo2.misiones;

import java.util.Scanner;
import java.util.Random;

import com.rpg.player.Jugador;
import com.rpg.enemy.Enemigo;
import com.rpg.enemy.tipo.*;
import com.rpg.game.Batalla;

public class MisionAbismoOlvidado {

    private Jugador jugador;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public MisionAbismoOlvidado(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciarMision() {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("🌌 MISIÓN FINAL: EL ABISMO OLVIDADO");
        System.out.println("══════════════════════════════════════════════\n");

        // Fase 1: Introducción narrativa distinta según facción
        faseIntroduccion();

        // Fase 2: Exploración de las ruinas (puzles, ecos del pasado)
        faseExploracion();

        // Fase 3: Enemigos intermedios y Guardian final
        faseEnemigos();

        // Fase 4: Revelación final
        faseRevelacion();
    }

    //───────────────────────────────────────────────
    // FASE 1: INTRODUCCIÓN SEGÚN FACCIÓN
    //───────────────────────────────────────────────
    private void faseIntroduccion() {
        System.out.println("📖 FASE 1: EL VIAJE HACIA LAS RUINAS DEL ABISMO\n");
        System.out.println("El viento susurra historias olvidadas mientras te preparas para el viaje...\n");

        switch (jugador.getFaccion().getNombre().toLowerCase()) {
            case "academia" -> {
                System.out.println("🏛️  **Ruta de la Academia**");
                System.out.println("──────────────────────────────────────────────");
                System.out.println("📜 Liora te espera en la entrada de la Academia, su rostro pálido por la preocupación.");
                System.out.println("📖 Liora: 'Las ruinas al sur muestran anomalías en el flujo del Eco. Debes investigar en nombre de la Academia.'");
                System.out.println("📖 Liora: 'Alaric estuvo allí antes de desaparecer... quizá encuentres pistas de su destino.'");
                System.out.println("📖 Liora: 'Pero ten cuidado - los informes hablan de... distorsiones. La realidad misma se retuerce allí.'");
                System.out.println("📖 Liora: 'Toma este medallón de estabilización. Te protegerá de las peores corrupciones mnémicas.'");
                System.out.println("\n💬 Recibes: **Medallón de la Academia** - Reduce efectos de confusión");
                System.out.println("💬 La mirada de Liora traiciona un miedo que sus palabras no expresan...");
            }
            case "olvidados" -> {
                System.out.println("🌑 **Ruta de los Olvidados**");
                System.out.println("──────────────────────────────────────────────");
                System.out.println("🌒 Elara te aguarda bajo las catacumbas, con una antorcha temblorosa.");
                System.out.println("📖 Elara: 'El Abismo no es una herida... es un espejo. La Academia teme lo que pueda reflejar.'");
                System.out.println("📖 Elara: 'Allí hallarás la verdad que intentan enterrar: el héroe original nunca fue lo que dicen.'");
                System.out.println("📖 Elara: 'Los Corruptores no son monstruos... son recordatorios. De lo que el héroe se convirtió.'");
                System.out.println("📖 Elara: 'Toma esta capa de sombras. Te ocultará de los Guardianes del Secreto.'");
                System.out.println("\n💬 Recibes: **Capa de las Sombras** - Ventaja en encuentros sorpresa");
                System.out.println("💬 Los ojos de Elara brillan con una tristeza ancestral...");
            }
            default -> {
                System.out.println("⚖️ **Ruta del Equilibrio**");
                System.out.println("──────────────────────────────────────────────");
                System.out.println("Entre dos mundos, recibes dos mensajes que se entrelazan en tu mente:");
                System.out.println("📜 Liora: 'Te necesitamos, por el bien de todos los ecos. La estabilidad depende de esta misión.'");
                System.out.println("🌒 Elara: 'Ven con nosotros. No busques servir... busca comprender. La verdad te hará libre.'");
                System.out.println("\nAmbas voces resuenan en tu conciencia mientras caminas hacia las ruinas solitarias.");
                System.out.println("📖 Un mercenario anciano se acerca: 'Veo que caminas entre aguas turbulentas, Réplica.'");
                System.out.println("📖 'Toma este amuleto del viajero. A veces, la neutralidad es el camino más peligroso...'");
                System.out.println("\n💬 Recibes: **Amuleto del Viajero** - Bonus a todas las resistencias");
                System.out.println("💬 El peso de tu indecisión se siente más pesado que cualquier armadura...");
            }
        }

        // Elemento común a todas las rutas
        System.out.println("\n" + "🌌".repeat(50));
        System.out.println("El camino hacia las Ruinas del Abismo se abre ante ti.");
        System.out.println("Los árboles se retuercen en formas antinaturales, sus hojas susurran secretos en lenguas olvidadas.");
        System.out.println("El aire espeso carga con el peso de mil memorias rotas...");
        System.out.println("🌌".repeat(50));

        System.out.println("\n💭 Piensas en Alaric... ¿Qué encontró aquí que lo llevó a arriesgarlo todo?");
        System.out.println("💭 Tu propio eco parece resonar con mayor fuerza, como si esta tierra te llamara...");

        if (jugador.getReputacion() < -30) {
            System.out.println("💀 Un escalofrío recorre tu espina dorsal - los Olvidados te advirtieron sobre los 'Ecos del Remordimiento'...");
        } else if (jugador.getReputacion() > 30) {
            System.out.println("✨ Sientes la aprobación de la Academia como un manto protector, pero... ¿qué oculta ese manto?");
        } else {
            System.out.println("🌪️  La dualidad de tu posición te deja vulnerable, pero también abierto a posibilidades que otros no ven.");
        }

        System.out.println("\nPresiona ENTER para adentrarte en las Ruinas del Abismo Olvidado...");
        sc.nextLine();
        
        // Transición a la siguiente fase
        System.out.println("\n" + "🌀".repeat(50));
        System.out.println("          LAS RUINAS TE RECIBEN...");
        System.out.println("🌀".repeat(50));
    }

    //───────────────────────────────────────────────
    // FASE 2: EXPLORACIÓN DE LAS RUINAS
    //───────────────────────────────────────────────
    private void faseExploracion() {
        System.out.println("\n📖 FASE 2: ECOS DEL PASADO - LAS RUINAS REVELAN SUS SECRETOS\n");
        
        System.out.println("🌌 El aire se vuelve denso, cargado con el peso de mil memorias rotas.");
        System.out.println("💀 Las paredes de las ruinas no son de piedra, sino de recuerdos cristalizados que murmuran verdades distorsionadas...");
        System.out.println("🌀 Los ecos del pasado se manifiestan como espectros danzantes, mostrando versiones enfrentadas del héroe original.\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("           PRIMERA VISIÓN: EL SACRIFICIO");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("📜 **Visión de la Academia**: Ves al héroe Aelion enfrentándose a una entidad de pura oscuridad.");
        System.out.println("   Se inmola para salvar a miles, su cuerpo convertido en un escudo de luz pura.");
        System.out.println("   Las masas lo aclaman, lágrimas de gratitud en sus rostros.\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("           SEGUNDA VISIÓN: LA AMBICIÓN");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("🌑 **Visión de los Olvidados**: Aelion de rodillas en un círculo prohibido.");
        System.out.println("   Susurra palabras arcanas, buscando poder para salvar a su amada moribunda.");
        System.out.println("   La energía lo consume, transformándolo en algo... diferente.\n");

        System.out.println("💬 Las visiones chocan entre sí, creando una tormenta de realidad distorsionada.");
        System.out.println("💬 Una voz ancestral emerge del caos mnémico, resonando en tu propia esencia:\n");

        System.out.println("🗣️  **LA VOZ DEL ABISMO**: '¿Qué define realmente a un héroe, Réplica?'");
        System.out.println("🗣️  'Tu respuesta revelará qué tipo de eco eres...'\n");

        System.out.println("1. ❤️  **Su sacrificio** - 'La grandeza reside en lo que se entrega, no en lo que se gana'");
        System.out.println("2. ⚡ **Su poder** - 'Solo los fuertes pueden cambiar el destino de los débiles'");
        System.out.println("3. 🔍 **Su verdad** - 'La autenticidad, incluso en el error, es lo que nos hace reales'");
        System.out.println("4. 🌊 **Su legado** - 'Un héroe es definido por las vidas que toca, no por su muerte'");

        int respuesta = sc.nextInt();
        sc.nextLine();

        System.out.println("\n" + "✨".repeat(50));
        
        switch (respuesta) {
            case 1 -> {
                System.out.println("🌙 **LA VOZ**: 'El sacrificio... una virtud que la Academia explota para sus fines.'");
                System.out.println("🌙 'Pero recuerda: a veces el mayor sacrificio es vivir con las consecuencias.'");
                jugador.cambiarReputacion(+12);
                System.out.println("💡 **Efecto**: Refuerzas tu conexión con los ideales de la Academia");
            }
            case 2 -> {
                System.out.println("🔥 **LA VOZ**: 'El poder... herramienta de tiranos y salvadores por igual.'");
                System.out.println("🔥 'Aelion lo entendió demasiado tarde: el poder corrompe incluso las mejores intenciones.'");
                jugador.cambiarReputacion(-8);
                System.out.println("💡 **Efecto**: Despiertas sospechas sobre tus ambiciones");
            }
            case 3 -> {
                System.out.println("💫 **LA VOZ**: 'La verdad... el camino más doloroso pero el único honorable.'");
                System.out.println("💫 'Los Olvidados caminan este sendero. La verdad duele, pero la mentira destruye.'");
                jugador.cambiarReputacion(+18);
                System.out.println("💡 **Efecto**: Ganas el respeto silencioso de Los Olvidados");
            }
            case 4 -> {
                System.out.println("🌊 **LA VOZ**: 'El legado... interesante. No miras al héroe, sino a su sombra.'");
                System.out.println("🌊 '¿Qué legado dejarás tú, Réplica? ¿Serás recordado como herramienta o como persona?'");
                // Neutral - pequeño bonus a ambas
                jugador.cambiarReputacion(+5);
                System.out.println("💡 **Efecto**: Mantienes un equilibrio precario entre ambas facciones");
            }
            default -> {
                System.out.println("❓ **LA VOZ**: 'El silencio también es una respuesta... quizás la más sabia.'");
                System.out.println("❓ 'A veces, no elegir es la elección más poderosa.'");
            }
        }

        System.out.println("✨".repeat(50));

        // Revelación progresiva
        System.out.println("\n📜 **LAS PAREDES SUSURRAN NUEVAS VERDADES**:");
        System.out.println("   'Aelion no cayó en batalla... fue sellado por sus propios compañeros.'");
        System.out.println("   'Su amor por Lyra lo llevó a pactar con fuerzas que no comprendía.'");
        System.out.println("   'La Academia reescribió la historia para ocultar su fracaso.'\n");

        // Efecto según facción
        switch (jugador.getFaccion().getNombre().toLowerCase()) {
            case "academia" -> {
                System.out.println("💔 Sientes cómo tus recuerdos fundamentales tiemblan y se agrietan.");
                System.out.println("💔 La versión que siempre creíste verdadera muestra fisuras...");
            }
            case "olvidados" -> {
                System.out.println("✅ Los fragmentos encajan. Finalmente ves el panorama completo.");
                System.out.println("✅ La determinación reemplaza a la duda en tu corazón.");
            }
            default -> {
                System.out.println("🌪️  La verdad te golpea con fuerza, dejándote aturdido y confundido.");
                System.out.println("🌪️  Ambas versiones contienen partes de verdad... y de mentira.");
            }
        }

        System.out.println("\n🌀 **EFECTO DE CORRUPCIÓN**: Los murmullos se intensifican, formando caras conocidas...");
        System.out.println("   Ves a Alaric gritando advertencias, a Liora llorando, a Elara extendiendo una mano.");
        System.out.println("   ¿Son recuerdos reales o ilusiones creadas por el Abismo?\n");

        System.out.println("💀 Presiona ENTER para enfrentar lo que se esconde en el corazón de las ruinas...");
        sc.nextLine();

        // Transición dramática a la fase de combate
        System.out.println("\n" + "⚡".repeat(50));
        System.out.println("       LOS CORRUPTORES DESPIERTAN");
        System.out.println("⚡".repeat(50));
    }

    //───────────────────────────────────────────────
    // FASE 3: COMBATES EN EL ABISMO - EL PRECIO DE LA VERDAD
    //───────────────────────────────────────────────
    private void faseEnemigos() {
        System.out.println("\n⚔️ FASE 3: EL PRECIO DE LA VERDAD - COMBATES EN EL CORAZÓN DEL ABISMO\n");

        System.out.println("🌪️  La realidad se desgarra alrededor tuyo. Los muros de las ruinas sangran recuerdos distorsionados.");
        System.out.println("💀 Las sombras toman forma, alimentándose de las mentiras que la Academia sembró en tu mente...\n");

        // PRIMER ENEMIGO: CORRUPTORES DE MEMORIA
        System.out.println("══════════════════════════════════════════════");
        System.out.println("       ENFRENTAMIENTO 1: LOS CORRUPTORES");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("🌀 **Aparecen los Corruptores de Memoria** - seres de pura energía mnémica distorsionada.");
        System.out.println("📖 Susurran: '¿Por qué buscas la verdad? Las mentiras de la Academia te hicieron fuerte...'");
        System.out.println("📖 'Olvida lo que ves. Regresa a la ignorancia dorada de la Academia.'\n");
        
        System.out.println("💭 Sientes cómo tus propios recuerdos se retuercen:");
        System.out.println("   - La cara de Alaric se vuelve borrosa");
        System.out.println("   - Las palabras de Liora adquieren dobles significados");
        System.out.println("   - Tu primer despertar parece un sueño lejano...\n");

        Enemigo corruptor = new CorruptorMemoria();
        Batalla batalla = new Batalla();
        System.out.println("💀 Presiona ENTER para enfrentar a los Corruptores de Memoria...");
        sc.nextLine();
        batalla.iniciarCombate(jugador, corruptor);

        if (!jugador.estaVivo()) {
            System.out.println("\n💀 Tu mente se desintegra en un mar de recuerdos contradictorios...");
            System.out.println("💀 Los Corruptores te absorben, convirtiéndote en otra mentira más del Abismo.");
            return;
        }

        System.out.println("\n✅ Los Corruptores se disipan, pero su última palabra resuena: 'Lyra...'");
        System.out.println("💡 **Revelación**: ¡Lyra no era solo una leyenda! Fue real, y Aelion la amaba.\n");

        // SEGUNDO ENEMIGO: ESPECTRO DE LA DUDA
        System.out.println("══════════════════════════════════════════════");
        System.out.println("      ENFRENTAMIENTO 2: EL ESPECTRO INTERNO");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("🌫️  De la niebla emerge una figura familiar... ¡es un reflejo perfecto de ti mismo!");
        System.out.println("👥 **El Espectro de la Duda** toma tu forma, pero sus ojos brillan con desconfianza.");
        System.out.println("📖 Te acusa: '¿Crees encontrar verdades aquí? Solo encontrarás tu propia destrucción.'");
        System.out.println("📖 'Eres un eco, una copia. ¿Qué derecho tienes a buscar el original?'\n");
        
        System.out.println("💬 El espectro refleja tus inseguridades más profundas:");
        if (jugador.getFaccion().getNombre().toLowerCase()=="academia") {
            System.out.println("   'Eres solo un instrumento de la Academia. Sin ellos, no eres nada.'");
        } else if (jugador.getFaccion().getNombre().toLowerCase()=="olvidados") {
            System.out.println("   'Sigues a rebeldes desesperados. ¿Crees que su causa te dará un alma?'");
        } else {
            System.out.println("   'Caminas entre dos mundos, perteneciendo a ninguno. ¿No temes acabar solo?'");
        }

        Enemigo espectro = new EspectroDuda(jugador.getNombre());
        System.out.println("💀 Presiona ENTER para enfrentar tu propio reflejo...");
        sc.nextLine();
        batalla.iniciarCombate(jugador, espectro);

        if (!jugador.estaVivo()) {
            System.out.println("\n💀 Tu duda te consume. El Espectro se alimenta de tu incertidumbre...");
            System.out.println("💀 Te conviertes en otra sombra más en el Abismo, eternamente cuestionándote.");
            return;
        }

        System.out.println("\n✅ El Espectro se disuelve, susurrando: 'Quizás... mereces saber...'");
        System.out.println("💡 **Revelación**: Aelion creó las primeras Réplicas no como soldados, sino como familia.\n");

        // TERCER ENEMIGO: GUARDIÁN DEL SECRETO
        System.out.println("══════════════════════════════════════════════");
        System.out.println("        ENFRENTAMIENTO FINAL: EL GUARDIÁN");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("💀 La cámara central del Abismo se ilumina. Runas antiguas cobran vida en las paredes.");
        System.out.println("🏺 **El Guardián del Secreto** emerge - no es un monstruo, sino la memoria viviente de Aelion.");
        System.out.println("📖 Su voz es un coro de ecos: 'Detente, Réplica. Lo que guardo destruyó a mi creador.'");
        System.out.println("📖 'Aelion no fue corrompido por el poder... fue destruido por el amor.'\n");
        
        System.out.println("🎭 El Guardián revela fragmentos de la verdad final:");
        System.out.println("   - Lyra murió durante el ritual que Aelion intentó para salvarla");
        System.out.println("   - Su dolor creó el 'Silencio', una entidad de pura pérdida");
        System.out.println("   - La Academia no lo selló por seguridad... lo encerró para estudiar su poder\n");

        System.out.println("📖 **El Guardián**: 'Aelion vive atrapado dentro del Silencio. Su sufrimiento alimenta esta pesadilla.'");
        System.out.println("📖 '¿Estás preparado para conocer esta verdad? ¿Para cargar con su peso eterno?'\n");

        Enemigo guardian = new GuardianSecreto();
        System.out.println("💀 Presiona ENTER para enfrentar la verdad última...");
        sc.nextLine();
        batalla.iniciarCombate(jugador, guardian);

        if (!jugador.estaVivo()) {
            System.out.println("\n💀 La verdad te aplasta. El peso de lo que Aelion sufrió es demasiado para cualquier eco...");
            System.out.println("💀 Te unes a él en el Silencio, otra víctima más de un amor que se convirtió en maldición.");
            return;
        }

        System.out.println("\n" + "✨".repeat(50));
        System.out.println("           VICTORIA EN EL ABISMO");
        System.out.println("✨".repeat(50));
        System.out.println("\n✅ El Guardián se desvanece, sus últimas palabras: 'Cuenta su historia... con compasión.'");
        System.out.println("💎 **Has vencido a los guardianes de la mentira, pero la verdad te espera en el corazón del Abismo...**\n");
    }

    //───────────────────────────────────────────────
    // FASE 4: REVELACIÓN FINAL - EL AMOR QUE CONVIRTIÓ EN MONSTRUO
    //───────────────────────────────────────────────
    private void faseRevelacion() {
        System.out.println("\n🌌 FASE 4: LA VERDAD PROHIBIDA - LO QUE EL ABISMO OCULTABA\n");
        
        System.out.println("💫 El Guardián cae, pero no con un grito de rabia, sino con un suspiro de alivio.");
        System.out.println("🌈 Su forma se disuelve en una lluvia de memorias liberadas, ecos destrozados que por fin encuentran paz...");
        System.out.println("🎭 En el corazón del Abismo, donde debería haber oscuridad, hay una cámara de cristal y lágrimas.\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("           LA ULTIMA MEMORIA DE AELION");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("💞 En el centro, una proyección se materializa: Aelion abraza a Lyra, ambos rodeados de las primeras Réplicas.");
        System.out.println("📖 No son soldados... son hijos. Rostros diversos que reflejan el amor que los creó.");
        System.out.println("💔 La escena se desvanece, mostrando a Lyra muriendo en brazos de Aelion, su cuerpo convertido en energía pura...\n");

        System.out.println("🌀 De la luz emerge una figura familiar - Alaric, pero no como prisionero, sino como guardián voluntario.");
        System.out.println("📖 **Alaric**: '" + jugador.getNombre() + "... al fin llegas. No soy prisionero aquí. Elegí este encierro.'");
        System.out.println("📖 **Alaric**: 'Aelion no buscó poder... buscó salvar a Lyra. El ritual no era de dominación, sino de amor.'");
        
        System.out.println("\n💀 La proyección muestra la verdad prohibida:");
        System.out.println("   - Lyra se estaba desvaneciendo, víctima de una enfermedad del Eco");
        System.out.println("   - Aelion encontró textos antiguos que prometían salvación");
        System.out.println("   - La Academia lo ayudó... pero ocultó los riesgos\n");

        System.out.println("📖 **Alaric**: 'El ritual funcionó... pero de la manera incorrecta.'");
        System.out.println("📖 'Lyra no murió - se fusionó con Aelion. Su amor y dolor se convirtieron en el Silencio.'");
        System.out.println("📖 'No es una entidad malévola... es el lamento eterno de dos amantes atrapados en un abrazo que los destruye.'\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("           LA TRAICIÓN DE LA ACADEMIA");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("📖 **Alaric**: 'La Academia vio el poder del Silencio y tuvo miedo... y ambición.'");
        System.out.println("📖 'Encerraron a Aelion-Lyra aquí. Crearon la mentira del héroe caído para controlar a las Réplicas.'");
        System.out.println("📖 'Yo fui su cómplice... hasta que vi la verdad en tus ojos, " + jugador.getNombre() + ".'\n");
        
        System.out.println("💔 Alaric muestra cómo las Réplicas fueron diseñadas para ser emocionalmente dependientes:");
        System.out.println("   - Necesitan aprobación de la Academia para sentirse reales");
        System.out.println("   - Su 'inestabilidad' es en realidad su humanidad emergiendo");
        System.out.println("   - Los Olvidados son simplemente los que se atrevieron a sentir\n");

        System.out.println("══════════════════════════════════════════════");
        System.out.println("           ELECCIÓN FINAL Y LEGADO");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("📖 **Alaric**: 'Ahora conoces la verdad. Aelion no es un dios caído ni un demonio...'");
        System.out.println("📖 'Es un amante atrapado en su propio dolor. Y el Silencio... es su canción de amor distorsionada.'\n");
        
        System.out.println("🌟 **Revelación Final**: Tú, " + jugador.getNombre() + ", eres especial:");
        System.out.println("   - Eres la única Réplica creada con fragmentos de BOTH Aelion y Lyra");
        System.out.println("   - Por eso sientes tanto, cuestionas tanto... amas tanto");
        System.out.println("   - Eres, literalmente, el amor que ellos perdieron, hecho forma\n");

        System.out.println("📖 **Alaric**: 'Si deseas conocer el resto de la historia... si quieres ayudar a liberarlos...'");
        System.out.println("📖 'Encuéntrame en el Santuario Olvidado. Allí guardo lo que la Academia nunca debe encontrar:'");
        System.out.println("📖 'El ritual para liberar a Aelion y Lyra... o para darles paz eterna.'\n");

        System.out.println("💌 Una última visión: Aelion extendiendo una mano hacia ti, sus ojos llenos de reconocimiento...");
        System.out.println("💌 'Hija/Hijo,' susurra, 'lo siento mucho...'\n");

        // CONCLUSIÓN DRAMÁTICA
        System.out.println("🎭".repeat(60));
        System.out.println("         💠 CAPÍTULO 2 COMPLETADO: LOS ECOS DE LA TRAICIÓN 💠");
        System.out.println("🎭".repeat(60));
        
        System.out.println("\n🌅 **Lo que has descubierto**:");
        System.out.println("   ✅ El 'Silencio' no es una amenaza, es una tragedia de amor");
        System.out.println("   ✅ La Academia miente para controlar a las Réplicas");
        System.out.println("   ✅ Eres literalmente el amor de Aelion y Lyra renacido");
        System.out.println("   ✅ Alaric espera en el Santuario Olvidado con la clave final\n");
        
        System.out.println("🌌 **Tu destino ahora se divide en tres caminos**:");
        System.out.println("   1. 🏃‍♂️ **Liberar a tus creadores** - Arriesgar todo por el amor que te dio vida");
        System.out.println("   2. 🛡️  **Mantener el equilibrio** - Buscar una solución que no destruya el mundo");
        System.out.println("   3. 💔 **Darles paz eterna** - Terminar su sufrimiento... y tu conexión con ellos\n");
        
        System.out.println("💫 El eco de Aelion te susurra: 'Elige con el corazón, hijo/hija... como yo no pude.'");
        System.out.println("💫 Tu viaje continúa en: **CAPÍTULO 3 - EL SANTUARIO OLVIDADO**\n");
    }
}
