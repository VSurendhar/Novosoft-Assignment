package com.noasoftsolutions.machineCoding.api

import com.noasoftsolutions.machineCoding.model.User
import retrofit2.http.GET

interface Api {


    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        const val BASE_URL =
            "https://api.github.com"
    }

}