⚛🔅**실습 목표 :** 빨간 테두리 박스 안의 UI 구성 + Button
## 결과물 레이아웃 
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/cfb4ad55-f1bf-4f72-8060-5d6bc8e92a7e)

- 배경 색깔 : 회색 (**#EEF3F6)**
- 동그라미 당첨 번호 6개 , 보너스 번호 1개: TextView(size : 20dp, bold)+ color background
- + 아이콘 : ImageView
- 하얀색 사각 라운드 배경
- 랜덤 번호 구하기
    - 라이브러리란?
        - 재사용 가능한 코드의 집합
        - 라이브러리에서 제공하는 특정 기능을 구현하는 데 도움을 줌
        - **`import`** 키워드 뒤에 해당 라이브러리의 패키지 경로를 명시
        - 개발자는 기존에 검증된 코드를 활용하여 개발 시간과 노력을 절약할 수 있음. 속도 Up!
        - 자주 사용되는 표준 라이브러리: Math, Random
        - 안드로이드에서 자주 사용되는 라이브러리: Retrofit, Glide, Room, Coroutines
        - **Math**  : 수학 관련 작업을 위한 함수와 상수를 제공 (**kotlin.math)**
        - **Random** : 난수를 생성하는 데 사용 (**kotlin.random.Random)**
        
        ```kotlin
        import kotlin.math.*
        
        println("PI: $PI")
        println("Square root of 9: ${sqrt(9.0)}")
        println("Sine of PI/2: ${sin(PI / 2)}")
        println("Cosine of PI/2: ${cos(PI / 2)}")
        ```
        
        ```kotlin
        import kotlin.random.Random
        
        fun generateLottoNumbers(): Set<Int> {
            val numbers = mutableSetOf<Int>()
            while (numbers.size < 6) {
                val randomNumber = Random.nextInt(1, 46) // 1부터 45까지의 숫자 중 랜덤 선택
                numbers.add(randomNumber)
            }
            return numbers
        }
        
        val lottoNumbers = generateLottoNumbers()
        println("lotto winner: $lottoNumbers")
        ```
