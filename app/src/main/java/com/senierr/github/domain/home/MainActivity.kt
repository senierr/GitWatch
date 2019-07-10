package com.senierr.github.domain.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.LogUtil
import com.senierr.github.R
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
