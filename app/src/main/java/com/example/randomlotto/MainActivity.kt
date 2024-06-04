package com.example.randomlotto

import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.Spanned
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.randomlotto.data.LottoData
import com.example.randomlotto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var number: Int = 1102
    private lateinit var binding: ActivityMainBinding
    private val lottoController by lazy { LottoController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchLottoData(number)
        with(binding) {
            searchButton.setOnClickListener {
                number = searchView.text.toString().toInt()
                fetchLottoData(number)
            }

            arrowLeft.setOnClickListener {
                number = number.dec()
                fetchLottoData(number)
            }

            arrowRight.setOnClickListener {
                number = number.inc()
                fetchLottoData(number)
            }
        }
    }

    private fun fetchLottoData(number: Int) {
        lottoController.getLottoNumber(number, { lottoData ->
            updateUI(lottoData)
        }, { _ ->
            Toast.makeText(
                this@MainActivity,
                "로또 정보를 가져오는데 실패했습니다. 잠시 후 다시 이용해주세요.",
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun updateUI(data: LottoData) {
        with(binding) {
            winResultTextView.text = getHtmlText(R.string.winning_result, number.toString())
            dateView.text = data.data // 날짜
            val listWinNumberView =
                listOf(number1Text, number2Text, number3Text, number4Text, number5Text, number6Text)
            listWinNumberView.mapIndexed { index, tv ->
                val numbers = data.winNumbers
                tv.text = if (numbers.isNotEmpty()) numbers[index] else ""
            }
            numberBonusText.text = data.bonusNumber
            val prize = data.totalWinPrize.toBillion()
            winAmountText.text = getHtmlText(R.string.win_prize, prize)
            val prizeOne = data.winPrize.toBillion()
            win1AmountText.text = getHtmlText(R.string.win_prize_1, prizeOne)
        }
    }

    private fun String.toBillion(): String {
        return if (this != "") (this.toLong() / 100_000_000).toString() else this
    }

    private fun getHtmlText(@StringRes id: Int, data: String): Spanned {
        val text: String = getString(id, data)
        return Html.fromHtml(text, FROM_HTML_MODE_COMPACT)
    }
}