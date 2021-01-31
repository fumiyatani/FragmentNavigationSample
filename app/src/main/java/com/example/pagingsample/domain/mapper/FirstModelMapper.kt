package com.example.pagingsample.domain.mapper

import com.example.pagingsample.data.entity.FirstEntity
import com.example.pagingsample.domain.model.FirstModel

fun FirstModel.convertToEntity(): FirstEntity {
    return FirstEntity(
        id, name
    )
}