package com.noasoftsolutions.machineCoding.repository


import com.noasoftsolutions.machineCoding.api.ApiResult
import com.noasoftsolutions.machineCoding.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getUsers(
    ): Flow<ApiResult<List<User>>>

}