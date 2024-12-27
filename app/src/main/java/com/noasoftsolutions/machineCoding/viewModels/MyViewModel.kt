package com.noasoftsolutions.machineCoding.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noasoftsolutions.machineCoding.api.ApiResult
import com.noasoftsolutions.machineCoding.model.User
import com.noasoftsolutions.machineCoding.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val users = _userList.asStateFlow()

    private var _showBankCards = MutableStateFlow<Boolean>(true)
    val showBankCards = _showBankCards.asStateFlow()

    private val _showErrorToastChannel = Channel<String>()
    val toastMessages = _showErrorToastChannel.receiveAsFlow()


    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers().collectLatest { result ->
                when (result) {
                    is ApiResult.Success -> {
                        _showErrorToastChannel.send("Success")
                        result.data?.let { userList ->
                            _userList.value = userList
                        } ?: run {
                            _showErrorToastChannel.send("NULL Response")
                        }
                    }

                    is ApiResult.Error -> {
                        println("Error fetching seats: ${result.message}")
                        _showErrorToastChannel.send("Error Fetching Users")
                    }
                }
            }
        }
    }

    fun showBankCards() {
        _showBankCards.value = false
    }

}

