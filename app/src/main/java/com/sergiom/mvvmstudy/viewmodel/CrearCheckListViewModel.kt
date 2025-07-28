package com.sergiom.mvvmstudy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sergiom.mvvmstudy.data.model.CheckListConfig


class CrearCheckListViewModel : ViewModel() {
    var config by mutableStateOf(CheckListConfig())
        private set

    fun onNombreCambiado(nombre: String){
        config = config.copy(nombre = nombre)
    }

    fun onModeloAvionCambiado(modelo: String){
        config = config.copy(modeloAvion = modelo)
    }

    fun onAerolineaCambiada(aerolinea: String){
        config = config.copy(aerolinea = aerolinea)
    }

    fun onIncluirLogoCambiado(valor: Boolean){
        config = config.copy(incluirLogo = valor)
    }

    fun onNumeroSeccionesCambiado(n: Int){
        config = config.copy(numeroSecciones = n.coerceIn(1,10)) // MÁX 10
    }
}