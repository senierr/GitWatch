package com.senierr.github.domain.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.senierr.base.support.ui.BaseActivity
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.user.vm.UserInfoViewModel
import com.senierr.repository.entity.dto.HttpException
import com.senierr.repository.entity.dto.UserInfo
import kotlinx.android.synthetic.main.activity_login.tb_top
import kotlinx.android.synthetic.main.activity_user_info.*

/**
 * 登录页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class UserInfoActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, UserInfoActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var userInfoViewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
        initViewModel()

        userInfoViewModel.fetchUserInfo()
    }

    private fun initView() {
        setSupportActionBar(tb_top)
        tb_top?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewModel() {
        userInfoViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        userInfoViewModel.fetchUserInfoSuccess.observe(this, Observer {
            renderView(it)
        })
        userInfoViewModel.fetchUserInfoFailure.observe(this, Observer {
            if (it is HttpException) {
                ToastUtil.showShort(this, it.message)
            } else {
                ToastUtil.showShort(this, R.string.network_error)
            }
        })
    }

    /**
     * 渲染视图
     */
    private fun renderView(userInfo: UserInfo) {
        // 头像
        Glide.with(this).asBitmap().into(iv_avatar)
        // 昵称
        tv_name?.text = userInfo.name
        // 关注者
        tv_followers?.text = userInfo.followers.toString()
        // 关注的人
        tv_following?.text = userInfo.following.toString()
        // 邮箱
        tv_email?.text = userInfo.email
        // 博客
        tv_blog?.text = userInfo.blog
        // 自我介绍
        tv_bio?.text = userInfo.bio
    }
}
