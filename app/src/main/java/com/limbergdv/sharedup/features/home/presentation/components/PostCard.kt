package com.limbergdv.sharedup.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.limbergdv.sharedup.R
import com.limbergdv.sharedup.core.ui.theme.onSurfaceVariantLight
import com.limbergdv.sharedup.core.ui.theme.outlineVariantLight

@Composable
fun PostCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(onSurfaceVariantLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.up_logo),
                contentDescription = "Logo UP",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Limberg del valle",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "Ingeniería en Software",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "7:10 am",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "12/12/23",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }


        Text(
            text = "El dia de hoy no hay paso en via téran hay una\nmarcha de docentes",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.size(8.dp))

        // Likes / Dislikes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // Eliminado el clip extra aquí, el contenedor padre ya redondea las esquinas
                .background(outlineVariantLight)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.like_icon),
                    contentDescription = "Like",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "12",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.dislike_icon),
                    contentDescription = "Dislike",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "0",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard()
}