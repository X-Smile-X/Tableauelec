package com.tableauelec.app.data

object CircuitRepository {

    val circuits: List<Circuit> = listOf(

        Circuit(
            id = "eclairage",
            nom = "Éclairage",
            type = CircuitType.ECLAIRAGE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC (ou A en pièce humide)",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V ou R02V dans le bâti",
            nombreMax = "8 points lumineux max par circuit (norme NF C 15-100)",
            pointsSchema = 4,
            couleurPhase = "Rouge ou marron",
            description = "Un circuit d'éclairage alimente les points lumineux (plafonniers, appliques, spots) et leurs organes de commande (interrupteurs, va-et-vient, télérupteurs). L'interrupteur coupe uniquement la phase, jamais le neutre. Le fil de terre alimente la carcasse métallique des luminaires le cas échéant.",
            pointsCles = listOf(
                "Le disjoncteur protège le câble, pas l'ampoule : jamais de fil plus fin que 1,5 mm² en aval d'un 16 A.",
                "En va-et-vient : 2 fils navette (souvent orange) entre les deux interrupteurs, en plus de la phase et du retour lampe.",
                "Le neutre n'est jamais coupé, seule la phase transite par l'interrupteur.",
                "Terre obligatoire sur tout point lumineux avec parties métalliques accessibles."
            ),
            normeRef = "NF C 15-100 §771.314 / §5.1"
        ),

        Circuit(
            id = "double_allumage",
            nom = "Double allumage (un point de commande, deux circuits)",
            type = CircuitType.ECLAIRAGE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm² par départ",
            typeCable = "U-1000 R2V",
            nombreMax = "8 points lumineux max par départ (comptés séparément)",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Un interrupteur double bascule (ou deux interrupteurs juxtaposés) commande deux groupes de points lumineux distincts depuis un seul emplacement mural, par exemple les spots et la suspension d'une même pièce, ou deux zones d'un même volume.",
            pointsCles = listOf(
                "Chaque bascule de l'interrupteur double coupe sa propre phase indépendamment ; les deux circuits ne doivent jamais être reliés entre eux.",
                "Chaque départ reste comptabilisé séparément dans la limite des 8 points lumineux par circuit.",
                "Le neutre et la terre peuvent être communs aux deux départs s'ils proviennent du même disjoncteur, mais chaque phase de commande reste dédiée.",
                "Ne pas confondre avec un simple interrupteur va-et-vient : ici il y a bien deux circuits électriques distincts pilotés depuis un même point."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "double_va_et_vient",
            nom = "Double va-et-vient (permutateur, 3 points de commande ou plus)",
            type = CircuitType.ECLAIRAGE_TELERUPTEUR,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "8 points lumineux max par circuit",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Quand un même point lumineux doit être commandé depuis 3 emplacements ou plus (grand couloir, pièce avec plusieurs accès), on ajoute un ou plusieurs permutateurs (croisillons à 4 bornes) entre les deux interrupteurs va-et-vient classiques.",
            pointsCles = listOf(
                "Les deux interrupteurs va-et-vient d'origine restent en bout de chaîne ; chaque permutateur supplémentaire s'insère entre les deux navettes.",
                "Un permutateur possède 4 bornes de navette (2 entrées, 2 sorties) et croise ou laisse passer le signal selon sa position.",
                "Au-delà de 3-4 points de commande, une solution par télérupteur piloté par boutons-poussoirs devient plus simple à câbler et à dépanner.",
                "Toujours reperer les fils navette (souvent orange) pour ne pas les confondre avec une phase permanente lors d'un dépannage."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "telerupteur_unipolaire",
            nom = "Télérupteur unipolaire",
            type = CircuitType.ECLAIRAGE_TELERUPTEUR,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "8 points lumineux max par circuit",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Le télérupteur unipolaire ne coupe que la phase : c'est le modèle standard utilisé pour l'éclairage domestique classique (cage d'escalier, couloir), suffisant dès lors que le neutre reste distribué en continu jusqu'aux points lumineux.",
            pointsCles = listOf(
                "Un seul pôle de puissance est coupé (la phase) ; le neutre traverse le télérupteur sans être interrompu.",
                "C'est le modèle le plus courant en habitation individuelle pour un usage standard.",
                "La bobine de commande (souvent 230V ou 12/24V selon modèle) est alimentée séparément du contact de puissance.",
                "Vérifier la tension de la bobine du télérupteur avant de câbler les boutons-poussoirs : certains modèles fonctionnent en TBTS."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "telerupteur_bipolaire",
            nom = "Télérupteur bipolaire",
            type = CircuitType.ECLAIRAGE_TELERUPTEUR,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "8 points lumineux max par circuit",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Le télérupteur bipolaire coupe simultanément la phase et le neutre. Il est recommandé ou exigé dans certaines configurations où une coupure complète du circuit est nécessaire, par exemple en local recevant du public, ou pour isoler totalement une ligne lors d'une intervention de maintenance.",
            pointsCles = listOf(
                "Coupure simultanée des deux pôles (phase + neutre), pour une isolation complète du circuit en aval.",
                "Utile dans les ERP ou installations où la norme/l'exploitant impose une coupure totale plutôt qu'une simple coupure de phase.",
                "Encombrement et coût légèrement supérieurs à un modèle unipolaire, à réserver aux cas où la coupure du neutre est réellement requise.",
                "Ne modifie pas la puissance ou le nombre de points lumineux admissibles sur le circuit : seule la logique de coupure change."
            ),
            normeRef = "NF C 15-100 §771.314 / §537 (sectionnement)"
        ),

        Circuit(
            id = "eclairage_detecteur",
            nom = "Éclairage extérieur à détecteur de mouvement / crépusculaire",
            type = CircuitType.ECLAIRAGE_DETECTEUR,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A (extérieur)",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V, boîtier de dérivation IP44 minimum",
            nombreMax = "8 points lumineux max par circuit",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Le détecteur de mouvement (infrarouge) ou la cellule crépusculaire est câblé en série sur la phase, entre le disjoncteur et le ou les luminaires, pour un allumage automatique sans action manuelle.",
            pointsCles = listOf(
                "Le détecteur se câble comme un interrupteur automatique : il coupe/laisse passer uniquement la phase.",
                "Prévoir un modèle avec réglage de seuil de luminosité, de durée et de sensibilité, accessible sans outil pour les ajustements.",
                "En extérieur, l'indice de protection du détecteur et du boîtier de raccordement doit être IP44 minimum.",
                "Un interrupteur de dérogation manuelle en parallèle du détecteur est souvent ajouté pour forcer l'allumage si besoin."
            ),
            normeRef = "NF C 15-100 §771.314 / §522"
        ),

        Circuit(
            id = "baes",
            nom = "BAES (Bloc Autonome d'Éclairage de Sécurité)",
            type = CircuitType.BAES,
            disjoncteur = "Circuit dédié non protégé par différentiel (têtes de ligne spécifiques)",
            differentiel = "Sans différentiel sur la ligne des BAES (obligation de continuité de service)",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V, ligne strictement dédiée, non mutualisée",
            nombreMax = "Selon le plan de sécurité incendie de l'établissement",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Obligatoire dans les ERP (établissements recevant du public) et parties communes d'immeubles, le BAES prend le relais de l'éclairage normal en cas de coupure secteur, grâce à sa batterie intégrée. Il doit rester alimenté en permanence par une ligne dédiée, jamais commandée par un interrupteur.",
            pointsCles = listOf(
                "La ligne d'alimentation des BAES ne doit jamais être protégée par un différentiel commun avec d'autres circuits, ni coupée par un interrupteur mural.",
                "Chaque BAES recharge sa batterie en permanence tant que le secteur est présent, et bascule automatiquement en cas de coupure.",
                "Une télécommande de test/mise au repos permet de vérifier le fonctionnement et d'économiser la batterie en cas d'inoccupation prolongée.",
                "En habitation individuelle, le BAES n'est généralement pas obligatoire ; il concerne surtout les ERP, bureaux et parties communes."
            ),
            normeRef = "NF C 15-100 / NF EN 60598-2-22 / Réglementation incendie ERP"
        ),

        Circuit(
            id = "contacteur_jn_ballon",
            nom = "Contacteur jour/nuit (ballon d'eau chaude, heures creuses)",
            type = CircuitType.CONTACTEUR_JN,
            disjoncteur = "20 A (courbe C), en amont du contacteur",
            differentiel = "30 mA type A",
            sectionCable = "2,5 mm² (puissance) + 1,5 mm² (signal HC/HP)",
            typeCable = "U-1000 R2V",
            nombreMax = "1 contacteur par ballon d'eau chaude",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Le contacteur jour/nuit est un relais de puissance piloté par le signal heures creuses/heures pleines du compteur (bornes C1/C2 en Linky, ou horloge programmable indépendante). Il coupe l'alimentation du ballon d'eau chaude pendant les heures pleines pour profiter du tarif réduit.",
            pointsCles = listOf(
                "Le signal de commande (bornes C1/C2 du compteur) alimente la bobine du contacteur, distincte du circuit de puissance du ballon.",
                "Le contact de puissance du contacteur coupe la phase (et parfois le neutre selon modèle) vers le ballon d'eau chaude.",
                "En l'absence de signal HC/HP disponible, une horloge programmable peut remplacer le pilotage par le compteur.",
                "Un interrupteur de forçage marche/auto est souvent ajouté pour relancer le chauffe-eau manuellement en heures pleines si besoin."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "refrigerateur",
            nom = "Réfrigérateur / congélateur (circuit dédié recommandé)",
            type = CircuitType.PRISE_SPECIALISEE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A recommandé",
            sectionCable = "1,5 mm² à 2,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "1 appareil par circuit dédié recommandé",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Bien que non explicitement imposé par la norme comme les autres circuits spécialisés, un circuit dédié pour le réfrigérateur/congélateur est fortement recommandé pour éviter toute coupure accidentelle (par un disjoncteur partagé qui saute) susceptible d'entraîner une perte alimentaire.",
            pointsCles = listOf(
                "Une prise détrompée ou un repérage visuel évite qu'un tiers ne débranche l'appareil par erreur pour un usage temporaire.",
                "Isoler ce circuit des autres prises évite qu'un défaut ailleurs (surcharge, court-circuit) ne coupe l'alimentation du froid.",
                "Pour un congélateur en local annexe (garage, cave), vérifier que le circuit reste protégé par un différentiel adapté à l'humidité ambiante."
            ),
            normeRef = "NF C 15-100 §771.314 (bonne pratique, non obligatoire)"
        ),

        Circuit(
            id = "hotte_aspirante",
            nom = "Hotte aspirante",
            type = CircuitType.PRISE_SPECIALISEE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "Peut être sur le circuit prises cuisine ou en dédié selon la puissance",
            pointsSchema = 1,
            couleurPhase = "Rouge ou marron",
            description = "La hotte aspirante est généralement raccordée sur une prise de courant classique proche du meuble haut, mais un circuit dédié est conseillé si elle est puissante (hotte à forte extraction) ou intégrée directement au bornier sans prise visible.",
            pointsCles = listOf(
                "Prévoir la prise ou le point de raccordement suffisamment haut et accessible pour l'entretien du moteur et le changement de filtre.",
                "Si raccordement direct au bornier (sans prise), couper impérativement le disjoncteur dédié avant toute intervention.",
                "Vérifier la compatibilité entre la puissance de la hotte et le calibre du circuit partagé si elle n'est pas en dédié."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "seche_serviettes",
            nom = "Sèche-serviettes électrique (salle de bain)",
            type = CircuitType.CHAUFFAGE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A (obligatoire en salle de bain)",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "1 sèche-serviettes par circuit, ou intégré au circuit chauffage de la pièce",
            pointsSchema = 1,
            couleurPhase = "Rouge ou marron",
            description = "Le sèche-serviettes électrique doit impérativement respecter les volumes de sécurité de la salle de bain (0, 1, 2, 3) définis par la norme : hors volume 1 pour un appareil classe I, ou en classe II uniquement s'il empiète sur le volume 2.",
            pointsCles = listOf(
                "Position obligatoire hors volume 0 et volume 1 sauf matériel spécifiquement autorisé et de classe adaptée.",
                "Un modèle avec résistance sèche (sans fluide caloporteur) ou mixte (fluide + résistance électrique) impose des règles de position légèrement différentes.",
                "Le différentiel 30 mA type A est obligatoire pour tout circuit desservant une salle d'eau.",
                "Si un fil pilote de régulation centralisée est prévu, respecter le même principe que pour les radiateurs (fil noir séparé)."
            ),
            normeRef = "NF C 15-100 partie 701 (locaux contenant une baignoire ou une douche)"
        ),

        Circuit(
            id = "climatisation",
            nom = "Climatisation réversible / pompe à chaleur air-air",
            type = CircuitType.CLIMATISATION,
            disjoncteur = "20 A à 32 A selon la puissance de l'unité extérieure",
            differentiel = "30 mA type A (voire type B selon variateur de fréquence du compresseur)",
            sectionCable = "2,5 mm² à 6 mm² selon la puissance",
            typeCable = "U-1000 R2V",
            nombreMax = "1 unité extérieure par circuit dédié, un ou plusieurs départs pour les unités intérieures selon le modèle",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Le circuit électrique alimente l'unité extérieure (compresseur), qui distribue ensuite l'énergie vers la ou les unités intérieures via une liaison frigorifique et un bus de communication propre au fabricant. Un interrupteur sectionneur de proximité est obligatoire près de l'unité extérieure.",
            pointsCles = listOf(
                "Un sectionneur à proximité immédiate de l'unité extérieure est obligatoire pour la sécurité des intervenants (maintenance, dépannage).",
                "Le type de différentiel (A ou B) dépend de la présence d'un variateur de fréquence sur le compresseur : vérifier la notice constructeur.",
                "Le calibre du disjoncteur doit correspondre exactement à la préconisation du fabricant, ne jamais le surdimensionner arbitrairement.",
                "La liaison entre unité intérieure et extérieure (frigorifique + bus) n'est pas un circuit électrique de puissance et ne suit pas les mêmes règles de section."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "store_banne",
            nom = "Store banne / pergola motorisée",
            type = CircuitType.VOLET_ROULANT,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A (motorisation extérieure)",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V, boîtier de raccordement IP44 minimum en extérieur",
            nombreMax = "Plusieurs stores par circuit selon puissance cumulée des moteurs",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Fonctionne sur le même principe qu'un volet roulant : un inverseur commande deux phases distinctes (sortie/rentrée) vers le moteur du store ou de la pergola, avec un neutre et une terre communs.",
            pointsCles = listOf(
                "Boîtier de raccordement et interrupteur mural en indice de protection IP44 minimum si exposés aux intempéries.",
                "Le moteur intègre généralement un capteur de vent qui force la fermeture automatique au-delà d'un seuil, indépendamment du tableau électrique.",
                "Le câblage suit la même logique montée/descente qu'un volet roulant classique."
            ),
            normeRef = "NF C 15-100 §771.314 / §522"
        ),

        Circuit(
            id = "alarme",
            nom = "Alarme / système de détection intrusion",
            type = CircuitType.ALARME,
            disjoncteur = "2 A dédié pour l'alimentation de la centrale",
            differentiel = "30 mA type AC (sur le primaire 230V de l'alimentation)",
            sectionCable = "1,5 mm² côté 230V, câbles constructeur en TBTS pour les détecteurs",
            typeCable = "U-1000 R2V (230V) + câble bus/TBTS spécifique alarme",
            nombreMax = "1 centrale par installation, détecteurs multiples en aval en TBTS",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Le tableau électrique alimente uniquement la centrale d'alarme (avec batterie de secours intégrée). Les détecteurs de mouvement, d'ouverture et la sirène fonctionnent ensuite en très basse tension ou en radio, indépendamment des circuits de puissance.",
            pointsCles = listOf(
                "La centrale doit rester alimentée en permanence : ne jamais la brancher sur un circuit commandé par interrupteur.",
                "La batterie de secours intégrée prend le relais en cas de coupure secteur, pour une autonomie de plusieurs heures.",
                "Une ligne téléphonique, un module GSM ou une connexion internet est souvent nécessaire pour la télétransmission des alertes, sans lien avec le tableau électrique.",
                "Prévoir un accès facile au disjoncteur dédié pour les opérations de maintenance sans couper le reste de l'installation."
            ),
            normeRef = "NF C 15-100 §771.314 (partie courants faibles)"
        ),

        Circuit(
            id = "eclairage_telerupteur",
            nom = "Éclairage par télérupteur / minuterie (escalier, couloir)",
            type = CircuitType.ECLAIRAGE_TELERUPTEUR,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm² (puissance) / 1,5 mm² (BP)",
            typeCable = "U-1000 R2V",
            nombreMax = "8 points lumineux max, boutons-poussoirs illimités en parallèle",
            pointsSchema = 3,
            couleurPhase = "Rouge ou marron",
            description = "Utilisé quand plus de 2 points de commande sont nécessaires (cage d'escalier, long couloir) : un télérupteur (relais bistable) ou une minuterie (relais temporisé) est piloté par des boutons-poussoirs (BP) en parallèle, eux-mêmes alimentés en très basse tension ou en 230V selon le modèle.",
            pointsCles = listOf(
                "Le télérupteur bascule l'état à chaque impulsion ; la minuterie coupe automatiquement après un délai réglable.",
                "Les boutons-poussoirs se câblent tous en parallèle sur la bobine de commande, pas en série comme un va-et-vient.",
                "Le télérupteur/minuterie est alimenté par le même circuit que les points lumineux qu'il commande, en amont de ceux-ci.",
                "Pour l'éclairage de cage d'escalier collective, une temporisation avec pré-alarme (baisse d'intensité) est souvent exigée."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "prises_16a",
            nom = "Prises de courant 16 A",
            type = CircuitType.PRISE_COURANT,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "8 socles max (1,5 mm²) ou 12 socles max en 2,5 mm² / 20 A",
            pointsSchema = 4,
            couleurPhase = "Rouge ou marron",
            description = "Circuit dédié aux prises de courant classiques 2P+T du logement (hors circuits spécialisés). Le câblage se fait en étoile ou en dérivation depuis une boîte de dérivation, jamais en cascade directe entre deux prises sans boîte.",
            pointsCles = listOf(
                "Boîte de dérivation obligatoire si le câblage n'est pas fait prise à prise avec bornes auto-dénudantes adaptées.",
                "Terre reliée systématiquement, même si l'appareil branché n'en a pas besoin.",
                "Respect scrupuleux du code couleur : bleu = neutre, vert/jaune = terre, jamais utilisés pour la phase.",
                "Une prise commandée (interrupteur général) doit être signalée par un repère normalisé."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "prise_commandee",
            nom = "Prise commandée (pilotée par interrupteur)",
            type = CircuitType.PRISE_COMMANDEE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "Intégrée au comptage du circuit prises (8 socles max en 1,5 mm²)",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Prise de courant dont la phase est coupée par un interrupteur mural, généralement utilisée pour allumer une lampe sur pied ou un lampadaire depuis l'entrée d'une pièce, sans câblage électrique dédié au luminaire lui-même.",
            pointsCles = listOf(
                "Comme pour l'éclairage, seule la phase est coupée par l'interrupteur : le neutre et la terre restent continus jusqu'à la prise.",
                "La prise commandée doit être repérée (détrompeur ou pictogramme) pour la distinguer d'une prise normale lors de travaux ultérieurs.",
                "Le circuit reste un circuit prises au sens de la norme : il partage donc le même quota que les prises non commandées du même départ.",
                "Ne pas confondre avec une prise à interrupteur intégré (rocker switch), qui ne nécessite pas de commande murale déportée."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "prise_specialisee",
            nom = "Prise spécialisée 20 A (four, lave-linge, lave-vaisselle...)",
            type = CircuitType.PRISE_SPECIALISEE,
            disjoncteur = "20 A (courbe C)",
            differentiel = "30 mA type A (obligatoire dès qu'un appareil à moteur/électronique est prévu)",
            sectionCable = "2,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "1 seul appareil par circuit, prise dédiée non partagée",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Chaque gros électroménager (four, lave-linge, lave-vaisselle, sèche-linge) doit disposer de sa propre ligne dédiée depuis le tableau, sans aucune autre prise en dérivation dessus.",
            pointsCles = listOf(
                "Une seule prise en bout de ligne : jamais de multiprise ni de dérivation supplémentaire.",
                "Différentiel de type A recommandé/obligatoire pour les appareils générant des courants de fuite avec composante continue (électronique de puissance).",
                "La cuisine nécessite généralement 6 circuits spécialisés minimum (four, lave-vaisselle, plaque, réfrigérateur conseillé en dédié, etc.)."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "plaque_cuisson",
            nom = "Plaque de cuisson",
            type = CircuitType.PLAQUE_CUISSON,
            disjoncteur = "32 A (courbe C)",
            differentiel = "30 mA type A",
            sectionCable = "6 mm² (mono) ou 3x2,5 mm² (tri selon puissance)",
            typeCable = "U-1000 R2V",
            nombreMax = "1 plaque par circuit",
            pointsSchema = 1,
            couleurPhase = "Marron (+ noir/rouge si triphasé)",
            description = "Circuit dédié pour la plaque de cuisson (induction, vitrocéramique) raccordée via une sortie de câble avec domino/bornier ou une prise spécifique 32A selon la puissance annoncée par le fabricant.",
            pointsCles = listOf(
                "Vérifier la puissance de la plaque : au-delà de 7,4 kW en mono, un raccordement triphasé peut être requis.",
                "Le raccordement se fait souvent directement au bornier de la plaque (pas de prise), via un boîtier de connexion mural.",
                "Toujours couper le disjoncteur dédié avant toute intervention sur ce circuit à forte intensité."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "chauffe_eau",
            nom = "Chauffe-eau électrique (cumulus)",
            type = CircuitType.CHAUFFE_EAU,
            disjoncteur = "20 A (courbe C) — 16 A possible selon puissance",
            differentiel = "30 mA type A",
            sectionCable = "2,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "1 chauffe-eau par circuit dédié",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Circuit dédié au chauffe-eau, souvent piloté en heures creuses via un contacteur jour/nuit commandé par un fil pilote depuis le compteur ou une horloge. Raccordement direct sur bornier, sans prise.",
            pointsCles = listOf(
                "Le contacteur jour/nuit (télérupteur) se place en amont du chauffe-eau, commandé par le signal HC/HP.",
                "Le bornier du chauffe-eau comporte phase, neutre et terre ; jamais de prise de courant sur ce type d'appareil fixe.",
                "Vérifier la puissance du cumulus (souvent 2200 à 2400 W) pour valider le calibre retenu."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "vmc",
            nom = "VMC (ventilation mécanique contrôlée)",
            type = CircuitType.VMC,
            disjoncteur = "2 A (ou 16 A si sur circuit dédié classique)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "1 groupe VMC par circuit dédié",
            pointsSchema = 1,
            couleurPhase = "Rouge ou marron",
            description = "La VMC doit être alimentée par un circuit dédié et fonctionner en permanence (pas de commande manuelle de coupure), afin de garantir le renouvellement d'air du logement.",
            pointsCles = listOf(
                "Le circuit VMC ne doit jamais être coupé par un interrupteur mural accessible aux occupants.",
                "Un fusible ou petit disjoncteur 2A dédié est courant, mais un 16A classique est toléré selon config.",
                "Prévoir une position accessible pour la maintenance (nettoyage filtres, entretien moteur)."
            ),
            normeRef = "NF C 15-100 §771.314 / DTU 68.3"
        ),

        Circuit(
            id = "chauffage",
            nom = "Chauffage électrique (radiateurs / fil pilote)",
            type = CircuitType.CHAUFFAGE,
            disjoncteur = "16 A ou 20 A selon puissance cumulée par zone",
            differentiel = "30 mA type AC (type A si régulation électronique)",
            sectionCable = "1,5 mm² (pilote) / 2,5 mm² (puissance)",
            typeCable = "U-1000 R2V",
            nombreMax = "Selon puissance : environ 1750 W par circuit en 1,5 mm² / 16A",
            pointsSchema = 3,
            couleurPhase = "Rouge ou marron",
            description = "Chaque zone de chauffage (radiateurs électriques à fil pilote) est raccordée en phase/neutre/terre plus un fil pilote noir supplémentaire permettant la régulation centralisée (confort, éco, hors-gel, arrêt).",
            pointsCles = listOf(
                "Le fil pilote (noir) ne transporte pas de puissance : il porte un signal de régulation entre le boîtier de programmation et chaque radiateur.",
                "Chaque radiateur peut être sur un circuit propre ou plusieurs radiateurs en aval d'un même disjoncteur selon la puissance totale.",
                "Ne jamais confondre le fil pilote avec une phase : un mauvais raccordement peut détruire l'électronique du radiateur."
            ),
            normeRef = "NF C 15-100 §771.314 / Guide UTE C 15-105"
        ),

        Circuit(
            id = "prise_exterieure",
            nom = "Prise de courant extérieure étanche",
            type = CircuitType.PRISE_EXTERIEURE,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A (obligatoire à l'extérieur)",
            sectionCable = "2,5 mm² recommandé (contraintes de longueur/tenue mécanique)",
            typeCable = "U-1000 R2V, cheminement sous fourreau TPC si enterré",
            nombreMax = "Circuit dédié recommandé, non mutualisé avec les prises intérieures",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Prise installée en extérieur (terrasse, jardin, façade), qui doit être équipée d'un indice de protection IP44 minimum (IP55 en zone très exposée) et d'un volet de protection contre les projections d'eau.",
            pointsCles = listOf(
                "Indice de protection IP44 minimum obligatoire, boîtier avec clapet ou trappe de fermeture.",
                "Différentiel de type A ou supérieur recommandé en extérieur du fait de l'humidité et des appareils électroportatifs.",
                "Si le câble chemine enterré, il doit être sous fourreau TPC (rouge) à une profondeur minimale de 50 à 85 cm selon protection.",
                "Prévoir un circuit dédié plutôt qu'un piquage sur une prise intérieure, pour isoler facilement en cas de défaut."
            ),
            normeRef = "NF C 15-100 §522 / §771.314"
        ),

        Circuit(
            id = "volet_roulant",
            nom = "Volets roulants électriques",
            type = CircuitType.VOLET_ROULANT,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm²",
            typeCable = "U-1000 R2V",
            nombreMax = "Plusieurs volets par circuit selon puissance cumulée des moteurs",
            pointsSchema = 2,
            couleurPhase = "Rouge ou marron",
            description = "Chaque volet roulant motorisé est commandé par un inverseur (montée/descente) alimentant le moteur via deux phases distinctes selon le sens, sur un neutre et une terre communs.",
            pointsCles = listOf(
                "Le commutateur volet utilise deux phases de commande (montée / descente), jamais simultanément.",
                "Le neutre et la terre sont communs aux deux sens de marche.",
                "Les fins de course sont intégrées au moteur, pas au tableau électrique."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "portail",
            nom = "Portail / porte de garage automatisée",
            type = CircuitType.PORTAIL,
            disjoncteur = "16 A (courbe C)",
            differentiel = "30 mA type A (moteur + électronique embarquée)",
            sectionCable = "1,5 mm² (alimentation) + câbles spécifiques constructeur pour les cellules/antenne",
            typeCable = "U-1000 R2V",
            nombreMax = "1 motorisation par circuit dédié",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Circuit dédié à l'armoire de commande de la motorisation (portail battant/coulissant, porte de garage). L'armoire répartit ensuite en TBTS vers le moteur, les cellules photoélectriques et le récepteur radio.",
            pointsCles = listOf(
                "Le 230V s'arrête au coffret de commande : le moteur, les cellules et l'antenne fonctionnent en très basse tension depuis ce coffret.",
                "Prévoir un fourreau dédié séparé entre le coffret et chaque cellule/antenne pour éviter les perturbations électromagnétiques.",
                "Un différentiel de type A est recommandé du fait de l'électronique de la carte de commande.",
                "Prévoir une coupure de proximité (interrupteur sectionneur) accessible pour la maintenance du moteur."
            ),
            normeRef = "NF C 15-100 §771.314"
        ),

        Circuit(
            id = "piscine",
            nom = "Piscine / spa (pompe de filtration)",
            type = CircuitType.PISCINE,
            disjoncteur = "16 A ou 20 A selon la puissance de la pompe",
            differentiel = "30 mA type A dédié, obligatoire (voire immunisé si local pompe humide)",
            sectionCable = "2,5 mm²",
            typeCable = "U-1000 R2V, cheminement conforme aux volumes 0/1/2 de la norme piscine",
            nombreMax = "1 pompe de filtration par circuit strictement dédié",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "La norme piscine (NF C 15-100 partie 702) découpe la zone en volumes 0, 1 et 2 avec des règles strictes : matériel de classe II en volume 1, TBTS 12V pour l'éclairage subaquatique en volume 0, liaison équipotentielle locale obligatoire reliant toutes les masses métalliques (échelle, structure, pompe).",
            pointsCles = listOf(
                "Liaison équipotentielle supplémentaire obligatoire : toutes les parties métalliques accessibles doivent être reliées entre elles et à la terre.",
                "En volume 0 (dans l'eau), seul le TBTS 12V est autorisé pour l'éclairage, avec transformateur de sécurité installé hors des volumes 0/1.",
                "La pompe de filtration doit être sur un circuit dédié avec son propre différentiel 30mA type A.",
                "Le tableau électrique piscine (coffret étanche IP65) doit être positionné hors des volumes réglementés."
            ),
            normeRef = "NF C 15-100 partie 702 (piscines et bassins de fontaine)"
        ),

        Circuit(
            id = "interphone",
            nom = "Interphone / visiophone (portier)",
            type = CircuitType.INTERPHONE,
            disjoncteur = "2 A dédié pour le transformateur d'alimentation",
            differentiel = "30 mA type AC (sur le primaire 230V du transformateur uniquement)",
            sectionCable = "1,5 mm² côté 230V, câble constructeur TBTS côté platine/combiné",
            typeCable = "U-1000 R2V (230V) + câble bus/TBTS spécifique visiophone",
            nombreMax = "1 transformateur par installation, plusieurs combinés en aval en TBTS",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Le tableau électrique alimente uniquement le transformateur (230V vers très basse tension) du système d'interphonie ou de visiophonie. La platine de rue, le ou les combinés intérieurs et la gâche électrique fonctionnent ensuite en TBTS, hors du champ des règles de protection différentielle classiques.",
            pointsCles = listOf(
                "Seul le primaire 230V du transformateur est protégé par le disjoncteur du tableau ; le câblage TBTS suit les préconisations du fabricant.",
                "La TBTS (généralement 12 à 24V) n'impose pas les mêmes contraintes de section/différentiel que les circuits de puissance.",
                "Prévoir un cheminement séparé des câbles TBTS et des câbles 230V pour limiter les perturbations sur le signal audio/vidéo.",
                "La gâche électrique de la porte est aussi alimentée en TBTS depuis le même transformateur ou un second dédié."
            ),
            normeRef = "NF C 15-100 §771.314 (partie courants faibles)"
        ),

        Circuit(
            id = "vdi",
            nom = "Réseau VDI (coffret de communication)",
            type = CircuitType.VDI_RESEAU,
            disjoncteur = "16 A dédié pour l'alimentation du coffret (prise du switch/box)",
            differentiel = "30 mA type AC",
            sectionCable = "1,5 mm² pour l'alimentation électrique du coffret",
            typeCable = "U-1000 R2V (230V) + câbles réseau Cat.6/6A pour le VDI lui-même",
            nombreMax = "1 circuit dédié pour l'alimentation du coffret de communication (grade 2 ou 3)",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Le coffret de communication (VDI : Voix - Données - Images) centralise l'arrivée opérateur, le switch réseau et le répartiteur téléphonique/TV. Le tableau électrique ne fournit qu'une prise de courant dédiée pour alimenter le switch et la box internet ; le câblage réseau lui-même (RJ45) est un réseau à part, sans rapport avec les normes de puissance du tableau.",
            pointsCles = listOf(
                "Une prise de courant dédiée doit être prévue dans ou à proximité immédiate du coffret VDI pour le switch et la box.",
                "Le câblage réseau (Cat.6/6A) et le câblage électrique doivent être séparés d'au moins quelques centimètres pour limiter les perturbations.",
                "Le grade du coffret (2 ou 3) détermine le nombre de prises RJ45 réparties dans le logement, sans impact sur le tableau électrique.",
                "Le coffret VDI est généralement placé près de l'arrivée du tableau électrique principal pour mutualiser les gaines techniques."
            ),
            normeRef = "NF C 15-100 §771.314 / Norme installation VDI (grade 2/3)"
        ),

        Circuit(
            id = "irve",
            nom = "Borne de recharge véhicule électrique (IRVE)",
            type = CircuitType.IRVE,
            disjoncteur = "32 A (courbe C) minimum, calibré selon la borne",
            differentiel = "30 mA type B (ou type A + dispositif de détection 6mA intégré à la borne)",
            sectionCable = "6 mm² minimum",
            typeCable = "U-1000 R2V",
            nombreMax = "1 borne par circuit strictement dédié",
            pointsSchema = 1,
            couleurPhase = "Marron",
            description = "Circuit obligatoirement dédié et protégé en tête de ligne par un différentiel adapté aux courants de défaut continus générés par l'électronique embarquée du véhicule et du chargeur.",
            pointsCles = listOf(
                "Le différentiel de type B est obligatoire sauf si la borne intègre déjà une protection RDC-DD homologuée (alors type A possible).",
                "Un circuit IRVE ne doit jamais être partagé avec un autre usage, même occasionnel.",
                "L'installation IRVE doit faire l'objet d'une attestation de conformité spécifique (Consuel IRVE)."
            ),
            normeRef = "NF C 15-100 §722 / NF C 15-100 Amendement A5"
        )
    )

    fun findById(id: String): Circuit? = circuits.find { it.id == id }
}
