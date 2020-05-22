package com.senierr.github.domain.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.senierr.base.support.ext.click
import com.senierr.base.support.ui.BaseActivity
import com.senierr.github.R
import com.senierr.github.domain.issue.IssueFragment
import com.senierr.github.domain.home.HomeFragment
import com.senierr.github.domain.user.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_bottom_navigation.*

/**
 * 主页面
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        vp_main?.adapter = MainPageAdapter(this)
        vp_main?.offscreenPageLimit = 2
        vp_main?.isUserInputEnabled = false
        vp_main?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onNavigationSelected(position)
            }
        })

        ll_navigation_home?.click {
            vp_main.currentItem = 0
        }
        ll_navigation_issue?.click {
            vp_main.currentItem = 1
        }
        ll_navigation_me?.click {
            vp_main.currentItem = 2
        }
    }

    private fun onNavigationSelected(index: Int) {
        iv_navigation_home?.isSelected = index == 0
        tv_navigation_home?.isSelected = index == 0

        iv_navigation_issue?.isSelected = index == 1
        tv_navigation_issue?.isSelected = index == 1

        iv_navigation_me?.isSelected = index == 2
        tv_navigation_me?.isSelected = index == 2
    }

    private inner class MainPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> HomeFragment()
            1 -> IssueFragment()
            2 -> MeFragment()
            else -> HomeFragment()
        }
    }
}