package com.senierr.github.domain.account

import android.content.Context
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService

/**
 * 登录逻辑
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class LoginPresenter : LoginContract.Presenter() {

    private val userService = Repository.getService<IUserService>()

    override fun login(context: Context) {
        val account = view?.getAccount()
        val password = view?.getPassword()

        if (account.isNullOrBlank()) {
            view?.showAccountEmpty()
            return
        }
        if (password.isNullOrBlank()) {
            view?.showPasswordEmpty()
            return
        }

        userService.login(account, password)
            .subscribeOnIO()
            .observeOnMain()
            .subscribe({
                view?.showLoginSuccess()
            }, {
                view?.showLoginFailure(it)
            })
            .bindToLifecycle()
    }
}