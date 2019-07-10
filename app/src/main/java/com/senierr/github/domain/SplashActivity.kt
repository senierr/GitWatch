package com.senierr.github.domain

import android.content.Intent
import android.os.Bundle
import com.senierr.base.support.ui.BaseActivity
import com.senierr.github.R
import com.senierr.github.domain.account.LoginActivity
import com.senierr.github.domain.home.MainActivity
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 引导页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Observable.timer(1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap Repository.getService<IUserService>().getCurrentUser()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // 已登录，跳转
                    MainActivity.start(this)
                    finish()
                }, {
                    val targetIntent = Intent(this, MainActivity::class.java)
                    LoginActivity.start(this, targetIntent)
                    finish()
                })
                .bindToLifecycle()
    }
}