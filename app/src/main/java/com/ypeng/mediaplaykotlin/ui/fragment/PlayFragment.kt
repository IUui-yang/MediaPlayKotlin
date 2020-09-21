package com.ypeng.mediaplaykotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ypeng.mediaplaykotlin.R
import com.ypeng.mediaplaykotlin.logic.controller.MediaController
import com.ypeng.mediaplaykotlin.logic.entity.Song
import com.ypeng.mediaplaykotlin.logic.observer.MediaObserver
import kotlinx.android.synthetic.main.fragment_media_play.*

/**
 * &lt;playFragment&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class PlayFragment : BaseFragment(), MediaObserver {
    private lateinit var mediaController: MediaController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_media_play, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()
    }

    private fun initData() {
        if (!::mediaController.isInitialized) {
            mediaController = MediaController.getInstance(mContext)
        }
    }

    private fun initEvent() {
        button_play_pause.setOnClickListener { }
    }

    override fun songListResp(list: List<Song>) {

    }

}