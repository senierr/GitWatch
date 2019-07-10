package com.senierr.github.domain.account

import android.arch.lifecycle.MutableLiveData
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.mvvm.BaseViewModel
import com.senierr.repository.Repository
import com.senierr.repository.remote.entity.HttpResponse
import com.senierr.repository.remote.entity.UserInfo
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable

/**
 *
 * @author zhouchunjie
 * @date 2019/7/9
 */
class LoginViewModel : BaseViewModel() {

    data class LoginResult(val e: Throwable?, val response: HttpResponse<UserInfo>?)

    private val userService = Repository.getService<IUserService>()

    val loginResult = MutableLiveData<LoginResult>()

    fun login(account: String, password: String) {
        userService.login(account, password)
            .subscribeOnIO()
            .observeOnMain()
            .subscribe({
                loginResult.value = LoginResult(null, it)
            }, {
                loginResult.value = LoginResult(it, null)
            })
            .bindToLifecycle()
    }
}