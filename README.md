# Rest Api Kotlin

## 적용할 목록 2023.04 ( 목표 )
- Rest API Response 구조 ( 완료 )
- ExceptionAdvice 설정 ( 완료  )
- JPA , QueryDsl 설정 ( H2 ) ( JPA H2 연동 완료 ,QueryDsl 진행중 )  
- Spring boot Security 적용 
  - 테스트 데이터 구성 
    - User DB : 회원정보
      1. 구성 : 회원번호 , 회원아이디 , 회원이름 , 회원비밀번호(암호화 X),등록일 ( Entity 완료 )
      2. API 목록 : 회원 가입 (완료),회원 로그인(진행중),회원 정보 수정
    - UserToken DB : 현재 로그인되어 있는 회원 (개인용이기에 DB)
      1. 구성 :  회원번호  , 연결된 회원키  , 생성 시간 ( Entity 완료 ) 
      2. API 목록 : Token 인증 및 재발급 
  - Exception 추가 
    - 기본에러 ( 상태 200 , 에러 메시지 전달 타입 )  
    - 회원 처리별 에러 ( 2000 ~ 2100 , 에러 메시지 )


