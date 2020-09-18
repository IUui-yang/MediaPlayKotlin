package com.ypeng.mediaplaykotlin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
class BaseFragment : Fragment() {
    private val logTrace = LogTrace("MediaModule", BaseFragment::class.simpleName)

    /**
     * 判断是否已经又Fragment被添加；
     */
    private var isAdd: Boolean = false

    /**
     * 父类初始化，子类可以直接使用
     */
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logTrace.i("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logTrace.i("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logTrace.i("onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        logTrace.i("onResume")
    }

    override fun onPause() {
        super.onPause()
        logTrace.i("onPause")
    }

    override fun onStop() {
        super.onStop()
        logTrace.i("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logTrace.i("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logTrace.i("onDestroy")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context.applicationContext
        logTrace.i("onAttach")
    }

    /**
     * Fragment生命周期，在onDestroy之后
     * Fragment真正销毁
     */
    override fun onDetach() {
        super.onDetach()
        logTrace.i("onDetach")
    }

}