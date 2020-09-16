package com.ypeng.mediaplaykotlin.logic.entity

/**
 * &lt;数据实体类&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/Media]
 */
data class Song(
    /**
     * 歌手
     */
    val singer: String,
    /**
     * 歌曲名
     */
    val song: String,
    /**
     * 歌曲的地址
     */
    val path: String,
    /**
     * 歌曲的长度
     */
    val duration: Int,
    /**
     * 歌曲的大小
     */
    val size: Long,
    /**
     * 专辑
     */
    val album: String
)

/**
 * id - id
 *
 * path - 地址
 *
 * status - 状态
 */
data class HaveMusic(val id: Int, val path: String, val status: String)