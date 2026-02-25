package com.limbergdv.sharedup.features.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.limbergdv.sharedup.core.shared.components.Header
import com.limbergdv.sharedup.core.shared.components.NavBar
import com.limbergdv.sharedup.core.ui.theme.onPrimaryLight
import com.limbergdv.sharedup.core.ui.theme.primaryLight
import com.limbergdv.sharedup.features.home.presentation.components.Card

@Composable
fun HomeScreen() {
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
                .padding(16.dp)
        ) {
            Header()
            Text(
                text = "Novedades",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = primaryLight
            )
            Card()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    HomeScreen()
}