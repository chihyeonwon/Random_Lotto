⚛🔅**실습 목표 :** 빨간 테두리 박스 안의 UI 구성 + Button 
- 웹서버와의 통신 방식인 REST API의 기본 개념과 원리를 이해할 수 있다.     
- Retrofit을 사용하여 REST API를 호출하고, Gson을 통해 JSON 데이터를 처리하는 방법을 알 수 있다.    
- JSON 데이터 포맷의 구조를 파악하고, 이를 어떻게 활용하는지 알 수 있다.    
- 실제 로또 정보를 웹서버에서 받아와 안드로이드 앱에 연동시키는 과정을 직접 구현할 수 있다.

  
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
1. File > Setting > Plugins > JSON To Kotlin Class > Install > OK 선택    
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/af5f46e7-667d-4654-a030-0d029900d98b)
2. 원하는 folder 선택 후 File > New > Kotlin data class File from JSON 선택     
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/2ddb302e-b0d5-4f27-a5be-cde0c5c5a429)
3. JSON 정보 붙여 넣기        
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/d5c378f2-548b-404b-9a95-346a89adcac4)
4. Class Name 입력 후 Generate 선택     
5. 자동으로 생성된 data class 확인

## 04. 당첨 번호를 찾아서: 실제 로또 당첨 데이터와 만나다   
1. 라이브러리 추가: 먼저, 프로젝트의 build.gradle 파일에 Retrofit 라이브러리와 데이터 변환을 위한 Gson 컨버터 라이브러리를 추가하세요.
```kotlin
dependencies {
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```
2. **Retrofit 인스턴스 생성:** Retrofit Builder를 사용하여 Retrofit Instance를 생성하고, BaseUrl과 Data Converter를 설정
```kotlin
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://www.dhlottery.co.kr/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: LottoApi by lazy { retrofit.create(LottoApi::class.java) }
}
```
3. API 인터페이스 정의: 서비스의 각 HTTP 엔드 포인트에 대해 메서드를 정의하는 인터페이스를 생성
```kotlin
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {
    @GET("common.do")
    suspend fun getLottoNumber(
        @Query("drwNo") num: Int,    // 회차 정보
        @Query("method") method: String = "getLottoNumber"
    ): LottoModel
}
```

4. API 호출:  인터페이스를 통해 API 호출을 수행하고, 응답을 처리
```kotlin
CoroutineScope(Dispatchers.IO).launch {
    val data = RetrofitInstance.api.getLottoNumber(num = 1010)
}
```

5. 인터넷 권한 추가: AndroidManifest.xml 파일에 인터넷 권한을 추가해야 네트워크 요청을 할 수 있습니다.
```kotlin
<uses-permission android:name="android.permission.INTERNET" />
```

6. 로컬 테스트 서버나 아직 **HTTPS**를 지원하지 않는 백엔드 서버와의 통신이 필요한 경우 **`usesCleartextTraffic`** 속성 추가가 필요한 경우도 있음
```kotlin
android:usesCleartextTraffic="true"
```

## 05. 실습 A-2: 로또 번호 서비스 앱 완성하기

로또 앱 UI 구성하기
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/b5f16e9c-6280-4db6-a60e-904bd1c7491d)

Open API 연동을 통한 실제 로또 번호 조회    

데이터 표시 및 사용자 인터랙션 구현    

## 전보다 새롭게 알게된 내용
```
클래스와 유사한 object 클래스는 앱 프로젝트에서 1개만 생성 가능하다. (싱글톤 패턴의 오브젝트)

Retrofit 객체를 빌더타입으로 생성한다.
빌더에는 base url, .client (로그를 찍어서 다양한 로그를 사용 가능)
.gson 컨버터가 json을 gson으로 변경해준다.

https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=1010

LottoApi 에서 엔드포인트 common.do에서 Get으로 가져올 것인데 method가 getLottoNumber 이면서 drwNo는 (num으로 개명) 1010 인것을 가져온다. 

api 호출 : MainActivity에서 random으로 로또번호를 generate 하는 함수를 지우고 생성한 RetrofitInstance의 api를 호출해서 getLottoNumber의 매개변수로 회차번호인
num = 1111을 넣어준다. 매개변수가 두 개 필요한데 뒤에 method는 getLottoNumber쿼리 어노테이션으로 주어져있다.

앱이 죽었는데 죽은 이유를 알고 싶으면 검색창에 fat을 입력한다. fatal의 줄임말
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/e6f3c576-d2af-49ec-8222-855848f1df16)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/e0565ce8-a88e-45be-a3dc-91f441ef0301)
```
Logcat에서 앱이 죽었을 때 죽은 이유만 따로 모아서 보고 싶었는데 fat 키워드를 사용하면 되었다.

SecurityException: Permission denied (missing INTERNET permission?)
인터넷 권한을 추가하지 않으면 안된다. -> androidManifest 파일에 인터넷 권한을 추가한다.

회차번호 num이 1111을 받아왔을 때 로그캣화면이다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/3b0b7b0d-dc27-4b15-864d-64afa1c68e65)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/38467bd9-05f7-4308-91cf-fad72047f05f)
```
만약 회차번호 num 이 개발일 기준 최신 로또회차는 1122회인데 그보다 큰 값인 1130을 넣어주면 데이터가 null 이 들어온다.
(당연히 미래 회차번호를 넣어서 당첨 번호 데이터가 왔으면 이미 부자가 되었겠지만..)
이럴때는 예외처리를 해줘야한다. 항상 데이터를 받아올 때는 이 데이터가 뜻하는 것과 null이 되는 상황을 예외처리해야 한다를 생각한다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/35b85e4b-181b-4e00-82a9-d9952c0822ec)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/98695a42-c7c9-4794-a5a2-a00fe4c5f618)
```
만약 엔드포인트의 주소에 https:// security가 적용되어있지 않고 http://로 시작한다면 암호화 처리가 되지 않아 시큐리티 정책에 어긋난다는
not permitted by network security policy 오류가 발생한다.

이를 해결하려면 즉 http의 데이터도 받고자 한다면(로컬 테스트 서버나 아직 HTTPS를 지원하지 않는 백엔드 서버와의 통신이 필요한 경우)
usesCleartextTraffic ="true"속성 추가가 필요한 경우도 있음 (androidmanifest의 application 속성에 넣어줌)
하지만 안쓰면 좋다.(보안에 위협)
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/94a61767-f063-4917-962c-c3af493c9656)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/0d0d747c-0667-47a1-ae8a-cb22215c40c8)
```
gravity 속성은 위치에 대한 정렬, layout_gravity 속성은 뷰 안에 있는 텍스트의 위치

디자인탭에서 색깔을 지정할 때 스포이드 기능이 포함되어 있다.

디자인탭의 searchView 를 활용해서 쉽게 검색창을 구현할 수 있다.

단 searchView 안의 돋보기 아이콘을 삭제하려면 코드에서 강제로 삭제해주는 코드를 사용해야한다.
이런 번거로움을 해소하려고 EditText를 쓸 수가 있다. searchView와 유사

ImageView의 백그라운드 회색 동그라미, src에는 동그라미 안에 들어가는 이미지

src 이미지의 크기를 동그라미 안에서 조절하려고 한다. -> scale Type 조절한다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/21d4c8fd-59de-4ace-8026-f8d0021b68d8)
```
클래스명을 바꾸려고 할 때 클래스위에서 rename하면 파일명도 같이 다 바뀐다.
변수명도 변수 위에서 rename하면 이 변수를 사용하는 곳에서 다 바뀐다. <- 기존에 replace로 찾아서 바꾸는 것보다 편리하다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/f7b08f25-b1f3-46b2-bbca-d8e5b6562d15)
```
데이터모델에서 String의 빈 값을 뜻하는 "-"을 EMPTY_STRING 으로 상수화하여 표현했다.(코드의 가시성)
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/c0082718-8e14-4b32-a21b-6e3907396e60)
```
json에서 데이터를 가져올 때 키값의 string이 이상할 때가 있다.
이 경우에 데이터 변수명을 알아보기 쉬운 변수명으로 바꾸면서 값을 초기화한다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/1eb1fce5-3a7a-45dc-9a4d-606d1b90bce2)
```
binding을 쓰면 findViewById를 사용하지 않고 레이아웃의 요소를 아이디로 찾아서 쓸 수 있다. (편리성을 위해서)
메인액티비티에 로드한다. setContentView(binding.root)
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/afbe8656-947f-4bde-8143-92be6d74b9a7)
```
with(binding) { } binding.1, binding.2, binding3이라면 with(binding) { 1.~  2.~ 3. ~ } binding 을 생략할 수 있다.
```
## 컨트롤러
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/c68d4b35-795a-44b9-93c3-cd1afab853ee)
```
컨트롤러는 Retrofit 인스턴스를 가져와서 서버에 있는 데이터값을 받아온다.
서버에서 정보를 받아올 때는 시간이 걸릴 수 있다. 이것을 비동기 적으로 처리하기 위해서 코루틴을 사용한다.
서버에 요청을 보내놓고 다른 것을 할 수 있기에 효율적이다.

요청에 대한 결과가 왔을 때 그 때 다시 화면에 뿌려준다.

runCatching <- 앱이 죽었을 때를 처리하기 위해 넣는 안전한 보호 코드

Dispatcher.IO 오래 걸리는 비동기적 서비스에서 로또 번호를 실제로 받아오는 getLottoNumber 함수를 실행

onSuccess 성공한다면 로또 데이터를 toLottoData() 보기좋은 형식으로 변환한 후에 getLottoNumber의 결과로 받아온다.
```
## strings.xml
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/eee24540-f32b-4b77-83c2-800cd9f19030)
```
<b></b>는 사이의 문자를 볼드체로 표현한다.
<big></big>은 글씨를 크게 적는다.

%1$s <- 숫자 문자열이 들어가면 문자열에 직접 적용을 해준다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/34e76a10-5263-4a39-a302-6ef0b7dbee1a)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/460afbc8-ca8b-49d1-b35b-d5cb5c66a508)
```
html 태그가 적용된 텍스트가 그대로 적용되어 출력된다.

mapIndexed <- 리스트의 인덱스를 사용할 수 있는 메서드 map은 못씀
당첨 번호 6개를 넣어놨고 번호가 비어있지 않으면 번호를 넣는다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/e47600d2-3f0c-4a49-be00-4203afc63bdf)
```
전체 금액을 0을 제외하고 몇 억원인지 변환하기 위해 to Billion 함수를 사용한다.
데이토포맷을 함수명으로 사용가능하다.

abcd.tomillion() 이렇게 있을 때 abcd -> String 이므로 String.tomillion() 으로 함수를 정의할 수 있고
함수 안에서 this는 String 즉 abcd를 의미하게 된다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/16dd3923-d2ee-41c2-93e8-23b59f8fb1b1)
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/b2fa8f82-3eef-4091-85e5-6882d1a9bf69)
```
@color/컬러명 <- 자주쓰이는 컬러를 colors.xml에 작성하여 사용할 수 있다.
```
![image](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/d87e925d-0106-48b3-8cfe-462d7f56718a)
```
dec() <- decrease 회차.toInt()해서 회차가 줄어듬 -1
inc() <- increase 회차가 늘어남 +1

로또 회차에 따른 데이터를 가져온다음 fetchLottoData() 데이터 가져오기에 성공하면 updateUI ui를 업데이트한다.
```
## 결과물의 기술
![2024-06-03 19;37;54](https://github.com/chihyeonwon/Random_Lotto/assets/58906858/eace38de-a40a-4b4c-bb34-e798a192df3c)
```
로또 회차를 검색창에 입력하면 해당 로또 회차의 당첨 결과, 날짜, 당첨 번호 6개 (5+ 보너스1개)와 1등 총 당첨 정보와 금액을
서버에서 받아와서 보여준다. 
```
