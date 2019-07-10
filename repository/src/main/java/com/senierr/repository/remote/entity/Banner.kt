package com.senierr.repository.remote.entity

/**
 *
 * @author zhouchunjie
 * @date 2019/7/10 20:10
 */
data class Banner(
    var desc: String? = null,
    var id: Int? = null,
    var imagePath: String? = null,
    var isVisible: Int? = null,
    var order: Int? = null,
    var title: String? = null,
    var type: Int? = null,
    var url: String? = null
)