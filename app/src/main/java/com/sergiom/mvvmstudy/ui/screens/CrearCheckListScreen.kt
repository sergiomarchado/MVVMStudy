package com.sergiom.mvvmstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergiom.mvvmstudy.viewmodel.CrearCheckListViewModel


@Composable
fun CrearCheckListScreen(
    viewModel: CrearCheckListViewModel = viewModel(),
    onContinuar: (String) -> Unit // Pasar config completa en el futuro

) {
    val config = viewModel.config

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(text = "Crear nueva Check List", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = config.nombre,
            onValueChange = viewModel::onNombreCambiado,
            label = { Text("Nombre de tu Check List personalizada")},
            singleLine = true
        )

        OutlinedTextField(
            value = config.modeloAvion,
            onValueChange = viewModel::onModeloAvionCambiado,
            label = { Text("Modelo de avión (ej. A320)") },
            singleLine = true
        )

        OutlinedTextField(
            value = config.aerolinea,
            onValueChange = viewModel::onAerolineaCambiada,
            label = { Text("Compañía o aerolínea (opcional)") },
            singleLine = true
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Incluir logo?")
            Switch(
                checked = config.incluirLogo,
                onCheckedChange = viewModel::onIncluirLogoCambiado
            )
        }

        OutlinedTextField(
            value = config.numeroSecciones.toString(),
            onValueChange = {
                val nuevo = it.toIntOrNull() ?: 1
                viewModel.onNumeroSeccionesCambiado(nuevo)
            },
            label = { Text("Número de secciones") },
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onContinuar(config.nombre) }, // En el futuro puedes pasar el objeto completo
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }
    }
}