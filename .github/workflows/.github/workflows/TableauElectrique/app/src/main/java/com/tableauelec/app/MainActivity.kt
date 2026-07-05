package com.tableauelec.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tableauelec.app.data.CircuitRepository
import com.tableauelec.app.ui.CircuitDetailScreen
import com.tableauelec.app.ui.CircuitListScreen
import com.tableauelec.app.ui.theme.TableauElectriqueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TableauElectriqueTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "liste") {
        composable("liste") {
            CircuitListScreen(onCircuitClick = { circuit ->
                navController.navigate("detail/${circuit.id}")
            })
        }
        composable(
            route = "detail/{circuitId}",
            arguments = listOf(navArgument("circuitId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("circuitId")
            val circuit = CircuitRepository.findById(id ?: "")
            if (circuit != null) {
                CircuitDetailScreen(circuit = circuit, onBack = { navController.popBackStack() })
            }
        }
    }
}
