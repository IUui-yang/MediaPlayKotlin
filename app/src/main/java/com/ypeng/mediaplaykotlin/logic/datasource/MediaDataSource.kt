package com.ypeng.mediaplaykotlin.logic.datasource

import android.content.Context
import android.provider.MediaStore
import com.ypeng.mediaplaykotlin.logic.entity.Song
import com.ypeng.mediaplaykotlin.logic.util.BaseDataSource
import com.ypeng.mediaplaykotlin.logic.util.BaseMessage
import com.ypeng.mediaplaykotlin.logic.util.LogTrace

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ypeng
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class MediaDataSource private constructor(private val context: Context) : BaseDataSource(context) {
    //Kotlin 使用companion object来声明静态变量和静态方法；
    private val logTrace: LogTrace = LogTrace("Media", MediaDataSource::class.simpleName)

    private val flag = hashMapOf<Int, Int>()
    private val cacheSongList = mutableListOf<Song>()

    companion object {

        //public static final 常量；
        const val MSG_UPDATE_SONG_LIST = 0x01

        //public static final 常量；
        const val MSG_UPDATE_MUSIC_STAT = 0x02

        //对象私有化；如果需要公有public  则const val 修饰；
        private var mInstance: MediaDataSource? = null

        @Synchronized
        fun getInstance(context: Context): MediaDataSource {
            mInstance ?: MediaDataSource(context)
            //返回的mInstance不可以为null
            return mInstance!!
        }
    }

    /**
     * 从手机中获取歌曲名称，路径，等；
     */
    private fun getMusicData(): List<Song> {
        val songList = mutableListOf<Song>()
        val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC
        )
        while (cursor?.moveToNext() as Boolean) {
            val song = Song(
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
            )
            //添加歌曲到列表；
            songList.add(song)
        }
        cacheSongList.addAll(songList)
        //给songList中的歌曲添加当前状态；
        for (i in 0 until songList.size) {
            flag[i] = 0
        }
        //释放资源
        cursor.close()
        return songList
    }

    //when 语句：
    //当when中既有需要执行的语句，没有返回，又有有返回值得判断时，则直接在执行语句中加return;
    //当when中只有return语句时，则可以直接使用return when语句的值；
    override fun fetchData(msg: BaseMessage?): Any? {
        logTrace.d("fetchData", "what--> ${msg?.messageWhat} + obj--> ${msg?.messageObject}")
        when (msg?.messageWhat) {
            MSG_UPDATE_SONG_LIST -> getMusicData()
            MSG_UPDATE_MUSIC_STAT -> return 2
        }
        return null
    }
}