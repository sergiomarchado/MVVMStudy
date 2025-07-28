package com.sergiom.mvvmstudy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable que representa el campo de entrada + botón para añadir nuevos ítems
 * a la checklist (por ejemplo: "Encender baterías", "Verificar flaps", etc.).
 *
 * Se trata de una UI simple y reutilizable. No contiene lógica de negocio: solo
 * muestra lo que se le pasa desde fuera.
 */
@Composable
fun ChecklistInput(
    texto: String,                        // Texto actual del campo de entrada
    onTextoCambiado: (String) -> Unit,   // Callback cuando el usuario escribe
    onAgregar: () -> Unit                // Callback cuando el usuario pulsa "Agregar"
) {
    Column {
        TextField(
            value = texto,
            onValueChange = onTextoCambiado,
            label = { Text("Nuevo ítem de checklist") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onAgregar) {
            Text("Agregar")
        }
    }
}
