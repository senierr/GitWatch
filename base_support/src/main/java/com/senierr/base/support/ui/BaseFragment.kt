package com.senierr.base.support.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Fragment基类
 *
 * @author zhouchunjie
 * @date 2018/5/28
 */
open class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId)