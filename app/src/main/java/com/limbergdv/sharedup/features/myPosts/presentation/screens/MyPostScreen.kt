package com.limbergdv.sharedup.features.myPosts.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.limbergdv.sharedup.core.shared.components.Header
import com.limbergdv.sharedup.core.shared.components.NavBar
import com.limbergdv.sharedup.core.ui.theme.onPrimaryLight
import com.limbergdv.sharedup.core.ui.theme.primaryLight
import com.limbergdv.sharedup.features.myPosts.presentation.components.MyPostCard

@Composable
fun MyPostScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = onPrimaryLight,
        bottomBar = {
            NavBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header()

            Spacer(modifier = Modifier.height(19.dp))

            Text(
                text = "Mis publicaciones",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = primaryLight,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            MyPostCard()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMyPost(){
    MyPostScreen()
}