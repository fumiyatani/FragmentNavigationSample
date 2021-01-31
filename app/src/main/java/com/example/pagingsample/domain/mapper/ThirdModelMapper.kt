package com.example.pagingsample.domain.mapper

import com.example.pagingsample.data.entity.ThirdEntity
import com.example.pagingsample.domain.model.ThirdModel

fun ThirdModel.convertToEntity(): ThirdEntity {
    return ThirdEntity(
        id, name
    )
}