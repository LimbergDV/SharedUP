package com.limbergdv.sharedup.core.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.limbergdv.sharedup.R
import com.limbergdv.sharedup.core.ui.theme.primaryLight

@Composable
fun NavBar(
    onHomeClick: () -> Unit,
    onAddClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Padding exterior para que no pegue a los bordes de la pantalla
        horizontalArrangement = Arrangement.spacedBy(12.dp), // Espacio entre el bloque azul y el negro
        verticalAlignment = Alignment.CenterVertically
    ) {


        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(primaryLight)
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.famicons_home),
                contentDescription = "Inicio",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onHomeClick() },
                tint = Color(0xFFE0E0E0)
            )

            Icon(
                painter = painterResource(id = R.drawable.add_outline),
                contentDescription = "Agregar",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onAddClick() },
                tint = Color(0xFFE0E0E0)
            )
        }

        // BLOQUE DERECHO (Negro)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
                .clickable { onHistoryClick()}
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.histpost),
                contentDescription = "Historial",
                modifier = Modifier.size(32.dp),
                tint = Color(0xFFE0E0E0)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNavBar() {

}