package com.tableauelec.app.data

/**
 * Type de circuit : détermine le symbole/la forme du schéma affiché.
 */
enum class CircuitType {
    ECLAIRAGE,
    ECLAIRAGE_TELERUPTEUR,
    ECLAIRAGE_DETECTEUR,
    PRISE_COURANT,
    PRISE_COMMANDEE,
    PRISE_SPECIALISEE,
    PRISE_EXTERIEURE,
    PLAQUE_CUISSON,
    CHAUFFE_EAU,
    CONTACTEUR_JN,
    VMC,
    CHAUFFAGE,
    CLIMATISATION,
    VOLET_ROULANT,
    PORTAIL,
    PISCINE,
    INTERPHONE,
    ALARME,
    BAES,
    VDI_RESEAU,
    IRVE
}

/**
 * Représente un circuit terminal du tableau électrique, avec ses
 * caractéristiques normatives (NF C 15-100, amendement A5 - 2023 inclus).
 */
data class Circuit(
    val id: String,
    val nom: String,
    val type: CircuitType,
    val disjoncteur: String,
    val differentiel: String,
    val sectionCable: String,
    val typeCable: String,
    val nombreMax: String,
    val pointsSchema: Int,
    val couleurPhase: String,
    val description: String,
    val pointsCles: List<String>,
    val normeRef: String
)
