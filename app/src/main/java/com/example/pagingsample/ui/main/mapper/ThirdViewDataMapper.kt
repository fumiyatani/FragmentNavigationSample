package com.example.pagingsample.ui.main.mapper

import com.example.pagingsample.domain.model.ThirdModel
import com.example.pagingsample.ui.main.viewdata.ThirdListViewData
import com.example.pagingsample.ui.main.viewdata.ThirdViewData

fun ThirdModel.convertToViewData(): ThirdViewData {
    return ThirdViewData(
        id, name
    )
}