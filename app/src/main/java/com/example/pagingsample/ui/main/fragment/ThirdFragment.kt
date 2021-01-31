package com.example.pagingsample.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentThirdBinding
import com.example.pagingsample.ui.main.viewdata.ThirdListViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

    private lateinit var bind: FragmentThirdBinding
    private lateinit var listener: Listener
    private lateinit var thirdListViewData: ThirdListViewData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thirdListViewData =
            arguments?.getSerializable(KEY_THIRD_LIST) as? ThirdListViewData
                ?: throw RuntimeException("データなし")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("Listenerを実装する")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_third,
            container,
            false
        )
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.navigateStartActivity.setOnClickListener {
            listener.selectThird(thirdListViewData.list[0].id)
        }

        bind.navigatePreviousActivity.setOnClickListener {
            listener.selectThird(thirdListViewData.list[1].id)
        }
    }

    companion object {
        private const val KEY_THIRD_LIST = "third_list"

        fun newInstance(thirdListViewData: ThirdListViewData) = ThirdFragment().apply {
            arguments = Bundle().also {
                it.putSerializable(KEY_THIRD_LIST, thirdListViewData)
            }
        }
    }

    interface Listener {
        fun selectThird(id: Int)
    }
}