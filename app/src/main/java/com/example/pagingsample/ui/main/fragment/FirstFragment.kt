package com.example.pagingsample.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentFirstBinding
import com.example.pagingsample.ui.main.viewdata.FirstListViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var bind: FragmentFirstBinding
    private lateinit var listener: Listener
    private lateinit var firstListViewData: FirstListViewData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstListViewData =
            arguments?.getSerializable(KEY_FIRST_LIST) as? FirstListViewData
                ?: throw RuntimeException("データがない")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )
        return bind.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("Listenerを実装する")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.navigateStartActivity.setOnClickListener {
            listener.selectFirst(firstListViewData.list[0].id)
        }

        bind.navigateSecondFragment.setOnClickListener {
            listener.selectFirst(firstListViewData.list[1].id)
        }
    }

    companion object {
        private const val KEY_FIRST_LIST = "first_list"

        fun newInstance(firstListViewData: FirstListViewData) = FirstFragment().apply {
            arguments = Bundle().also {
                it.putSerializable(KEY_FIRST_LIST, firstListViewData)
            }
        }
    }

    interface Listener {
        fun selectFirst(id: Int)
    }
}