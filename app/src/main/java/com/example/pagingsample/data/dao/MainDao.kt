package com.example.pagingsample.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pagingsample.data.entity.FirstEntity
import com.example.pagingsample.data.entity.SecondEntity
import com.example.pagingsample.data.entity.ThirdEntity

@Dao
interface MainDao {
    @Insert
    suspend fun initFirst(firstList: List<FirstEntity>)

    @Insert
    suspend fun initSecond(secondList: List<SecondEntity>)

    @Insert
    suspend fun initThird(thirdList: List<ThirdEntity>)

    @Query("SELECT * FROM tbl_first")
    suspend fun getFirstList(): List<FirstEntity>

    @Query("SELECT * FROM tbl_second")
    suspend fun getSecondList(): List<SecondEntity>

    @Query("SELECT * FROM tbl_third")
    suspend fun getThirdList(): List<ThirdEntity>
}