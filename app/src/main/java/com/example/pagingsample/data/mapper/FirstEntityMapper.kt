package com.example.pagingsample.data.mapper

import com.example.pagingsample.data.entity.FirstEntity
import com.example.pagingsample.domain.model.FirstModel

fun FirstEntity.convertToModel(): FirstModel {
    return FirstModel(
        id, name
    )
}