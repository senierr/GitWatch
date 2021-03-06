package com.senierr.github.domain.user

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.senierr.base.support.ui.BaseFragment
import com.senierr.base.support.utils.LogUtil
import com.senierr.base.support.utils.ToastUtil
import com.senierr.github.R
import com.senierr.github.domain.user.vm.UserInfoViewModel
import com.senierr.repository.entity.dto.HttpException
import com.senierr.repository.entity.dto.UserInfo
import kotlinx.android.synthetic.main.fragment_me.*
import java.lang.NullPointerException

/**
 * 我的页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class MeFragment : BaseFragment(R.layout.fragment_me) {

    private lateinit var userInfoViewModel: UserInfoViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()

        userInfoViewModel.fetchUserInfo()
    }

    private fun initView() {

    }

    private fun initViewModel() {
        userInfoViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        userInfoViewModel.fetchUserInfoSuccess.observe(this, Observer {
            renderView(it)
        })
        userInfoViewModel.fetchUserInfoFailure.observe(this, Observer {
            LogUtil.logE(Log.getStackTraceString(it))
//            if (it is HttpException) {
//                ToastUtil.showShort(context, it.message)
//            } else {
//                ToastUtil.showShort(context, R.string.network_error)
//            }
        })
    }

    /**
     * 渲染视图
     */
    private fun renderView(userInfo: UserInfo) {
//        // 头像
//        Glide.with(this).load(userInfo.avatarUrl).into(iv_avatar)
//        // 昵称
//        tv_name?.text = userInfo.name
//        // 关注者
//        tv_followers?.text = userInfo.followers.toString()
//        // 关注的人
//        tv_following?.text = userInfo.following.toString()
//        // 邮箱
//        tv_email?.text = userInfo.email
//        // 博客
//        tv_blog?.text = userInfo.blog
//        // 自我介绍
//        tv_bio?.text = userInfo.bio
    }
}