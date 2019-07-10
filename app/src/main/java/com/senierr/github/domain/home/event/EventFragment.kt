package com.senierr.github.domain.home.event

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senierr.base.support.ui.BaseFragment
import com.senierr.github.R
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * 事件页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class EventFragment : BaseFragment() {

    private var pageIndex = 1
    private var pageSize = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onLazyCreated() {
        super.onLazyCreated()
    }

    private fun initView() {
        rv_event?.layoutManager = LinearLayoutManager(context)
        rv_event
    }


}