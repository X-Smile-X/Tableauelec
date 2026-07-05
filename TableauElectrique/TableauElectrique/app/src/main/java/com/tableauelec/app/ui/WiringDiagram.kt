package com.tableauelec.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.tableauelec.app.data.Circuit
import com.tableauelec.app.data.CircuitType
import com.tableauelec.app.ui.theme.FilNeutreBleu
import com.tableauelec.app.ui.theme.FilPhaseMarron
import com.tableauelec.app.ui.theme.FilPhaseRouge
import com.tableauelec.app.ui.theme.FilPilote
import com.tableauelec.app.ui.theme.FilTerreJaune
import com.tableauelec.app.ui.theme.FilTerreVert

/**
 * Schéma de principe du câblage : peigne d'alimentation -> interrupteur
 * différentiel -> disjoncteur divisionnaire -> point(s) d'utilisation.
 * Représentation pédagogique simplifiée, pas un plan d'exécution.
 */
@Composable
fun WiringDiagram(circuit: Circuit, modifier: Modifier = Modifier) {
    val phaseColor = if (circuit.couleurPhase.contains("marron", ignoreCase = true))
        FilPhaseMarron else FilPhaseRouge

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(380.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
    ) {
        val w = size.width
        val h = size.height
        val leftX = w * 0.18f
        val phaseX = leftX
        val neutreX = leftX + 34f
        val terreX = leftX + 68f
        val sw = 4f

        fun text(
            s: String, x: Float, y: Float, textSize: Float = 24f,
            color: Int = android.graphics.Color.DKGRAY, bold: Boolean = false,
            center: Boolean = false
        ) {
            val paint = android.graphics.Paint().apply {
                this.color = color
                this.textSize = textSize
                isAntiAlias = true
                textAlign = if (center) android.graphics.Paint.Align.CENTER else android.graphics.Paint.Align.LEFT
                if (bold) typeface = android.graphics.Typeface.DEFAULT_BOLD
            }
            drawContext.canvas.nativeCanvas.drawText(s, x, y, paint)
        }

        // --- 1. Peigne d'alimentation (bus Ph / N / T) ---
        val busY = 18f
        text("Ph", phaseX, busY - 4f, 20f, android.graphics.Color.RED, true, true)
        text("N", neutreX, busY - 4f, 20f, android.graphics.Color.BLUE, true, true)
        text("T", terreX, busY - 4f, 20f, android.graphics.Color.rgb(46, 125, 50), true, true)

        // --- 2. Interrupteur différentiel ---
        val diffTop = 40f
        val diffBottom = 100f
        drawLine(phaseColor, Offset(phaseX, busY + 6f), Offset(phaseX, diffTop), sw)
        drawLine(FilNeutreBleu, Offset(neutreX, busY + 6f), Offset(neutreX, diffTop), sw)

        drawRect(
            color = Color(0xFF37474F),
            topLeft = Offset(leftX - 24f, diffTop),
            size = Size(140f, diffBottom - diffTop),
            style = Stroke(3f)
        )
        text("Différentiel", leftX - 14f, diffTop + 26f, 20f, bold = true)
        text(circuit.differentiel, leftX - 14f, diffTop + 46f, 15f)

        // --- 3. Disjoncteur divisionnaire ---
        val breakerTop = diffBottom + 26f
        val breakerBottom = breakerTop + 60f
        drawLine(phaseColor, Offset(phaseX, diffBottom), Offset(phaseX, breakerTop), sw)
        drawLine(FilNeutreBleu, Offset(neutreX, diffBottom), Offset(neutreX, breakerTop), sw)

        drawRect(
            color = Color(0xFF263238),
            topLeft = Offset(leftX - 24f, breakerTop),
            size = Size(140f, breakerBottom - breakerTop),
            style = Stroke(3f)
        )
        text("Disjoncteur", leftX - 14f, breakerTop + 24f, 20f, bold = true)
        text(circuit.disjoncteur, leftX - 14f, breakerTop + 44f, 15f)

        // --- Terre : ligne continue, jamais coupée par un appareil de protection ---
        val terreBottomY = h - 30f
        drawTerreLine(terreX, busY + 6f, terreBottomY, sw)

        // --- 4. Distribution vers le(s) point(s) d'utilisation ---
        val distY = breakerBottom + 30f
        val n = circuit.pointsSchema.coerceAtLeast(1)
        val zoneLeft = leftX + 90f
        val zoneRight = w - 30f
        val step = if (n > 1) (zoneRight - zoneLeft) / (n - 1) else 0f

        drawLine(phaseColor, Offset(phaseX, breakerBottom), Offset(phaseX, distY), sw)
        drawLine(FilNeutreBleu, Offset(neutreX, breakerBottom), Offset(neutreX, distY), sw)
        // bus horizontal de répartition
        drawLine(phaseColor, Offset(phaseX, distY), Offset(if (n > 1) zoneRight else phaseX, distY), sw)
        drawLine(FilNeutreBleu, Offset(neutreX, distY - 8f), Offset(if (n > 1) zoneRight else neutreX, distY - 8f), sw)

        val symbolY = distY + 90f
        for (i in 0 until n) {
            val cx = if (n > 1) zoneLeft + step * i else (zoneLeft + zoneRight) / 2f
            // branches verticales
            drawLine(phaseColor, Offset(cx, distY), Offset(cx, symbolY - 24f), sw)
            drawLine(FilNeutreBleu, Offset(cx - 6f, distY - 8f), Offset(cx - 6f, symbolY - 24f), sw)
            drawLine(FilTerreVert, Offset(terreX, terreBottomY), Offset(cx + 6f, symbolY - 24f), sw)
            drawSymbol(circuit.type, cx, symbolY, ::text)
        }

        // --- Fil pilote pour le chauffage électrique ---
        if (circuit.type == CircuitType.CHAUFFAGE) {
            text("Fil pilote (noir) : régulation confort / éco / hors-gel", leftX, h - 8f, 15f, android.graphics.Color.DKGRAY)
        }
    }
}

private fun DrawScope.drawTerreLine(x: Float, yTop: Float, yBottom: Float, strokeWidth: Float) {
    // Représentation bicolore vert/jaune du fil de terre par tirets alternés
    var y = yTop
    var green = true
    val dash = 14f
    while (y < yBottom) {
        val yEnd = (y + dash).coerceAtMost(yBottom)
        drawLine(
            color = if (green) FilTerreVert else FilTerreJaune,
            start = Offset(x, y),
            end = Offset(x, yEnd),
            strokeWidth = strokeWidth
        )
        y = yEnd
        green = !green
    }
}

private fun DrawScope.drawSymbol(
    type: CircuitType,
    cx: Float,
    cy: Float,
    text: (String, Float, Float, Float, Int, Boolean, Boolean) -> Unit
) {
    val black = Color(0xFF212121)
    when (type) {
        CircuitType.ECLAIRAGE -> {
            drawCircle(black, radius = 20f, center = Offset(cx, cy), style = Stroke(3f))
            drawLine(black, Offset(cx - 14f, cy - 14f), Offset(cx + 14f, cy + 14f), 2f)
            drawLine(black, Offset(cx - 14f, cy + 14f), Offset(cx + 14f, cy - 14f), 2f)
            text("Point lumineux", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.ECLAIRAGE_TELERUPTEUR -> {
            // petit relais (télérupteur) au-dessus, lampe en dessous
            drawRoundRect(
                black, topLeft = Offset(cx - 18f, cy - 32f), size = Size(36f, 20f),
                cornerRadius = CornerRadius(4f, 4f), style = Stroke(2.5f)
            )
            text("TL", cx, cy - 18f, 13f, android.graphics.Color.DKGRAY, true, true)
            drawCircle(black, radius = 18f, center = Offset(cx, cy + 8f), style = Stroke(3f))
            drawLine(black, Offset(cx - 12f, cy - 4f), Offset(cx + 12f, cy + 20f), 2f)
            drawLine(black, Offset(cx - 12f, cy + 20f), Offset(cx + 12f, cy - 4f), 2f)
            text("Télérupteur + lampe", cx, cy + 54f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PRISE_COURANT, CircuitType.PRISE_SPECIALISEE -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 22f, cy - 15f), size = Size(44f, 30f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            drawCircle(black, 3.5f, Offset(cx - 9f, cy))
            drawCircle(black, 3.5f, Offset(cx + 9f, cy))
            val label = if (type == CircuitType.PRISE_SPECIALISEE) "Prise dédiée" else "Prise 16A"
            text(label, cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PRISE_COMMANDEE -> {
            // interrupteur mural avant la prise
            drawRoundRect(
                black, topLeft = Offset(cx - 44f, cy - 12f), size = Size(20f, 24f),
                cornerRadius = CornerRadius(3f, 3f), style = Stroke(2.5f)
            )
            drawLine(black, Offset(cx - 24f, cy), Offset(cx - 22f, cy), 2f)
            drawRoundRect(
                black, topLeft = Offset(cx - 4f, cy - 15f), size = Size(44f, 30f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            drawCircle(black, 3.5f, Offset(cx + 9f, cy))
            drawCircle(black, 3.5f, Offset(cx + 27f, cy))
            text("Interrupteur → prise", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PRISE_EXTERIEURE -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 22f, cy - 15f), size = Size(44f, 30f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            drawCircle(black, 3.5f, Offset(cx - 9f, cy))
            drawCircle(black, 3.5f, Offset(cx + 9f, cy))
            // goutte de pluie = IP44
            drawCircle(black, 4f, Offset(cx + 30f, cy - 20f), style = Stroke(2f))
            text("IP44", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PLAQUE_CUISSON -> {
            drawRect(black, topLeft = Offset(cx - 26f, cy - 20f), size = Size(52f, 40f), style = Stroke(3f))
            drawCircle(black, 8f, Offset(cx - 10f, cy - 5f), style = Stroke(2f))
            drawCircle(black, 8f, Offset(cx + 10f, cy + 6f), style = Stroke(2f))
            text("Plaque cuisson", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.CHAUFFE_EAU -> {
            drawRect(black, topLeft = Offset(cx - 16f, cy - 22f), size = Size(32f, 44f), style = Stroke(3f))
            drawArc(black, 0f, -180f, false, topLeft = Offset(cx - 16f, cy - 30f), size = Size(32f, 16f), style = Stroke(3f))
            text("Cumulus", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.CONTACTEUR_JN -> {
            // relais J/N (horloge) puis ballon
            drawRoundRect(
                black, topLeft = Offset(cx - 46f, cy - 16f), size = Size(30f, 32f),
                cornerRadius = CornerRadius(4f, 4f), style = Stroke(2.5f)
            )
            drawCircle(black, 9f, Offset(cx - 31f, cy), style = Stroke(1.8f))
            drawLine(black, Offset(cx - 31f, cy), Offset(cx - 31f, cy - 6f), 1.5f)
            drawLine(black, Offset(cx - 31f, cy), Offset(cx - 26f, cy), 1.5f)
            text("J/N", cx - 31f, cy + 22f, 12f, android.graphics.Color.DKGRAY, true, true)
            drawLine(black, Offset(cx - 16f, cy), Offset(cx - 2f, cy), 2f)
            drawRect(black, topLeft = Offset(cx - 2f, cy - 22f), size = Size(28f, 44f), style = Stroke(3f))
            drawArc(black, 0f, -180f, false, topLeft = Offset(cx - 2f, cy - 30f), size = Size(28f, 16f), style = Stroke(3f))
            text("Contacteur + ballon", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.VMC -> {
            drawCircle(black, radius = 20f, center = Offset(cx, cy), style = Stroke(3f))
            drawLine(black, Offset(cx, cy - 18f), Offset(cx, cy + 18f), 2f)
            drawLine(black, Offset(cx - 16f, cy - 9f), Offset(cx + 16f, cy + 9f), 2f)
            drawLine(black, Offset(cx - 16f, cy + 9f), Offset(cx + 16f, cy - 9f), 2f)
            text("Groupe VMC", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.CHAUFFAGE -> {
            drawRect(black, topLeft = Offset(cx - 24f, cy - 18f), size = Size(48f, 36f), style = Stroke(3f))
            for (i in 1..3) {
                val fy = cy - 18f + i * 9f
                drawLine(black, Offset(cx - 24f, fy), Offset(cx + 24f, fy), 1.5f)
            }
            drawLine(FilPilote, Offset(cx - 24f, cy + 28f), Offset(cx - 24f, cy - 40f), 2f)
            text("Radiateur", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.VOLET_ROULANT -> {
            drawRect(black, topLeft = Offset(cx - 20f, cy - 18f), size = Size(40f, 36f), style = Stroke(3f))
            text("↑", cx, cy - 2f, 18f, android.graphics.Color.DKGRAY, true, true)
            text("↓", cx, cy + 14f, 18f, android.graphics.Color.DKGRAY, true, true)
            text("Moteur volet", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.ECLAIRAGE_DETECTEUR -> {
            drawCircle(black, radius = 18f, center = Offset(cx, cy + 4f), style = Stroke(3f))
            drawLine(black, Offset(cx - 12f, cy - 8f), Offset(cx + 12f, cy + 16f), 2f)
            drawLine(black, Offset(cx - 12f, cy + 16f), Offset(cx + 12f, cy - 8f), 2f)
            // arcs de détection
            drawArc(black, -60f, 120f, false, topLeft = Offset(cx - 34f, cy - 30f), size = Size(28f, 28f), style = Stroke(2f))
            drawArc(black, -60f, 120f, false, topLeft = Offset(cx - 26f, cy - 22f), size = Size(14f, 14f), style = Stroke(2f))
            text("Détecteur + lampe", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.BAES -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 22f, cy - 16f), size = Size(44f, 32f),
                cornerRadius = CornerRadius(5f, 5f), style = Stroke(3f)
            )
            // pictogramme flèche de sortie simplifié
            drawLine(black, Offset(cx - 10f, cy), Offset(cx + 8f, cy), 2f)
            drawLine(black, Offset(cx + 8f, cy), Offset(cx + 1f, cy - 6f), 2f)
            drawLine(black, Offset(cx + 8f, cy), Offset(cx + 1f, cy + 6f), 2f)
            text("BAES", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.CLIMATISATION -> {
            drawRect(black, topLeft = Offset(cx - 28f, cy - 18f), size = Size(56f, 36f), style = Stroke(3f))
            for (i in 1..2) {
                val fy = cy - 18f + i * 12f
                drawLine(black, Offset(cx - 28f, fy), Offset(cx + 28f, fy), 1.5f)
            }
            drawCircle(black, 7f, Offset(cx + 20f, cy), style = Stroke(2f))
            text("Unité extérieure", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.ALARME -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 18f, cy - 18f), size = Size(36f, 36f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            // cloche simplifiée
            drawArc(black, 180f, 180f, false, topLeft = Offset(cx - 9f, cy - 9f), size = Size(18f, 16f), style = Stroke(2f))
            drawLine(black, Offset(cx - 10f, cy + 7f), Offset(cx + 10f, cy + 7f), 2f)
            drawCircle(black, 2f, Offset(cx, cy + 10f))
            text("Centrale alarme", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PORTAIL -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 20f, cy - 20f), size = Size(40f, 40f),
                cornerRadius = CornerRadius(4f, 4f), style = Stroke(3f)
            )
            drawLine(black, Offset(cx - 12f, cy - 8f), Offset(cx - 12f, cy + 12f), 2f)
            drawLine(black, Offset(cx + 12f, cy - 8f), Offset(cx + 12f, cy + 12f), 2f)
            text("Coffret moteur", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.PISCINE -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 22f, cy - 18f), size = Size(44f, 30f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            text("POMPE", cx, cy + 2f, 12f, android.graphics.Color.DKGRAY, true, true)
            // vaguelettes
            for (i in -1..1) {
                drawArc(
                    black, 0f, 180f, false,
                    topLeft = Offset(cx - 16f + i * 14f, cy + 18f), size = Size(14f, 8f), style = Stroke(2f)
                )
            }
            text("Filtration piscine", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.INTERPHONE -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 18f, cy - 18f), size = Size(36f, 36f),
                cornerRadius = CornerRadius(6f, 6f), style = Stroke(3f)
            )
            drawCircle(black, 8f, Offset(cx, cy), style = Stroke(2f))
            drawCircle(black, 2.5f, Offset(cx, cy))
            text("Transfo TBTS", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.VDI_RESEAU -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 24f, cy - 18f), size = Size(48f, 36f),
                cornerRadius = CornerRadius(4f, 4f), style = Stroke(3f)
            )
            for (i in 0..2) {
                drawLine(black, Offset(cx - 24f + i * 24f, cy - 18f), Offset(cx - 24f + i * 24f, cy + 18f), 1.5f)
            }
            text("Coffret VDI", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
        CircuitType.IRVE -> {
            drawRoundRect(
                black, topLeft = Offset(cx - 28f, cy - 20f), size = Size(56f, 40f),
                cornerRadius = CornerRadius(8f, 8f), style = Stroke(3f)
            )
            drawCircle(black, 4f, Offset(cx - 10f, cy))
            drawCircle(black, 4f, Offset(cx + 10f, cy))
            drawCircle(black, 12f, Offset(cx + 26f, cy - 26f), style = Stroke(2f))
            text("B", cx + 26f, cy - 21f, 14f, android.graphics.Color.DKGRAY, true, true)
            text("Borne IRVE", cx, cy + 46f, 15f, android.graphics.Color.DKGRAY, false, true)
        }
    }
}
