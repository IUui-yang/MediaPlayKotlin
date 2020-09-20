package com.ypeng.mediaplaykotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ypeng.mediaplaykotlin.R
import com.ypeng.mediaplaykotlin.logic.nuit.FragmentTab
import com.ypeng.mediaplaykotlin.logic.util.LogTrace
import com.ypeng.mediaplaykotlin.ui.fragment.BaseFragment
import com.ypeng.mediaplaykotlin.ui.fragment.ListFragment
import com.ypeng.mediaplaykotlin.ui.fragment.PlayFragment

class MediaMainActivity : AppCompatActivity() {
    private val logTrace: LogTrace = LogTrace("MediaModule", ListFragment::class.simpleName)
    private lateinit var listFragment: ListFragment
    private lateinit var playFragment: PlayFragment
    private var clickNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData() {
        listFragment = ListFragment()
        playFragment = PlayFragment()
    }

    override fun onAttachFragment(fragment: Fragment) {
        logTrace.d("onAttachFragment", "fragment --> $fragment")
        super.onAttachFragment(fragment)
        when (fragment) {
            is ListFragment -> listFragment
            is PlayFragment -> playFragment
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logTrace.d("onNewIntent")
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun switchFragmentWithExtra(value: Int) {
        switchFragment(getFragmentTab(value))
    }

    /**
     * 获取当前要跳转的Fragment;
     */
    private fun getFragmentTab(position: Int): FragmentTab {
        var titleTab: FragmentTab? = null
        FragmentTab.values().forEach {
            if (it.ordinal == position) {
                titleTab = it
                return@forEach
            }
        }
        //如果titleTab为null，则返回FragmentTab.PLAY
        return titleTab ?: FragmentTab.PLAY
    }

    private fun switchFragment(tab: FragmentTab) {
        val fragmentId = tab.ordinal
        if (clickNumber != fragmentId) {
            clickNumber = fragmentId
        }
        logTrace.d("switchFragment", "fragmentId --> $fragmentId, clickNum --> $clickNumber")
        val tabString = tab.toString()
        when (tab) {

        }
    }

    private fun judgeFragment(
        fragmentTransaction: FragmentTransaction,
        fragment: BaseFragment,
        tag: String
    ) {
        val isAdded: Boolean = fragment.isAdded
        //获取Transaction;
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}