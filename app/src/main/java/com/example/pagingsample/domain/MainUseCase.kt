package com.example.pagingsample.domain

import com.example.pagingsample.data.dao.MainDao
import com.example.pagingsample.data.entity.FirstEntity
import com.example.pagingsample.data.entity.SecondEntity
import com.example.pagingsample.data.entity.ThirdEntity
import com.example.pagingsample.data.mapper.convertToModel
import com.example.pagingsample.domain.mapper.convertToEntity
import com.example.pagingsample.domain.model.FirstModel
import com.example.pagingsample.domain.model.SecondModel
import com.example.pagingsample.domain.model.ThirdModel
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val dao: MainDao
) {
    suspend fun initList() {
        val result = dao.getFirstList()
        if (result.isNotEmpty()) return

        val firstList: List<FirstEntity> = listOf(
            FirstModel(0, "Mainに戻る").convertToEntity(),
            FirstModel(1, "SecondFragmentに遷移").convertToEntity()
        )
        val secondList: List<SecondEntity> = listOf(
            SecondModel(0, "Mainに戻る").convertToEntity(),
            SecondModel(1, "ThirdFragmentに遷移").convertToEntity()
        )
        val thirdList: List<ThirdEntity> = listOf(
            ThirdModel(0, "0Mainに戻る").convertToEntity(),
            ThirdModel(1, "1Mainに戻る").convertToEntity()
        )

        dao.initFirst(firstList)
        dao.initSecond(secondList)
        dao.initThird(thirdList)
    }

    suspend fun getFirstList(): List<FirstModel> {
        return dao.getFirstList().map { it.convertToModel() }
    }

    suspend fun getSecondList(): List<SecondModel> {
        return dao.getSecondList().map { it.convertToModel() }
    }

    suspend fun getThirdList(): List<ThirdModel> {
        return dao.getThirdList().map { it.convertToModel() }
    }
}