package com.example.pagingsample.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentSecondBinding
import com.example.pagingsample.ui.main.viewdata.SecondListViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var bind: FragmentSecondBinding
    private lateinit var listener: Listener
    private lateinit var secondListViewData: SecondListViewData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        secondListViewData =
            arguments?.getSerializable(KEY_SECOND_LIST) as? SecondListViewData
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
            R.layout.fragment_second,
            container,
            false
        )
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.navigateStartActivity.setOnClickListener {
            listener.selectSecond(secondListViewData.list[0].id)
        }

        bind.navigateThirdFragment.setOnClickListener {
            listener.selectSecond(secondListViewData.list[1].id)
        }
    }

    companion object {
        private const val KEY_SECOND_LIST = "second_list"

        fun newInstance(secondListViewData: SecondListViewData) = SecondFragment().apply {
            arguments = Bundle().also {
                it.putSerializable(KEY_SECOND_LIST, secondListViewData)
            }
        }
    }

    interface Listener {
        fun selectSecond(id: Int)
    }
}