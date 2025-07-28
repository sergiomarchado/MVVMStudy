package com.sergiom.mvvmstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergiom.mvvmstudy.components.ChecklistInput
import com.sergiom.mvvmstudy.components.ChecklistItemCard
import com.sergiom.mvvmstudy.viewmodel.ChecklistViewModel

@Composable
fun ChecklistPantalla(
    modifier: Modifier = Modifier,
    viewModel: ChecklistViewModel = viewModel()
) {
    /*
    * Obtenemos el estado actual de los ítems de checklist desde el ViewModel.
    * Esta lista es observable: si cambia (añadir, eliminar, marcar), Compose lo detecta y recompone.
    *
    * viewModel es una instancia de ChecklistViewModel.
    * .checkListItems accede a la propiedad pública que definimos en el ViewModel.
    * Desde aquí solo podemos ver su valor, pero no modificarla directamente.
    */
    val checkListItems = viewModel.checkListItems

    // Texto actual del campo de entrada (estado controlado por el ViewModel)
    val texto = viewModel.textoNuevoItem

    Column(modifier = modifier.padding(16.dp)) {

        // Composable reutilizable para el campo de texto + botón de agregar
        ChecklistInput(
            texto = texto,
            onTextoCambiado = { viewModel.onTextoNuevoItemCambiado(it) },
            onAgregar = { viewModel.agregarItem() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*
        * LazyColumn es como un RecyclerView moderno.
        * Muestra los ítems de la checklist con eficiencia incluso si la lista crece.
        */
        LazyColumn {
            items(checkListItems) { item ->
                ChecklistItemCard(
                    item = item,
                    onEliminar = { viewModel.eliminarItem(item) },
                    onToogleCompletado = { viewModel.marcarComoCompletado(item) }
                )
            }
        }
    }
}
