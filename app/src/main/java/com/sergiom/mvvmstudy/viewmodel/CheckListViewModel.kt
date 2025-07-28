package com.sergiom.mvvmstudy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sergiom.mvvmstudy.data.model.CheckListItem

class ChecklistViewModel : ViewModel() {

    // AQUÍ IRÁ EL ESTADO Y LA LÓGICA DE LA CHECKLIST AERONÁUTICA

    /*
    * La app necesita una lista de elementos de checklist (List<CheckListItem>).
    * Esta lista representa las acciones que deben marcarse como realizadas durante una fase del vuelo.
    * Por ejemplo: encender baterías, chequear luces, flaps, etc.
    */

    //----------------------------------------------------------------------------------------------

    /*
    * _checkListItems es una lista mutable y observable.
    * Esta variable representa la fuente REAL de datos y solo puede modificarse desde el ViewModel.
    */
    private val _checkListItems = mutableStateListOf<CheckListItem>()

    /*
    * checkListItems es la versión pública y de solo lectura.
    * La UI accede a esta lista para mostrar los datos, pero no puede modificarla directamente.
    */
    val checkListItems: List<CheckListItem> = _checkListItems

    /*
    * siguienteId nos permite asignar un ID único a cada ítem de la checklist.
    * Es importante que cada elemento tenga su identificador para poder gestionarlo correctamente.
    */
    private var siguienteId = 0

    //----------------------------------------------------------------------------------------------

    // ¿POR QUÉ? ENCAPSULAMIENTO:
    /*
    * Intentamos siempre que el resto del código (la UI u otros componentes) no toque directamente
    * el estado interno del ViewModel.
    *
    * Si hiciéramos esto directamente:
    *     val checkListItems = mutableStateListOf<CheckListItem>()
    * La UI podría modificarla con:
    *     viewModel.checkListItems.clear()  ← ¡no queremos esto!
    *
    * O peor:
    *     viewModel.checkListItems[0] = CheckListItem(...)
    *
    * Eso rompería el principio de que **el ViewModel se encarga de TODA la lógica**.
    */

    /*
    * Campo para almacenar temporalmente el texto que el usuario escribe al añadir un nuevo ítem.
    * mutableStateOf lo convierte en observable por Compose.
    */
    var textoNuevoItem by mutableStateOf("")
        private set

    /*
    * Función para actualizar el texto cuando el usuario escribe en el TextField.
    * Solo se puede modificar desde aquí (por eso el setter está en private).
    */
    fun onTextoNuevoItemCambiado(nuevoTexto: String) {
        textoNuevoItem = nuevoTexto
    }

    /*
    * Función que agrega un nuevo ítem a la checklist.
    * Solo se agrega si el texto no está en blanco.
    * Después de agregarlo, limpiamos el campo de texto.
    */
    fun agregarItem() {
        if (textoNuevoItem.isNotBlank()) {
            _checkListItems.add(CheckListItem(id = siguienteId++, texto = textoNuevoItem))
            textoNuevoItem = "" // Limpiamos el campo después de agregar
        }
    }

    /*
    * Función que elimina un ítem de la checklist.
    * Al eliminarlo, Compose detecta automáticamente el cambio gracias a la lista observable.
    */
    fun eliminarItem(item: CheckListItem) {
        _checkListItems.remove(item)
        /*
        * Al ser una data class, Kotlin compara por contenido (equals) y no por referencia.
        * Más adelante podríamos usar el id para mayor seguridad.
        */
    }

    /*
    * Esta función cambia el estado de completado de un ítem.
    * Si estaba en false (no completado), pasa a true (completado), y viceversa.
    */
    fun marcarComoCompletado(item: CheckListItem) {
        val index = _checkListItems.indexOf(item)

        if (index != -1) {
            _checkListItems[index] = item.copy(completada = !item.completada)
            /*
            * copy() crea una nueva copia del objeto con el valor "completada" invertido.
            * Al asignarla a la misma posición en la lista, Compose detecta el cambio y recompone.
            */
        }
    }
}
