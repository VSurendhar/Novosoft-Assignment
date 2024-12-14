package com.noasoftsolutions.machineCoding.repository

import com.noasoftsolutions.machineCoding.api.Api
import com.noasoftsolutions.machineCoding.api.ApiResult
import com.noasoftsolutions.machineCoding.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    override suspend fun getUsers(): Flow<ApiResult<List<User>>> {
        return flow {
            val usersFromApi = try {
                api.getUsers()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiResult.Error("Couldn't load data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Http Exception"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading Items"))
                return@flow
            }
            emit(ApiResult.Success(usersFromApi))
        }
    }

}