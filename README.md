# Rest Api Kotlin

## 적용할 목록 2023.04 ( 목표 )
- Rest API Response 구조 ( 완료 )
- ExceptionAdvice 설정 ( 완료  )
- JPA , QueryDsl 설정 ( H2 ) ( JPA H2 연동 완료 ,QueryDsl 진행중 )  
- Spring boot Security 적용 
  - 테스트 데이터 구성 
    - User DB : 회원정보
      1. 구성 : 회원번호 , 회원아이디 , 회원이름 , 회원비밀번호(암호화 X),등록일, 마지막수정일 ( Entity 완료 )
      2. API 목록 : 회원 가입 (완료),회원 로그인 (완료 ),회원 정보 수정 (완료)
    - UserToken DB : 현재 로그인되어 있는 회원 (개인용이기에 DB)
      1. 구성 :  회원번호  , 연결된 회원키  , 생성 시간 ( Entity 완료 ) 
      2. API 목록 : Token 인증 및 재발급  ( 완료 )
  - Exception 추가 
    - 기본에러 ( 상태 200 , 에러 메시지 전달 타입 )  
    - 회원 처리별 에러 ( 2000 ~ 2100 , 에러 메시지 )


버전 다운그레이드 및 변경   
- spring boot 3.0.5 -> 2.7.2
- plugins  1.7.x -> 1.6.21 
- jakarta -> javax 로 변경 

java -> kotlin 작성해 보니 좋았던 점
- 코드가 확실히 줄수가 줄어듬  
- unchecked exception 코드 작성시 편함

하지만.. jakarta -> javax 로 변경한 이유..아직은.. 최신버전되면서 javax가 빠진건 알겠는데 javax querydsl 작성시 음.. 섞어 쓰니 문제 되서 일단.. 버전 다운그레이드.. 이후 다시 
jakarta 로 변경해 봐야 하겠지 만 querydslConfig 생성이 정상 작동안해서 우선 다운그레이드 나중에 시간이 되면다시 .. 


이후 해야 할것.. 안할듯..  
docker 설정 
Redis 설정 ( docker )
DB Mysql 설정  ( docker  )
DB Hikari 설정 
DB Master/Slave 및 여러 DB 연결 설정 
CustomerUserDetails에 대한 데이터를 DB 가 아닌 레디스 로 변경 
querydsl 다양한 쿼리 조건 
LocalDataTime Json 아웃푼 포멧 변경 - yyyy-MM-dd HH:mm:ss ( 완료 )

postman api - api_test_list.json 