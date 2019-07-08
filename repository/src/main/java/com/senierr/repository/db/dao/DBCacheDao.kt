package com.senierr.repository.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.senierr.repository.db.entity.DBCache

/**
 * 数据库缓存接口
 *
 * @author zhouchunjie
 * @date 2018/3/29
 */
@Dao
interface DBCacheDao {

    @Query("SELECT * FROM DBCache WHERE `key` = :key")
    fun get(key: String): DBCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(dbCache: DBCache)

    @Query("DELETE FROM DBCache WHERE `key` = :key")
    fun deleteByKey(key: String)

    @Query("DELETE FROM DBCache")
    fun deleteAll()
}