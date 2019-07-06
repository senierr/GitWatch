package com.senierr.github.domain

import android.os.Bundle
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.setOnThrottleClickListener
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.LogUtil
import com.senierr.github.R
import com.senierr.repository.service.impl.UserService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
