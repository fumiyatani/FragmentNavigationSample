package com.example.pagingsample.ui.main.mapper

import com.example.pagingsample.domain.model.SecondModel
import com.example.pagingsample.ui.main.viewdata.SecondListViewData
import com.example.pagingsample.ui.main.viewdata.SecondViewData

fun SecondModel.convertToViewData(): SecondViewData {
    return SecondViewData(
        id, name
    )
}