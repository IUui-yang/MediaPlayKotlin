package com.ypeng.mediaplaykotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ypeng.mediaplaykotlin.R
import com.ypeng.mediaplaykotlin.logic.controller.MediaController
import com.ypeng.mediaplaykotlin.logic.entity.Song
import com.ypeng.mediaplaykotlin.logic.observer.MediaObserver
import com.ypeng.mediaplaykotlin.logic.util.LogTrace

/**
 * &lt;listFragment&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class ListFragment : Fragment(), MediaObserver {
    private val logTrace: LogTrace = LogTrace("Media", ListFragment::class.simpleName)

    //延迟加载，当对象需要使用时，才加载；
    private lateinit var mediaController: MediaController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_media_list, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()

    }

    private fun initEvent() {
        
    }

    private fun initData() {
        //如果mediaController没有初始化，则进行初始化；
        if (!::mediaController.isInitialized) {
            mediaController = MediaController.getInstance(context!!)
        }
        mediaController.addObserver(this)
        mediaController.requestSongList()
    }

    override fun songListResp(list: List<Song>) {
        logTrace.d("songListResp-->", list.toTypedArray().contentToString())
    }
}