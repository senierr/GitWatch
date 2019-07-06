package com.senierr.github.domain.account

import android.content.Intent
import android.os.Bundle
import com.senierr.base.support.ext.setOnThrottleClickListener
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.MainActivity
import com.senierr.repository.entity.Error
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    private val loginPresenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    /**
     * 初始化界面
     */
    private fun initViews() {
        btn_login?.setOnThrottleClickListener {
            loginPresenter.login(this)
        }
    }

    override fun getAccount(): String? {
        return et_account?.text?.toString()
    }

    override fun getPassword(): String? {
        return et_password?.text?.toString()
    }

    override fun showLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showLoginFailure(e: Throwable) {
        if (e is Error) {
            val msg = e.errorMsg
            if (msg != null) {
                ToastUtil.showLong(this, msg)
            }
        }
    }
}
