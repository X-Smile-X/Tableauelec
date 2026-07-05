package com.tableauelec.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tableauelec.app.data.Circuit
import com.tableauelec.app.ui.theme.BleuElectrique
import com.tableauelec.app.ui.theme.TexteGris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircuitDetailScreen(circuit: Circuit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(circuit.nom, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BleuElectrique,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Schéma de câblage", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            WiringDiagram(circuit = circuit)

            SpecCard(circuit)

            Text("Points clés", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            circuit.pointsCles.forEach { point ->
                Row(verticalAlignment = androidx.compose.ui.Alignment.Top) {
                    Icon(
                        Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = BleuElectrique,
                        modifier = Modifier
                            .padding(top = 2.dp, end = 8.dp)
                            .size(18.dp)
                    )
                    Text(point, fontSize = 14.sp, lineHeight = 20.sp)
                }
            }

            Surface(
                color = Color(0xFFFFF3E0),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "⚠️ Contenu pédagogique. Toute intervention sur un tableau électrique doit être réalisée ou validée par un professionnel qualifié, avec attestation de conformité (Consuel) si nécessaire. Réf. : ${circuit.normeRef}",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 12.sp,
                    color = Color(0xFF8A5300)
                )
            }
        }
    }
}

@Composable
private fun SpecCard(circuit: Circuit) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(14.dp),
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(circuit.description, fontSize = 14.sp, lineHeight = 20.sp, color = Color(0xFF333333))
            Divider()
            SpecRow("Disjoncteur", circuit.disjoncteur)
            SpecRow("Différentiel", circuit.differentiel)
            SpecRow("Section de câble", circuit.sectionCable)
            SpecRow("Type de câble", circuit.typeCable)
            SpecRow("Limite normative", circuit.nombreMax)
        }
    }
}

@Composable
private fun SpecRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            label,
            modifier = Modifier.weight(0.4f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = TexteGris
        )
        Text(value, modifier = Modifier.weight(0.6f), fontSize = 13.sp)
    }
}
