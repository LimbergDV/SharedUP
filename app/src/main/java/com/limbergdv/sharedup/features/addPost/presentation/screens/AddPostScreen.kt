package com.limbergdv.sharedup.features.addPost.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.limbergdv.sharedup.core.shared.components.Header
import com.limbergdv.sharedup.core.shared.components.NavBar
import com.limbergdv.sharedup.core.ui.theme.onPrimaryLight
import com.limbergdv.sharedup.core.ui.theme.onTextFielColor
import com.limbergdv.sharedup.core.ui.theme.primaryLight
import com.limbergdv.sharedup.core.ui.theme.textFieldColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen() {
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Comparte una idea",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = primaryLight,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Escribe aquí...", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = textFieldColor,
                    unfocusedContainerColor = onTextFielColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = primaryLight
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            //
            Button(
                onClick = { /* Acción para añadir la publicación */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight
                )
            ) {
                Text(
                    text = "Añadir Publicación",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Recuerda que esta es una aplicacion institucional no divulgues ni agredas a los demas",
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPost() {
    AddPostScreen()
}