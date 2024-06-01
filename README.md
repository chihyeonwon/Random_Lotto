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
## 24.05.21
![2024-05-31 18;37;18](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/c8932da2-0d78-4a59-931a-db15e88b84ba)
```
레이아웃 : LinearLayout horizontal textView 4개 각각 백그라운드 지정, gravity center
generateRandomNumbers 메서드에서 난수 생성해서 Set 데이터 리스트에 담는다.

알고리즘 : sort() Set 리스트의 데이터들을 오름차순으로 정렬한다.
Set 중복된 번호는 생성되면 안된다.
```
## **01. 2차시 오늘 배울 것**

<aside>
✔️ **Open API 연결을 통해 실제 Data와 연결해보기!**

</aside>

- **이번 2차시 목표는요?**
    
    ☑️ LottoApp에 생명을 불어 넣기!
    
    - 1주 차에 배운 로또 앱은 랜덤 번호만 생성할 뿐 실제 로또 번호를 모르기 때문에 실용성이 떨어져요
    - 복권 서버에서 당첨 정보를 직접 받아올 순 없을까요?

### ⁉️  서버에서 실제 로또 정보를 가지고 오려면 어떻게 해야 하나요?

- 비장의 기술을 사용하려면 세 가지 준비물이 필요해요. 자 그럼 시작해 볼까요?
    - Retrofit, Json, Gson
 
## **02. Retrofit, 웹서버와 연결하는 마법의 도구**

<aside>
✔️ 웹서버에서 실제 Data와 연결해 주는 마법의 도구 Retrofit에 대해서 알아보아요!

</aside>

- **REST API**란? (**RE**presentational **S**tate **T**ransfer)
    - 웹에서 서버와 클라이언트 간의 통신을 위한 표준 방법
    - 웹에서 데이터를 주고받는 규칙을 정의
    - 클라이언트는 서버에 정보를 요청하고, 서버는 그 정보를 제공
    - 비유:
        - **스타*스**
            - 고객(클라이언트):  “아메리카노, 톨싸이즈, 아이스 주세요”를 요청
            - 스타벅스(진짜 커피를 가진 서버): 커피를 완성 후→ “A-21번 고객님 주문하신 음료 나왔습니다. Pickup 창구로 와주세요” 답변
    - **구성 요소**
        - URL 엔드 포인트 : 웹상의 자원(텍스트, 이미지, 문서 등)을 나타내는 URL 링크
        - HTTP 동사 : 서버에게 URL 엔드 포인트의 리소스에 대해 수행하고자 하는 동작을 알림.
            - **`POST`**는 새로운 리소스를 생성
            - **`GET`**은 기존 리소스를 조회
        - 본문(Body) : 리소스의 속성과 값이 포함됨
            - JSON, XML, plain text, HTML, 또는 특정 약속된 형식 등
            - 안드로이드 앱 개발에서는 JSON을 많이 사용함
         
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/a744e7fd-ebdc-4157-8ee2-cd29318f9f44)
