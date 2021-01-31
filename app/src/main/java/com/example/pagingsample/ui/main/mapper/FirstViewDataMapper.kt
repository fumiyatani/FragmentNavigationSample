package com.example.pagingsample.ui.main.mapper

import com.example.pagingsample.domain.model.FirstModel
import com.example.pagingsample.ui.main.viewdata.FirstListViewData
import com.example.pagingsample.ui.main.viewdata.FirstViewData

fun FirstModel.convertToViewData(): FirstViewData {
    return FirstViewData(
        id, name
    )
}