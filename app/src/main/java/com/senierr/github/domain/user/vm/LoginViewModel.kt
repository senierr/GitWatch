package com.senierr.github.domain.user.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.Token
import com.senierr.repository.entity.dto.UserInfo
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

    val loginSuccess = MutableLiveData<UserInfo>()
    val loginFailure = MutableLiveData<Exception>()

    private val userService = Repository.getService<IUserService>()

    fun login(account: String, password: String) {
        viewModelScope.launch {
            try {
                val userInfo = userService.login(account, password)
                loginSuccess.value = userInfo
            } catch (e: Exception) {
                loginFailure.value = e
            }
        }
    }
}