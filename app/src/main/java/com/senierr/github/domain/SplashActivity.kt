package com.senierr.github.domain

import android.content.Intent
import android.os.Bundle
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.LogUtil
import com.senierr.github.R
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Observable.timer(1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap Repository.getService<IUserService>().getAuthorization()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    LogUtil.logE("Authorization: $it")
                    // 已登录，跳转
                    startActivity(Intent(this, MainActivity::class.java))
//                    if (it) {
//                        // 已登录，跳转
//                        startActivity(Intent(this, MainActivity::class.java))
//                    } else {
//                        // 未登录，跳转登录页
//                        val intent = Intent(this, LoginActivity::class.java)
//                        val targetIntent = Intent(this, MainActivity::class.java)
//                        intent.putExtra(LoginActivity.EXTRA_TARGET_INTENT, targetIntent)
//                        startActivity(intent)
//                    }
                    finish()
                }, {

                })
                .bindToLifecycle()
    }
}