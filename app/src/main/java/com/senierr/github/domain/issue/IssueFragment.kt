package com.senierr.github.domain.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senierr.base.support.ui.BaseFragment
import com.senierr.github.R

/**
 * 事件页面
 *
 * @author zhouchunjie
 * @date 2019/7/8 21:21
 */
class IssueFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}