package com.senierr.github.domain.account

import android.content.Context
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService

/**
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class LoginPresenter : LoginContract.Presenter() {

    private val userService = Repository.getService<IUserService>()

    override fun login(context: Context) {
        val account = view?.getAccount() ?: return
        val password = view?.getPassword() ?: return

        // todo 验证合法性

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