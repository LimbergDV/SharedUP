package com.limbergdv.sharedup.features.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.limbergdv.sharedup.core.navigation.FeatureNavGraph
import com.limbergdv.sharedup.features.authentication.presentation.screens.LoginScreen
import com.limbergdv.sharedup.features.authentication.presentation.screens.RegisterScreen

import javax.inject.Inject

class AuthNavGraph @Inject constructor() : FeatureNavGraph {

    override fun register(builder: NavGraphBuilder) {
        builder.navigation(
            route = AuthRoutes.AUTH_GRAPH,
            startDestination = AuthRoutes.LOGIN
        ) {
            composable(AuthRoutes.LOGIN) {
                LoginScreen()
            }

            composable(AuthRoutes.REGISTER) {
                RegisterScreen()
            }
        }
    }
}