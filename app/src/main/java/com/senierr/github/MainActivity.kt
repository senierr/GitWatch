package com.senierr.github

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.setOnThrottleClickListener
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.base.support.utils.LogUtil
import com.senierr.repository.remote.GithubServiceImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hello.setOnThrottleClickListener {
            GithubServiceImpl.login("senierr", "111")
                .subscribeOnIO()
                .observeOnMain()
                .subscribe({
                    LogUtil.logE(it.toString())
                }, {
                    LogUtil.logE("error: ${it.message}")
                })
        }
    }
}
