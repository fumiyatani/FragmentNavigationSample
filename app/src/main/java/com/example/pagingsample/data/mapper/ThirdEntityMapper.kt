package com.example.pagingsample.data.mapper

import com.example.pagingsample.data.entity.ThirdEntity
import com.example.pagingsample.domain.model.ThirdModel

fun ThirdEntity.convertToModel(): ThirdModel {
    return ThirdModel(
        id, name
    )
}