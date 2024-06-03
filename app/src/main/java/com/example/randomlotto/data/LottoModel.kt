package com.example.randomlotto.data


import com.google.gson.annotations.SerializedName

data class LottoModel(
    @SerializedName("drwNo")
    val drwNo: String, //회차 번호
    @SerializedName("drwNoDate")
    val drwNoDate: String, //로또 날짜
    @SerializedName("drwtNo1")
    val drwtNo1: String,
    @SerializedName("drwtNo2")
    val drwtNo2: String,
    @SerializedName("drwtNo3")
    val drwtNo3: String,
    @SerializedName("drwtNo4")
    val drwtNo4: String,
    @SerializedName("drwtNo5")
    val drwtNo5: String,
    @SerializedName("drwtNo6")
    val drwtNo6: String,
    @SerializedName("bnusNo")
    val bnusNo: String,
    @SerializedName("firstAccumamnt")
    val returnValue: String,
    @SerializedName("totSellamnt")
    val totSellamnt: String,
    @SerializedName("firstWinamnt")
    val firstWinamnt: String
) {
    fun toLottoData(): LottoData {
        return if (returnValue == "fail") {
            LottoData()
        } else {
            LottoData(
                gameNumber = drwNo,
                winNumbers = listOf(drwtNo1, drwtNo2, drwtNo3, drwtNo4, drwtNo5, drwtNo6),
                bonusNumber = bnusNo,
                data = drwNoDate,
                totalWinPrize = totSellamnt,
                winPrize = firstWinamnt
            )
        }
    }
}