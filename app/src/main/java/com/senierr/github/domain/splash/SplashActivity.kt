package com.senierr.github.domain.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.main.MainActivity
import com.senierr.github.domain.splash.vm.SplashViewModel
import com.senierr.github.domain.user.LoginActivity
import com.senierr.repository.entity.dto.HttpException

/**
 * 引导页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        splashViewModel.autoLogin()
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        splashViewModel.autoLoginSuccess.observe(this, Observer {
            MainActivity.start(this)
            finish()
        })
        splashViewModel.autoLoginFailure.observe(this, Observer {
            if (it is HttpException) {
                ToastUtil.showShort(this, it.message)
            }
            val targetIntent = Intent(this, MainActivity::class.java)
            LoginActivity.start(this, targetIntent)
            finish()
        })
    }
}