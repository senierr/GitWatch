package com.senierr.github.domain.home.me

import com.senierr.base.support.ext.observeOnMain
import com.senierr.base.support.ext.subscribeOnIO
import com.senierr.repository.Repository
import com.senierr.repository.service.api.IUserService
import io.reactivex.functions.BiFunction

/**
 * 事件页面逻辑
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
class MePresenter : MeContract.Presenter() {

    private val userService = Repository.getService<IUserService>()

    override fun getEvents(pageIndex: Int, pageSize: Int) {
        userService.getCurrentAccount()
            .zipWith(userService.getAuthorization(), BiFunction<String, String, Array<String>> { t1, t2 ->
                return@BiFunction arrayOf(t1, t2)
            })
            .flatMap {
                return@flatMap userService.getReceivedEvents(it[0], it[1], pageIndex, pageSize)
            }
            .subscribeOnIO()
            .observeOnMain()
            .subscribe({
                view?.showGetEventsSuccess(it)
            }, {
                view?.showGetEventsFailure(it)
            })
            .bindToLifecycle()
    }
}