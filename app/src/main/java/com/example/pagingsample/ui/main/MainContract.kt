package com.example.pagingsample.ui.main

import com.example.pagingsample.ui.main.viewdata.FirstListViewData
import com.example.pagingsample.ui.main.viewdata.SecondListViewData
import com.example.pagingsample.ui.main.viewdata.ThirdListViewData

interface MainContract {
    interface View {
        fun moveToFirstFragment(firstList: FirstListViewData)
        fun moveToSecondFragment(secondList: SecondListViewData)
        fun moveToThirdFragment(thirdList: ThirdListViewData)
        fun moveToPrevious(navigationType: NavigationType)
    }

    interface Presenter {
        fun onCreate(navigationType: NavigationType)
        fun onSelectFirst(id: Int)
        fun onSelectSecond(id: Int)
        fun onSelectThird(id: Int)
        fun onDestroy()
    }
}