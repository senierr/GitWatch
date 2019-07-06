package com.senierr.repository.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 *
 * @author zhouchunjie
 * @date 2019/7/6
 */
@Entity(tableName = "DBCache")
data class DBCache(
    @PrimaryKey
    var key: String,
    var value: String? = null
) {
    @Ignore
    constructor() : this("")
}