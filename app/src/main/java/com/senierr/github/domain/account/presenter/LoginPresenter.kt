package com.senierr.github.domain.account.presenter

import android.content.Context
import android.text.TextUtils
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.github.domain.account.contract.LoginContract
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

        if (TextUtils.isEmpty(account)) {
            view?.showAccountEmpty()
            return
        }
        if (TextUtils.isEmpty(password)) {
            view?.showPasswordEmpty()
            return
        }

        if (account != null && password != null) {
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
}