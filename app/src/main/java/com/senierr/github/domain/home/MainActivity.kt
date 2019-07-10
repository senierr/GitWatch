package com.senierr.github.domain.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.senierr.base.support.ext.setGone
import com.senierr.base.support.ui.BaseActivity
import com.senierr.github.R
import kotlinx.android.synthetic.main.activity_main.*

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

        initView()
    }

    private fun initView() {
        vp_main.adapter = PageAdapter()
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                tb_top.setGone(p0 == 3)
            }
        })

        bnv_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_home -> vp_main.currentItem = 0
                R.id.tab_hierarchy -> vp_main.currentItem = 1
                R.id.tab_official_accounts -> vp_main.currentItem = 2
                R.id.tab_me -> vp_main.currentItem = 3
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    inner class PageAdapter : FragmentPagerAdapter(supportFragmentManager) {

        private val fragments = arrayOf(HomeFragment(), HierarchyFragment(), OfficialAccountsFragment(), MeFragment())

        override fun getItem(p0: Int): Fragment = fragments[p0]

        override fun getCount(): Int = 4
    }
}
