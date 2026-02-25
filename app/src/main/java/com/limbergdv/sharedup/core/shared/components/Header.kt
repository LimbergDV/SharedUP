package com.limbergdv.sharedup.core.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.limbergdv.sharedup.core.ui.theme.onSecondaryLight
import com.limbergdv.sharedup.R

@Composable
fun Header(
    name: String = "Limberg",
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(onSecondaryLight)
            .padding(horizontal = 22.dp, vertical = 20.dp),
        // SpaceBetween empuja el texto a la izquierda y el ícono a la derecha
        horizontalArrangement = Arrangement.SpaceBetween,
        // Centra ambos elementos verticalmente
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hola $name",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Icon(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Perfil de usuario",
            modifier = Modifier
                .size(32.dp)
                .clickable { onProfileClick() },
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeader() {
    Header(name = "Limberg")
}