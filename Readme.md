## 🈺 주요 기능

### 카카오톡 API

- 카카오톡 로그인 기능 구현
- 사용자 카카오톡 프로필, 이름의 정보를 얻는다.
- 카카오 페이를 활용한 결제 기능을 구현

### **Google API**

- Google Map을 이용한 카페 위치 정보
- 마커를 통하여 전체 카페의 위치를 보여줌

### **Database**

- Room Database를 활용한 내부 데이터 베이스 모델
  - 내부 데이터 베이스는 어플이 지워져도 괜찮은 데이터가 저장됨.
  - ex ) 카페 정보 데이터등
- Fire Database를 활용한 외부 데이터 베이스 모델
  - 외부 데이터 베이스는 어플이 지워져도 남아 있어야 하는 데이터가 저장됨.
  - ex ) 각 사용자의 포인트 정보등

### 기타 구현

- ViewPager를 이용한 슬라이더
  - 자동 슬라이더
  - 화면 슬라이더
- 다양한 라이브러리를 활용
  - Volley와 gson을 이용한 API request, response 구현
  - Firebase와 Room을 이용한 내,외부 데이터베이스 구현
  - Kakao, Google을 이용한 사용자 서비스 구현

## 📚 사용된 라이브러리

1. Firebase (DB)
2. Room (DB)
3. ~~Lottie (예정. UI animation)~~
4. Kakao API (API)
5. Volley (HTTP 통신)
6. glide (이미지 파싱)
7. gson (Json 파싱)
8. Google API (MAP)
