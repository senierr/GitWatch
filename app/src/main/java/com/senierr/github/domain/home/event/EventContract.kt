package com.senierr.github.domain.home.event

import com.senierr.base.support.mvp.BasePresenter
import com.senierr.base.support.mvp.BaseView
import com.senierr.repository.remote.entity.Event

/**
 * 事件契约类
 *
 * @author zhouchunjie
 * @date 2019/6/27
 */
interface EventContract {

    interface View : BaseView {
        fun showGetEventsSuccess(data: MutableList<Event>)
        fun showGetEventsFailure(e: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getEvents(pageIndex: Int, pageSize: Int)
    }
}