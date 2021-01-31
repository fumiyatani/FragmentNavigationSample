package com.example.pagingsample.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pagingsample.R
import com.example.pagingsample.databinding.ActivityMainBinding
import com.example.pagingsample.ui.main.fragment.FirstFragment
import com.example.pagingsample.ui.main.fragment.SecondFragment
import com.example.pagingsample.ui.main.fragment.ThirdFragment
import com.example.pagingsample.ui.main.viewdata.FirstListViewData
import com.example.pagingsample.ui.main.viewdata.SecondListViewData
import com.example.pagingsample.ui.main.viewdata.ThirdListViewData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(),
    FirstFragment.Listener,
    SecondFragment.Listener,
    ThirdFragment.Listener,
    MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var bind: ActivityMainBinding

    private var navigationType = NavigationType.FRAGMENT_FIRST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bind.navigatePrevious.setOnClickListener {
            presenter.onBackPressed()
        }

        navigationType =
            intent.getSerializableExtra(NavigationTypeBundleKey.KEY_NAVIGATION_TYPE) as? NavigationType
                ?: NavigationType.FRAGMENT_FIRST
        presenter.onCreate(navigationType)
    }

    override fun selectFirst(id: Int) {
        presenter.onSelectFirst(id)
    }

    override fun selectSecond(id: Int) {
        presenter.onSelectSecond(id)
    }

    override fun selectThird(id: Int) {
        presenter.onSelectThird(id)
    }

    override fun moveToFirstFragment(firstList: FirstListViewData) {
        val fragment = FirstFragment.newInstance(firstList)
        showFragment(fragment)
    }

    override fun moveToSecondFragment(secondList: SecondListViewData) {
        val fragment = SecondFragment.newInstance(secondList)
        showFragment(fragment)
    }

    override fun moveToThirdFragment(thirdList: ThirdListViewData) {
        val fragment = ThirdFragment.newInstance(thirdList)
        showFragment(fragment)
    }

    override fun moveToPreviousFirstFragment() {
        finishForActivityResult(RESULT_CANCELED, null)
    }

    override fun moveToStartActivity(navigationType: NavigationType) {
        intent.putExtra(NavigationTypeBundleKey.KEY_NAVIGATION_TYPE, navigationType)
        finishForActivityResult(RESULT_OK, intent)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    companion object {
        fun createIntent(context: Context, navigationType: NavigationType) =
            Intent(context, MainActivity::class.java).also {
                it.putExtra(NavigationTypeBundleKey.KEY_NAVIGATION_TYPE, navigationType)
            }
    }
}

object NavigationTypeBundleKey {
    const val KEY_NAVIGATION_TYPE = "navigation_type"
}

enum class NavigationType {
    FRAGMENT_FIRST,
    FRAGMENT_SECOND,
    FRAGMENT_THIRD
}

fun Activity.finishForActivityResult(resultCode: Int, intent: Intent? = null) {
    intent?.let { setResult(resultCode, it) } ?: setResult(resultCode)
    finish()
}