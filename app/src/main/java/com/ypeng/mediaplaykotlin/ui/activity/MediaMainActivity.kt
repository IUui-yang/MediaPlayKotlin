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
import kotlinx.android.synthetic.main.activity_main.*

class MediaMainActivity : AppCompatActivity() {
    private val logTrace: LogTrace = LogTrace("MediaModule", ListFragment::class.simpleName)
    private lateinit var listFragment: ListFragment
    private lateinit var playFragment: PlayFragment
    private var clickNumber: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initEvent()
    }

    private fun initEvent() {
        play_fragment.setOnClickListener { switchFragment(FragmentTab.PLAY) }
        list_fragment.setOnClickListener { switchFragment(FragmentTab.LIST) }
    }

    private fun initData() {
        listFragment = ListFragment()
        playFragment = PlayFragment()
    }

    /**
     * 使用AddFragment时，防止界面重叠；
     */
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
        //进入界面，首先进入 PlayFragment;
        if (clickNumber != -1) {
            switchFragmentWithExtra(clickNumber)
        } else {
            switchFragmentWithExtra(0)
        }

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
        //获取Transaction;
        val transaction = supportFragmentManager.beginTransaction()

        val fragmentId = tab.ordinal
        if (clickNumber != fragmentId) {
            clickNumber = fragmentId
        }
        logTrace.d("switchFragment", "fragmentId --> $fragmentId, clickNum --> $clickNumber")
        val tabString = tab.toString()
        when (tab) {
            FragmentTab.PLAY -> judgeFragment(transaction, playFragment, tabString)
            FragmentTab.LIST -> judgeFragment(transaction, listFragment, tabString)
        }
        transaction.commitAllowingStateLoss()
    }

    private var currentFragment: BaseFragment? = null

    private fun judgeFragment(fragmentTransaction: FragmentTransaction, fragment: BaseFragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val isAdded: Boolean = fragment.isAdded
        if (currentFragment == null && isAdded) {
            hideAllFragment(fragmentTransaction)
        }
        if (!isAdded && fragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(R.id.container, fragment, tag)
            fragment.isAdd = true
        } else {
            fragmentTransaction.show(fragment)
        }
        currentFragment ?: if (fragment != currentFragment) {
            fragmentTransaction.hide(currentFragment!!)
        }
        currentFragment = fragment
    }

    private fun hideAllFragment(fragmentTransaction: FragmentTransaction) {
        logTrace.d("hideAllFragment")
        fragmentTransaction.hide(playFragment)
        fragmentTransaction.hide(listFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}