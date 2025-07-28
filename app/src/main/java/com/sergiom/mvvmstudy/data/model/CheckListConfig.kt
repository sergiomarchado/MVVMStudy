package com.sergiom.mvvmstudy.data.model



data class CheckListConfig(
    val nombre: String = "",
    val modeloAvion: String = "",
    val aerolinea: String = "",
    val incluirLogo: Boolean = false,
    val numeroSecciones: Int = 1
)
