package com.ypeng.mediaplaykotlin.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ypeng.mediaplaykotlin.R

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [1.0]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
sealed class SongListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    class SongViewHolder(view: View) : SongListViewHolder(view) {
        val musicState: ImageView = view.findViewById(R.id.item_music_state)
        val musicSong: TextView = view.findViewById(R.id.item_music_song)
        val musicSinger: TextView = view.findViewById(R.id.item_music_singer)
        val musicDuration: TextView = view.findViewById(R.id.item_music_duration)
    }

}