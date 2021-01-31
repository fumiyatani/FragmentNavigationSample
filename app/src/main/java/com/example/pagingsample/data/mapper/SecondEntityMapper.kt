package com.example.pagingsample.data.mapper

import com.example.pagingsample.data.entity.SecondEntity
import com.example.pagingsample.domain.model.SecondModel

fun SecondEntity.convertToModel(): SecondModel {
    return SecondModel(
        id, name
    )
}