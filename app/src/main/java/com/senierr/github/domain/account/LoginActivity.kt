package com.senierr.github.domain.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.senierr.base.support.ext.click
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.KeyboardUtil
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.widget.CircularAnim
import com.senierr.repository.remote.entity.Error
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class LoginActivity : BaseActivity(), LoginContract.View {

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

    private val loginPresenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter.onAttach(this)
        initView()
    }

    override fun onDestroy() {
        loginPresenter.onDetach()
        super.onDestroy()
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
            btn_login?.isClickable = false
            btn_login?.setText(R.string.logging)
            KeyboardUtil.hideSoftInput(this)
            loginPresenter.login(this)
        }
    }

    override fun getAccount(): String? {
        return et_account?.text?.toString()
    }

    override fun getPassword(): String? {
        return et_password?.text?.toString()
    }

    override fun showAccountEmpty() {
        ToastUtil.showShort(this, R.string.account_empty)
        btn_login?.isClickable = true
        btn_login?.setText(R.string.login)
    }

    override fun showPasswordEmpty() {
        ToastUtil.showShort(this, R.string.password_empty)
        btn_login?.isClickable = true
        btn_login?.setText(R.string.login)
    }

    override fun showLoginSuccess() {
        CircularAnim().fullActivity(this, btn_login)
            .colorOrImageRes(R.color.colorPrimary)
            .go(object : CircularAnim.OnAnimationEndListener {
                override fun onAnimationEnd() {
                    doFinish(true)
                }
            })
    }

    override fun showLoginFailure(e: Throwable) {
        if (e is Error) {
            ToastUtil.showLong(this, e.errorMsg)
        } else {
            ToastUtil.showLong(this, R.string.network_error)
        }
        btn_login?.isClickable = true
        btn_login?.setText(R.string.login)
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
