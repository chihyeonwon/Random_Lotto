package com.example.randomlotto.data

private const val EMPTY_STRING = "-"

data class LottoData(
    val gameNumber: String = EMPTY_STRING,
    val winNumbers: List<String> = emptyList(),
    val bonusNumber: String = EMPTY_STRING,
    val totalWinPrize: String = EMPTY_STRING,
    val winPrize: String = EMPTY_STRING,
    val winnerCount: String = EMPTY_STRING,
    val data: String = EMPTY_STRING
)