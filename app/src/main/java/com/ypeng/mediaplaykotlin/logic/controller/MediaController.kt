@file:Suppress("UNCHECKED_CAST")

package com.ypeng.mediaplaykotlin.logic.controller

import android.content.Context
import com.ypeng.mediaplaykotlin.logic.datasource.MediaDataSource
import com.ypeng.mediaplaykotlin.logic.entity.Song
import com.ypeng.mediaplaykotlin.logic.interfaces.MediaInterface
import com.ypeng.mediaplaykotlin.logic.observer.MediaObserver
import com.ypeng.mediaplaykotlin.logic.util.BaseController
import com.ypeng.mediaplaykotlin.logic.util.BaseMessage

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class MediaController private constructor() : BaseController<MediaObserver>(), MediaInterface {

    //私有化构造函数；
    private constructor(context: Context) : this() {
        attachDataSource(MediaDataSource.getInstance(context))
    }

    companion object {
        private val instance: MediaController? = null

        @Synchronized
        fun getInstance(context: Context): MediaController {
            instance ?: MediaController(context)
            return instance!!
        }
    }

    override fun onDataChanged(msg: BaseMessage?) {
        when (msg?.messageWhat) {
            //获取列表，返回；
            MediaDataSource.MSG_UPDATE_SONG_LIST -> notifySongList(msg.messageObject as List<Song>)
        }

    }

    /**
     * 返回歌曲列表；
     */
    private fun notifySongList(list: List<Song>) {
        observerList.forEach { it.songListResp(list) }
    }

    /**
     * 请求歌曲列表；
     */
    override fun requestSongList() {
        sendMessage(MediaDataSource.MSG_UPDATE_SONG_LIST)
    }
}