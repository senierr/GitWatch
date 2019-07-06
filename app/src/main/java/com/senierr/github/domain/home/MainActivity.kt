package com.senierr.github.domain.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.setOnThrottleClickListener
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.LogUtil
import com.senierr.github.R
import com.senierr.github.domain.account.view.LoginActivity
import com.senierr.repository.service.impl.UserService
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 登录页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class MainActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    val githubService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubService.getCurrentAccount()
            .subscribeOnIO()
            .observeOnMain()
            .subscribe {
                et_account.setText(it)
            }
            .bindToLifecycle()


        btn_login.setOnThrottleClickListener {
            val account = et_account.text?.toString()
            val password = et_password.text?.toString()

            if (account != null && password != null) {
                githubService.login(account, password)
                    .subscribeOnIO()
                    .observeOnMain()
                    .subscribe {
                        LogUtil.logE(it.toString())
                    }
            }


        }
    }
}
