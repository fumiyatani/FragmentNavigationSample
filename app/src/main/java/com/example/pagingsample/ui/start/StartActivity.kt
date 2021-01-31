package com.example.pagingsample.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pagingsample.R
import com.example.pagingsample.databinding.ActivityStartBinding
import com.example.pagingsample.ui.main.MainActivity
import com.example.pagingsample.ui.main.NavigationType
import com.example.pagingsample.ui.main.NavigationTypeBundleKey

class StartActivity : AppCompatActivity() {

    private lateinit var bind: ActivityStartBinding

    private var navigationType = NavigationType.FRAGMENT_FIRST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_start)

        bind.selectedNavigationTypeTextView.text = navigationType.name

        bind.clearButton.setOnClickListener {
            bind.selectedNavigationTypeTextView.text = "FRAGMENT_FIRST"
            navigationType = NavigationType.FRAGMENT_FIRST
        }

        bind.navigateMainActivity.setOnClickListener {
            val intent = MainActivity.createIntent(this, navigationType)
            startActivityForResult(intent, RESULT_CODE_MAIN_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK) {
            return
        }

        if (requestCode == RESULT_CODE_MAIN_ACTIVITY) {
            navigationType = data
                ?.getSerializableExtra(NavigationTypeBundleKey.KEY_NAVIGATION_TYPE) as? NavigationType
                ?: NavigationType.FRAGMENT_FIRST
            bind.selectedNavigationTypeTextView.text = navigationType.name
        }
    }

    companion object {
        private const val RESULT_CODE_MAIN_ACTIVITY = 10000
    }
}