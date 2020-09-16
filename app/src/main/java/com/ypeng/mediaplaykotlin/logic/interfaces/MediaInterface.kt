package com.ypeng.mediaplaykotlin.logic.interfaces

import com.ypeng.mediaplaykotlin.logic.observer.MediaObserver
import com.ypeng.mediaplaykotlin.logic.util.BaseControllerInterface

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
interface MediaInterface : BaseControllerInterface<MediaObserver> {
    /**
     * 请求播放列表；
     */
    fun requestSongList()
}