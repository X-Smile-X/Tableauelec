package com.tableauelec.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tableauelec.app.data.Circuit
import com.tableauelec.app.data.CircuitRepository
import com.tableauelec.app.ui.theme.BleuElectrique
import com.tableauelec.app.ui.theme.TexteGris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircuitListScreen(onCircuitClick: (Circuit) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tableau Électrique — Circuits", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BleuElectrique,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    "Sélectionne un circuit pour voir son schéma de câblage et ses caractéristiques NF C 15-100.",
                    color = TexteGris,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            items(CircuitRepository.circuits) { circuit ->
                CircuitCard(circuit = circuit, onClick = { onCircuitClick(circuit) })
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun CircuitCard(circuit: Circuit, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(BleuElectrique.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.ElectricBolt, contentDescription = null, tint = BleuElectrique)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(circuit.nom, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(2.dp))
                Text(
                    "${circuit.disjoncteur} · ${circuit.sectionCable}",
                    color = TexteGris,
                    fontSize = 13.sp
                )
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = TexteGris)
        }
    }
}
