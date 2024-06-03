package com.example.randomlotto.api

import com.example.randomlotto.data.LottoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {
    @GET("common.do")
    suspend fun getLottoNumber(
        @Query("drwNo") num: Int, // 회차 정보
        @Query("method") method: String = "getLottoNumber"
    ): LottoModel
}
