package com.limbergdv.sharedup.features.home.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.core.navigation.AppNavigator
import com.limbergdv.sharedup.core.ws.PostWebSocketManager
import com.limbergdv.sharedup.features.home.domain.entities.Post
import com.limbergdv.sharedup.features.addPost.navigation.AddPostRoutes
import com.limbergdv.sharedup.features.home.domain.usecases.GetPostsUseCase
import com.limbergdv.sharedup.features.home.navigation.HomeRoutes
import com.limbergdv.sharedup.features.home.presentation.screens.HomeUiState
import com.limbergdv.sharedup.features.myPosts.navigation.MyPostRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val navigator: AppNavigator,
    private val postWebSocketManager: PostWebSocketManager  // ← Inyectado por Hilt
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    // Guardamos el Job para poder cancelarlo si es necesario
    private var wsJob: Job? = null

    init {
        getPosts()
        startListeningPosts()
    }

    // Carga inicial de posts desde la API
    fun getPosts() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getPostsUseCase()

            result.fold(
                onSuccess = { posts ->
                    _uiState.update { it.copy(isLoading = false, posts = posts) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }

    // Escucha posts en tiempo real vía WebSocket
    private fun startListeningPosts() {
        wsJob?.cancel() // Cancelamos si ya había una conexión activa
        wsJob = viewModelScope.launch {
            postWebSocketManager.observePosts()
                .catch { error ->
                    // Si el WebSocket falla, lo logueamos pero no tumbamos la app
                    Log.e("HomeViewModel", " Error en WebSocket: ${error.message}")
                }
                .collect { event ->
                    Log.d("HomeViewModel", " Nuevo post en tiempo real: ${event.title}")

                    // Construimos un Post de dominio con los datos del WebSocket
                    val nuevoPost = Post(
                        id = event.id,
                        title = event.title,
                        text = event.text,
                        likeCount = event.likeCount,
                        disLikeCount = event.dislikeCount,
                        idUser = event.idUser,
                        userName = "Carlos", // Agrega esto
                        userCareer = "Software", // Agrega esto
                        createdAt = "2026-02-26"  // Agrega esto
                    )

                    // Lo agregamos al INICIO de la lista para que se vea primero
                    _uiState.update { state ->
                        val listaActualizada = listOf(nuevoPost) + state.posts
                        state.copy(posts = listaActualizada)
                    }
                }
        }
    }


    override fun onCleared() {
        super.onCleared()
        wsJob?.cancel()
        postWebSocketManager.disconnect()
    }


    fun goHome() {
        navigator.navigate(HomeRoutes.HOME_GRAPH)
    }

    fun goToAddPost() {
        navigator.navigate(AddPostRoutes.ADD_POST_GRAPH)
    }

    fun goToHistory() {
        navigator.navigate(MyPostRoutes.MY_POST_GRAPH)
    }
}