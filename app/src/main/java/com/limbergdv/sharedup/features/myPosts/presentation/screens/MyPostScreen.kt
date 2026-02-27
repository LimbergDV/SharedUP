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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.limbergdv.sharedup.core.shared.components.Header
import com.limbergdv.sharedup.core.shared.components.NavBar
import com.limbergdv.sharedup.core.ui.theme.onPrimaryLight
import com.limbergdv.sharedup.core.ui.theme.primaryLight
import com.limbergdv.sharedup.features.myPosts.presentation.components.MyPostCard
import com.limbergdv.sharedup.features.myPosts.presentation.viewmodels.MyPostViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment


@Composable
fun MyPostScreen(
    viewModel: MyPostViewModel = hiltViewModel()
) {

    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMyPosts()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = onPrimaryLight,
        bottomBar = {
            NavBar(
                onHomeClick = { viewModel.goHome() },
                onAddClick = { viewModel.goToAddPost() },
                onHistoryClick = { viewModel.goToHistory() }
            )
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

            if (isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cargando...")
                }

            } else {

                LazyColumn {
                    items(posts) { post ->
                        MyPostCard(post = post)
                    }
                }
            }
        }
    }
}