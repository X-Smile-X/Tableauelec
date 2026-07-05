# Tableau Électrique Pro

Appli Android native (Kotlin + Jetpack Compose) qui présente, pour chaque
circuit terminal d'un tableau électrique domestique, un schéma de câblage
et les caractéristiques normatives (NF C 15-100 / guide UTE C 15-105) :
disjoncteur, différentiel, section de câble, code couleur des fils, points
clés de raccordement.

## Circuits couverts
Éclairage, prises 16A, prises spécialisées 20A, plaque de cuisson,
chauffe-eau électrique, VMC, chauffage électrique (fil pilote), volets
roulants, borne de recharge véhicule électrique (IRVE).

## Ouvrir le projet
1. Installer **Android Studio** (dernière version stable, Koala ou plus récent).
2. `File > Open`, sélectionner le dossier `TableauElectrique`.
3. Android Studio va détecter le projet Gradle et proposer de générer le
   **Gradle Wrapper** automatiquement au premier sync (le wrapper binaire
   n'est pas inclus dans cet export pour rester léger — Android Studio le
   crée tout seul, ou lance `gradle wrapper` depuis un terminal si tu as
   Gradle installé).
4. Laisser le sync Gradle se terminer (ça télécharge les dépendances
   Compose/Material3/Navigation listées dans `app/build.gradle.kts`).
5. Lancer sur un émulateur ou un téléphone (bouton ▶ "app").

## Structure du projet
```
app/src/main/java/com/tableauelec/app/
├── MainActivity.kt              -> point d'entrée + navigation
├── data/
│   ├── Circuit.kt                -> modèle de données
│   └── CircuitRepository.kt      -> les 9 circuits avec leurs specs NF C 15-100
└── ui/
    ├── CircuitListScreen.kt      -> liste des circuits
    ├── CircuitDetailScreen.kt    -> fiche détaillée + specs + points clés
    ├── WiringDiagram.kt          -> dessin du schéma (Canvas Compose)
    └── theme/                    -> couleurs (dont couleurs normalisées des fils)
```

## Pour aller plus loin
- Ajouter un mode "quiz" pour réviser les calibres et sections de câble.
- Ajouter une simulation de bilan de puissance du tableau (somme des
  disjoncteurs vs abonnement).
- Exporter chaque fiche en PDF pour usage hors-ligne sur chantier.

## Important
Contenu pédagogique de référence. Toute installation ou modification
d'un tableau électrique doit être réalisée ou validée par un professionnel
qualifié, avec attestation de conformité (Consuel) lorsque requise. Les
normes évoluent (dernière prise en compte ici : amendement A5 de la
NF C 15-100) : vérifie toujours la version en vigueur avant travaux.
