package com.sergiom.mvvmstudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sergiom.mvvmstudy.ui.screens.ChecklistPantalla
import com.sergiom.mvvmstudy.ui.screens.CrearCheckListScreen
import com.sergiom.mvvmstudy.ui.screens.HomeScreen
import com.sergiom.mvvmstudy.ui.screens.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationRoutes.Splash.route){
        composable(NavigationRoutes.Splash.route) {
            // Simulamos un splash screen corto con delay
            SplashScreen(
                onTimeout = {
                    navController.navigate(NavigationRoutes.Home.route){
                        popUpTo(NavigationRoutes.Splash.route) { inclusive = true  }
                    }
                }
            )
        }

        composable(NavigationRoutes.Home.route){
            HomeScreen(
                onIrAPersonalizada = {
                    navController.navigate(NavigationRoutes.CheckListPersonalizada.route)
                },
                onIrAPredefinida = {navController.navigate(NavigationRoutes.CheckListPredefinida.route)}
            )
        }

        composable(NavigationRoutes.CheckListPersonalizada.route) {
            CrearCheckListScreen(
                onContinuar = { nombre ->
                    navController.navigate(NavigationRoutes.CheckListEditor.route)
                }
            )
        }

        composable(NavigationRoutes.CheckListPredefinida.route) {
            // Pendiente de implementar más adelante
        }
    }



}