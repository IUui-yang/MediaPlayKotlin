package com.ypeng.mediaplaykotlin.logic.observer

import com.ypeng.mediaplaykotlin.logic.entity.Song

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
interface MediaObserver {
    /**
     * 回调返回歌曲列表；
     */
    fun songListResp(list: List<Song>)
}