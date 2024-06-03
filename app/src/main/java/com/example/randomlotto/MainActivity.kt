package com.example.randomlotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.randomlotto.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberOne = findViewById<TextView>(R.id.number_one)
        val numberTwo = findViewById<TextView>(R.id.number_two)
        val numberThree = findViewById<TextView>(R.id.number_three)
        val numberFour = findViewById<TextView>(R.id.number_four)
        val numberFive = findViewById<TextView>(R.id.number_five)
        val numberSix = findViewById<TextView>(R.id.number_six)
        val numberBonus = findViewById<TextView>(R.id.number_bonus)
        val randomButton = findViewById<Button>(R.id.random_button)

        randomButton.setOnClickListener {
            // 랜덤한 번호 7개를 받아와서 오름차순으로 정렬한다.
            CoroutineScope(Dispatchers.IO).launch {
                val data = RetrofitInstance.service.getLottoNumber(num = 1103)
                Log.d("getLottoNumber","data: ${data}")
            }


//            // 각각의 View 연결
//            numberOne.text = lottoNumbers[0].toString()
//            numberTwo.text = lottoNumbers[1].toString()
//            numberThree.text = lottoNumbers[2].toString()
//            numberFour.text = lottoNumbers[3].toString()
//            numberFive.text = lottoNumbers[4].toString()
//            numberSix.text = lottoNumbers[5].toString()
//            numberBonus.text = lottoNumbers[6].toString()
//            //val lottoNumbersView = listOf(numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix, numberBonus)
//            //lottoNumbers.mapIndexed { index, number -> lottoNumbersView[index].text = number.toString() }
        }
    }

}