package com.limbergdv.sharedup.core.network

import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.AuthResponse
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.LoginRequestDto
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.UserRegisterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SharedUpApi {

    @POST("api/v1/login/")
    suspend fun login(
        @Body credentials: LoginRequestDto
    ): AuthResponse

    @POST("api/v1/register/")
    suspend fun register(
        @Body userData: UserRegisterDto
    ): Response<AuthResponse>
}