package com.ashe.calculatorexample

import androidx.room.*

@Dao
interface HistoryDao {
    @Insert // 테이블에 데이터 삽임
    fun insert(history: History)

    @Update // 테이블에 데이터 업데이트
    fun update(history: History)

    @Delete // 테이블에 있는 데이터 삭제
    fun delete(history: History)

    // 전체 계산 History 조회
    @Query("SELECT * FROM History")
    fun getAll(): List<History>
}