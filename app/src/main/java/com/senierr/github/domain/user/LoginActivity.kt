package com.senierr.github.domain.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.senierr.base.support.ext.click
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.KeyboardUtil
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.user.vm.LoginViewModel
import com.senierr.repository.entity.dto.HttpException
import com.senierr.github.widget.CircularAnim
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class LoginActivity : BaseActivity(R.layout.activity_login) {

    companion object {
        const val EXTRA_TARGET_INTENT = "target_intent"
        const val LOGIN_SUCCESS = 1001
        const val LOGIN_FAILURE = 1002

        fun start(context: Context, targetIntent: Intent? = null) {
            val intent = Intent(context, LoginActivity::class.java)
            targetIntent?.let {
                intent.putExtra(EXTRA_TARGET_INTENT, it)
            }
            context.startActivity(intent)
        }

        fun startForResult(context: FragmentActivity, requestCode: Int) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivityForResult(intent, requestCode)
        }
    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    override fun onBackPressed() {
        doFinish(false)
    }

    private fun initView() {
        setSupportActionBar(tb_top)
        tb_top?.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_visibility?.click {
            it.isSelected = !it.isSelected
            et_password.setPasswordVisible(it.isSelected)
        }

        btn_login?.click {
            login()
        }

        et_account?.setText("senierr_zhou@163.com")
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.loginSuccess.observe(this, Observer {
            CircularAnim().fullActivity(this, btn_login)
                .colorOrImageRes(R.color.app_theme)
                .go(object : CircularAnim.OnAnimationEndListener {
                    override fun onAnimationEnd() {
                        doFinish(true)
                    }
                })
        })

        loginViewModel.loginFailure.observe(this, Observer {
            if (it is HttpException) {
                ToastUtil.showLong(this, it.message)
            } else {
                ToastUtil.showLong(this, R.string.network_error)
            }
            btn_login?.isClickable = true
            btn_login?.setText(R.string.login)
        })
    }

    private fun login() {
        btn_login?.isClickable = false
        btn_login?.setText(R.string.logging)
        KeyboardUtil.hideSoftInput(this)

        val account = et_account?.text?.toString()
        val password = et_password?.text?.toString()

        if (account.isNullOrBlank()) {
            ToastUtil.showShort(this, R.string.account_empty)
            btn_login?.isClickable = true
            btn_login?.setText(R.string.login)
            return
        }
        if (password.isNullOrBlank()) {
            ToastUtil.showShort(this, R.string.password_empty)
            btn_login?.isClickable = true
            btn_login?.setText(R.string.login)
            return
        }

        loginViewModel.login(account, password)
    }

    /**
     * 页面关闭处理
     */
    private fun doFinish(isSuccess: Boolean) {
        if (isSuccess) {
            val targetIntent = intent.getParcelableExtra<Intent>(EXTRA_TARGET_INTENT)
            if (targetIntent != null) {
                startActivity(targetIntent)
            } else {
                setResult(LOGIN_SUCCESS, intent)
            }
        } else {
            setResult(LOGIN_FAILURE, intent)
        }
        finish()
    }
}
