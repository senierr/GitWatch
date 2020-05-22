package com.senierr.github.domain.splash.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senierr.repository.Repository
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.Token
import com.senierr.repository.entity.dto.UserInfo
import com.senierr.repository.service.api.IUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 引导页
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class SplashViewModel : ViewModel() {

    val autoLoginSuccess = MutableLiveData<UserInfo>()
    val autoLoginFailure = MutableLiveData<Exception>()

    private val userService = Repository.getService<IUserService>()

    fun autoLogin() {
        viewModelScope.launch {
            try {
                val response = userService.autoLogin()
                delay(2 * 1000)
                autoLoginSuccess.value = response
            } catch (e: Exception) {
                autoLoginFailure.value = e
            }
        }
    }
}