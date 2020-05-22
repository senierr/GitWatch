package com.senierr.repository.entity.dto

/**
 *
 * @author zhouchunjie
 * @date 2020/5/22
 */
data class Keyword(
    val id: Long = 0,
    val link: String = "",
    val name: String = "",
    val order: Int = 0,
    val visible: Int = 0
)