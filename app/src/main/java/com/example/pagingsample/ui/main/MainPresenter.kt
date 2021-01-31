package com.example.pagingsample.ui.main

import com.example.pagingsample.domain.MainUseCase
import com.example.pagingsample.ui.main.mapper.convertToViewData
import com.example.pagingsample.ui.main.viewdata.FirstListViewData
import com.example.pagingsample.ui.main.viewdata.SecondListViewData
import com.example.pagingsample.ui.main.viewdata.ThirdListViewData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val useCase: MainUseCase,
) : MainContract.Presenter {

    private val job = Job()
    private val coroutineContext = CoroutineScope(Dispatchers.Main + job)

    private var navigationType = NavigationType.FRAGMENT_FIRST

    override fun onCreate(navigationType: NavigationType) {
        this.navigationType = navigationType

        coroutineContext.launch {

            useCase.initList()

            when (navigationType) {
                NavigationType.FRAGMENT_FIRST -> {
                    val firstList = FirstListViewData(
                        useCase.getFirstList().map { it.convertToViewData() }
                    )
                    view.moveToFirstFragment(firstList)
                }
                NavigationType.FRAGMENT_SECOND -> {
                    val secondList = SecondListViewData(
                        useCase.getSecondList().map { it.convertToViewData() }
                    )
                    view.moveToSecondFragment(secondList)
                }
                NavigationType.FRAGMENT_THIRD -> {
                    val thirdList = ThirdListViewData(
                        useCase.getThirdList().map { it.convertToViewData() }
                    )
                    view.moveToThirdFragment(thirdList)
                }
            }
        }
    }

    override fun onSelectFirst(id: Int) {
        coroutineContext.launch {
            if (id < 1) {
                navigationType = NavigationType.FRAGMENT_FIRST
                view.moveToStartActivity(navigationType)
            } else {
                val secondList = SecondListViewData(
                    useCase.getSecondList().map { it.convertToViewData() }
                )
                navigationType = NavigationType.FRAGMENT_SECOND
                view.moveToSecondFragment(secondList)
            }
        }
    }

    override fun onSelectSecond(id: Int) {
        coroutineContext.launch {
            if (id < 1) {
                navigationType = NavigationType.FRAGMENT_SECOND
                view.moveToStartActivity(navigationType)
            } else {
                val thirdList = ThirdListViewData(
                    useCase.getThirdList().map { it.convertToViewData() }
                )
                navigationType = NavigationType.FRAGMENT_THIRD
                view.moveToThirdFragment(thirdList)
            }

        }
    }

    override fun onSelectThird(id: Int) {
        coroutineContext.launch {
            navigationType = NavigationType.FRAGMENT_THIRD
            view.moveToStartActivity(navigationType)
        }
    }

    override fun onBackPressed() {
        coroutineContext.launch {

            when (navigationType) {
                NavigationType.FRAGMENT_FIRST -> {
                    // Firstの状態の時はStartActivityに戻る
                    view.moveToPreviousFirstFragment()
                }
                NavigationType.FRAGMENT_SECOND -> {
                    // Secondの状態の時はFirstに遷移する
                    val firstListViewData = FirstListViewData(
                        useCase.getFirstList().map { it.convertToViewData() }
                    )
                    navigationType = NavigationType.FRAGMENT_FIRST
                    view.moveToFirstFragment(firstListViewData)
                }
                NavigationType.FRAGMENT_THIRD -> {
                    // Thirdにいる時はSecondに遷移する
                    val secondList = SecondListViewData(
                        useCase.getSecondList().map { it.convertToViewData() }
                    )
                    navigationType = NavigationType.FRAGMENT_SECOND
                    view.moveToSecondFragment(secondList)
                }
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}