package com.sergiom.mvvmstudy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.sergiom.mvvmstudy.data.model.CheckListItem

@Composable
fun ChecklistItemCard(
    item: CheckListItem,
    onEliminar: () -> Unit,
    onToogleCompletado: () -> Unit
) {
    /*
    * mostrarDialogo controla si se debe mostrar el diálogo de confirmación.
    * Usamos rememberSaveable para que se conserve incluso si giramos la pantalla.
    */
    var mostrarDialogo by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Checkbox para marcar o desmarcar el ítem como completado
                Checkbox(
                    checked = item.completada,
                    onCheckedChange = { onToogleCompletado() }
                )

                // Texto con tachado si el ítem está marcado como completado
                Text(
                    text = item.texto,
                    style = if (item.completada)
                        MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough)
                    else
                        MaterialTheme.typography.bodyLarge
                )
            }

            // Botón de eliminar que muestra un diálogo de confirmación
            IconButton(onClick = { mostrarDialogo = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar ítem"
                )
            }
        }

        // Diálogo de confirmación para eliminar el ítem
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("Eliminar elemento") },
                text = { Text("¿Estás seguro de que deseas eliminar esta acción de la checklist?") },
                confirmButton = {
                    TextButton(onClick = {
                        onEliminar()
                        mostrarDialogo = false
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
