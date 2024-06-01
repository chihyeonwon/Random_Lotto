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
- **Retrofit이란?** 
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/c00f7e20-0957-47ae-89f7-d93f0d9d97db)
  
    - 안드로이드 및 자바 애플리케이션을 위한 인기 있는 HTTP 클라이언트 라이브러리
    - REST API를 쉽게 사용할 수 있도록 도와줌
    - REST API의 HTTP 요청을 안드로이드에 맞는 자바/코틀린 인터페이스로 변환하는 것이 주 목적
    - 서버와 데이터를 주고받기 위한 코드를 간단하고 효율적으로 작성할 수 있는 비장의 기술!!
    - 특징
        1. **간결한 코드**: 인터페이스 기반의 선언적 방식을 사용하여 API를 정의해서 복잡한 네트워크 작업을 간단한 메서드 호출로 변환 가능
        2. **데이터 변환**: JSON, XML 등 다양한 데이터 형식을 자동으로 객체로 변환해 줌
        3. **유연성**: 다양한 커스터마이징 옵션과 함께 사용할 수 있는 추가 라이브러리들이 많음
### ☑️ Retrofit 사용 준비물

1. **Gradle 의존성 추가:**
    - 프로젝트의 **`build.gradle`** 파일에 Retrofit 및 JSON 파싱을 위한 컨버터 라이브러리(Gson, Moshi 등)를 추가함
```kotlin   
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Gson Converter
implementation("com.squareup.retrofit2:converter-gson:2.9.0")  
 ```

2. **인터넷 권한 설정:**
    - **`AndroidManifest.xml`** 파일에 인터넷 사용 권한을 추가함.
```kotlin
<uses-permission android:name="android.permission.INTERNET" />    
```

3. **API 인터페이스 정의:**
    - Retrofit으로 통신할 웹 API의 엔드 포인트에 해당하는 메서드를 HTTP 요청 방식과 URL를 어노테이션으로 명시하여 정의함.
```kotlin  
interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("q") city: String): WeatherResponse
}
```

4. **Retrofit 인스턴스 생성:**
    - Retrofit 빌더를 활용하여 기본 URL, 컨버터 팩토리 등을 설정하여 Retrofit 인스턴스를 생성함.
  
```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

5. **API 호출 및 응답 처리:**
    - 정의한 API 인터페이스를 활용하여 네트워크 요청을 수행하고, 콜백이나 코루틴을 사용하여 응답을 처리함.
  
```kotlin
interface WeatherService  {
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("q") city: String): WeatherResponse
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(WeatherService  ::class.java)

// API 호출 및 응답 처리
CoroutineScope(Dispatchers.IO).launch {
    val response = api.getWeather("Seoul")
    withContext(Dispatchers.Main) {
        // UI 업데이트
    }
}
```
### ⁉️  Retrofit 준비물에 Gson이라는 Converter가 추가되던데 이건 뭐야?

- 서버에서 받아온 정보는 JSON 형식이라 안드로이드는 알아들을 수가 없어. 그걸 해석해 주는 친구가 바로?!!! Gson이지

![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/43ff826d-28fd-4d48-8b26-d7527f86197e)

## **03.** JSON, 데이터의 `비밀 코드` 해독하기

- **Json이란?**
    - JSON (JavaScript Object Notation)은 데이터를 교환하기 위한 경량의 텍스트 기반 포맷
    - 사람이 읽고 쓰기 쉬우며, 기계가 파싱하고 생성하기도 쉬운 구조
    - 널리 사용되는 데이터 포맷 중 하나로, 웹 서비스와 모바일 애플리케이션 간의 데이터 전송에 특히 많이 사용됨
    - 특징
        - **경량:** 데이터를 간결하게 표현할 수 있어요.
        - **언어 독립적:** 다양한 프로그래밍 언어에서 사용할 수 있어요.
        - **이해하기 쉬움:** 사람이 읽고 이해하기 쉬워요.
    - 구조
        - **객체 (Object):** 이름-값 쌍의 모음. 객체는 중괄호 **`{}`**로 둘러싸여 있으며, 각 이름은 문자열이고 값은 다양한 타입이 될 수 있음
```kotlin    
{
    "name": "John Doe",
    "age": 30,
    "isStudent": false
}
```
배열 (Array): 값의 순서화된 모음. 배열은 대괄호 []로 둘러싸여 있으며, 배열 내의 값들은 어떤 타입이든 될 수 있음
```kotlin
[
    "Apple",
    "Banana",
    "Cherry"
]
```

- **Gson이란?**
    - Google에서 제공하는 Java 라이브러리
    - JSON 데이터를 자바 객체로 변환하거나, 자바 객체를 JSON으로 변환하는 데 사용
    - 이 과정을 각각 '역직렬화(Deserialization)' 및 '직렬화(Serialization)'라고 함
    - **JSON 파싱:**  Gson 라이브러리를 사용하여 JSON 문자열을 Kotlin 객체(data class)로 변환
    - **@SerializedName 어노테이션 :** Kotlin 필드와 JSON 키 이름이 다를 경우 매핑
```kotlin
data class Person(
    @SerializedName("person_name")
    val personName: String
)
```

- **Kotlin data class file from Json 사용법**
    - 이 플러그인을 통해 Json data를 쉽게 kotlin data class로 변환 가능
```xml
로또 URL(브라우저 주소창에 넣어 보세요)
https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=1010
```

