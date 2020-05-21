package com.senierr.github.domain.user.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.Token
import com.senierr.repository.service.api.IUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 登录
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class LoginViewModel : ViewModel() {

    val loginSuccess = MutableLiveData<Token>()
    val loginFailure = MutableLiveData<Exception>()

    private val userService = Repository.getService<IUserService>()

    fun login(account: String, password: String) {
        viewModelScope.launch {
            try {
                val token = userService.login(account, password)
                loginSuccess.value = token
            } catch (e: Exception) {
                loginFailure.value = e
            }
        }
    }
}