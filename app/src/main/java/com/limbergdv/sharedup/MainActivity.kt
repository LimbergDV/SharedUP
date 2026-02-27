package com.limbergdv.sharedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.limbergdv.sharedup.core.navigation.AppNavigatorImpl
import com.limbergdv.sharedup.core.navigation.FeatureNavGraph
import com.limbergdv.sharedup.core.ui.theme.AppTheme
import com.limbergdv.sharedup.features.authentication.navigation.AuthRoutes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navGraphs: Set<@JvmSuppressWildcards FeatureNavGraph>

    @Inject
    lateinit var navigator: AppNavigatorImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {

                val navController = rememberNavController()

                // Se adjunta solo una vez
                LaunchedEffect(navController) {
                    navigator.attach(navController)
                }

                NavHost(
                    navController = navController,
                    startDestination = AuthRoutes.AUTH_GRAPH
                ) {
                    navGraphs.forEach { graph ->
                        graph.register(this)
                    }
                }
            }
        }
    }
}