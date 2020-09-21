package com.ypeng.mediaplaykotlin.ui.adapter

import android.content.Context
import android.content.pm.FeatureGroupInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ypeng.mediaplaykotlin.R
import com.ypeng.mediaplaykotlin.logic.entity.Song

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class SongListAdapter(private val context: Context) : RecyclerView.Adapter<SongListViewHolder>() {

    private val mSongList = arrayListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder =
            SongListViewHolder.SongViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_item_fragment,
                    parent, false))


    //这里定义了一个viewHolder的密封类，可供多布局使用；
    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        if (position < mSongList.size) {
            val song = mSongList[position]
            when (holder) {
                is SongListViewHolder.SongViewHolder -> {
                    holder.musicSong.text = song.song
                    holder.musicSinger.text = song.singer
                    holder.musicDuration.text = song.duration.toString()
                }
            }
            holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(it, position) }
        }
    }

    override fun getItemCount() = mSongList.size

    /**
     * 设置数据源；
     */
    fun setSongList(songList: List<Song>) {
        mSongList.clear()
        mSongList.addAll(songList)
    }

    /**
     * Kotlin为变量自动提供get set  方法，如果set方法中无自定义逻辑，使用默认set方法；
     */
    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}