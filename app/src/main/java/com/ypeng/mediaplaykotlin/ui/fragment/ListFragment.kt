package com.ypeng.mediaplaykotlin.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ypeng.mediaplaykotlin.R
import com.ypeng.mediaplaykotlin.logic.controller.MediaController
import com.ypeng.mediaplaykotlin.logic.entity.Song
import com.ypeng.mediaplaykotlin.logic.observer.MediaObserver
import com.ypeng.mediaplaykotlin.logic.util.LogTrace
import com.ypeng.mediaplaykotlin.ui.adapter.SongListAdapter
import kotlinx.android.synthetic.main.fragment_media_list.*

/**
 * &lt;listFragment&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class ListFragment : BaseFragment(), MediaObserver {
    private val logTrace: LogTrace = LogTrace("MediaModule", ListFragment::class.simpleName)

    companion object {
        val UPDATE_SONG_LIST = 1001
    }

    //延迟加载，当对象需要使用时，才加载；
    private lateinit var mediaController: MediaController
    private lateinit var songListAdapter: SongListAdapter
    private val songList = arrayListOf<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_media_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()
    }

    /**
     * apply plugin: 'kotlin-android-extensions'
     *
     * Kotlin中在build.gradle中引入，则可以省去findViewById();
     */
    private fun initEvent() {
        listTopBarIconArrows.setOnClickListener {

        }
        listBack.setOnClickListener {

        }

    }

    private fun initData() {
        //如果mediaController没有初始化，则进行初始化；
        if (!::mediaController.isInitialized) {
            mediaController = MediaController.getInstance(mContext)
        }
        mediaController.addObserver(this)
        mediaController.requestSongList()

        layoutMediaList.layoutManager = LinearLayoutManager(mContext)
        layoutMediaList.itemAnimator = DefaultItemAnimator()

        if (!::songListAdapter.isInitialized) {
            songListAdapter = SongListAdapter(mContext)
        }
        layoutMediaList.adapter = songListAdapter
    }

    @Suppress("UNCHECKED_CAST")
    private val handler = Handler {
        when (it.what) {
            UPDATE_SONG_LIST -> {
                songList.clear()
                songList.addAll(it.obj as List<Song>)
                songListAdapter.setSongList(songList)
            }
        }
        false
    }

    override fun songListResp(list: List<Song>) {
        logTrace.d("songListResp-->", list.toTypedArray().contentToString())
        val message = Message.obtain()
        message.obj = list
        message.what = UPDATE_SONG_LIST
        handler.sendMessage(message)
    }
}