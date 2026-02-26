package com.limbergdv.sharedup.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.limbergdv.sharedup.features.addPost.domain.entities.Post

@Composable
fun PostCard(
    post: Post,
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {}
) {
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
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
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
                    text = "Usuario #${post.idUser}",
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
            text = post.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Text(
            text = post.text,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(outlineVariantLight)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.like_icon),
                    contentDescription = "Like",
                    modifier = Modifier.size(28.dp).clickable { onLikeClick() },
                    // CAMBIO AQUÍ: Cambia de color si está seleccionado
                    tint = if (post.isLiked) com.limbergdv.sharedup.core.ui.theme.primaryLight else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.likeCount.toString(),
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
                    modifier = Modifier.size(28.dp).clickable { onDislikeClick() },
                    // CAMBIO AQUÍ: Cambia de color si está seleccionado (usé Red de ejemplo, puedes usar otro)
                    tint = if (post.isDisliked) Color.Red else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.disLikeCount.toString(),
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
    PostCard(
        post = Post(
            id = 1,
            title = "Aviso importante de vialidad",
            text = "El dia de hoy no hay paso en via téran hay una marcha de docentes",
            likeCount = 12,
            disLikeCount = 0,
            idUser = 101
        )
    )
}