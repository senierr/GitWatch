package com.senierr.github.domain.account

import android.content.Context
import com.senierr.base.support.mvp.BasePresenter
import com.senierr.base.support.mvp.BaseView

/**
 * 版本升级契约类
 *
 * @author zhouchunjie
 * @date 2019/6/27
 */
interface LoginContract {

    interface View: BaseView {
        fun getAccount(): String?
        fun getPassword(): String?
        fun showLoginSuccess()
        fun showLoginFailure(e: Throwable)
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun login(context: Context)
    }
}