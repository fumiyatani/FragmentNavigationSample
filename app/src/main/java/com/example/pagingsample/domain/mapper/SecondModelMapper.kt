package com.example.pagingsample.domain.mapper

import com.example.pagingsample.data.entity.SecondEntity
import com.example.pagingsample.domain.model.SecondModel

fun SecondModel.convertToEntity(): SecondEntity {
    return SecondEntity(
        id, name
    )
}