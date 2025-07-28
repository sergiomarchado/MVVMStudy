package com.sergiom.mvvmstudy.navigation


sealed class NavigationRoutes (val route: String) {
    object Splash: NavigationRoutes("splash")
    object Home: NavigationRoutes("home")
    object CheckListPersonalizada: NavigationRoutes("checklist_personalizada")
    object CheckListPredefinida: NavigationRoutes("checklist_predefinida")
    object CheckListEditor: NavigationRoutes("checklist_editor")
}