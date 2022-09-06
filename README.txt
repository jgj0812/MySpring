Gradle Sync : Ctrl + Shift + o

모듈 설치 : Ctrl + Shift + a

A001 : Application.java
A002 : HelloController.java
A003 : test/ HelloControllerTest.java
A004 : main/Application.java 실행
A005 : build.gradle 에 롬복 추가
A006 : main/ web/dto/HelloResponseDto.java
A007 : src/ web/dto/HelloResponseDtoTest.java
A008 : build.grale에 롬복 상세 설정
A009 : HelloController.java
A010 : HelloControllerTest.java
A011 : build.gradle 에 JPA사용하겠다고 설정
A012 : domain.posts.Posts.java
A013 : domain.posts.PostsRepository.java interface 를 만든다.
A014 : test/ domain.posts.PostsRepositoryTest.java
A015 : resources/application.properties 을 생성해서 로그를 남긴다.

B001 : web.dto.PostsSaveRequestDto.java
B002 : web.PostsApiController.java 이것을 먼저 만드는 것은 바람직하지 않음을 확인하기 위한 용도
B003 : study.service.posts.PostsService.java
B004 : web.PostsApiController.java PostsService에 DTO를 이용해 저장 명령 수행
B005 : test/web.PostsApiControllerTest.java에서 단위테스트
B006 : PostsUpdateRequestDto.java 수정하기 DTO
B007 : PostsResponseDto.java 응답을 위한 DTO
B008 : Posts.java에 update()메소드 추가
B009 : PostsService.java 에 update() 추가
B010 : PostsApiController.java에 수정하기 Mapping 추가
B011 : PostsApiControllerTest.java에서 단위테스트
B012 : application.properties에 H2DB console 설정
B013 : domain.BaseTimeEntity.java 추상 클래스
B014 : Posts.java가 BaseTimeEntity 클래스를 상속 받도록 기능 추가
B015 : application.java에 Auditing을 알려준다.(Annotation으로)
B016 : PostsRepositoryTest.java에서 Auditing을 단위 테스트


게시판
    id, title, content, author, 글쓴 시간, 글 최종 수정시간, 비밀번호, 사용자id, ....
    JPA Auditing 기술 제공
        글쓴 시간, 글 수정한 시간정보를 관리해야할 때, 시스템이 처리해주는 방법
        생성, 수정을 자동으로 감시(Auditing)하면서 데이터베이스를 이용하면 편리하다.

    insert into posts (title, content, author, time) values ('aaa', 'bbb', 'ccc', now());
    domain패키지에 BaseTimeEntity클래스 생성해야한다.

H2DB
    spring boot에서 제공하는 Built-in database
        별도의 설치가 필요없다.
        데이터베이스로 H2DB를 사용하겠다고 선언
    in-memory DB  ---------- Memory, 일상적인 배포용으로는 적합하지 않다.
                             개발용으로 매우 적합

    RDBMS         --------- File System
        Oracle
            + MySql
            + MariaDB
                + SQLite : SQL Light
            + MSSql
    Memory DB

JPA : Java Persistence API (자바 지속성 API)
기존 MyBatis 에서 SQL Mapper 를 이용해서 DB 쿼리를 작성하고는 했다.
--> 프로그램의 대부분의 SQL 작업하는 것이 일의 대부분
    SQL 처리하는 코드 작성하는 시간이 증가하는 문제
    객체지향적으로 처리할 수 있는 방법이 없을까?

    ORM(Object Relational Mapping) 기술이 나왔는데,
    대표적인 것이 JPA 이다.
    JPA 는 표준 자바 ORM(프로그램 - JPA - DB 의 인터페이스 역할)
    ORM 은 객체를 매핑하고 SQL Mapper 는 쿼리를 매핑
    SQL 에 따른 종속성에서 벗어나겠다.

    JPA 를 사용한다 = Java Collection 에서 데이터를 넣었다 빼게 만들겠다.
    CRUD (Create, Read, Update, Delete)
        저장 : jpa.persit(객체)
        조회 : Member member = jpa.find(키)
        수정 : member.setName("변경할이름");
                String name ==> getName(), setName()
        삭제 : jpa.remove(member)

    장점 : 유지보수가 간단하다.
                객체가 변경되면, 알아서 DB 에 Update Query 수행해준다.
                기존 : 필드명이 변경되면 모든 SQL 수정

    조회
    +-----------------------------+
    |  Java Application           |
    |                             |
    |     +------------------+    |
    |     | JPA              |    |
    |     | +--------------+ |    |            +---------+
    |     | |  JDBC        + +----+---SQL--->  |   DB    |
    |     | |  API         + +<---+---Ret----  |         |
    |     | +--------------+ |    |            +---------+
    |     +------------------+    |
    +-----------------------------+


    저장
    +---------------------------------------------+
    |                                             |
    |   +-----------+              +------------+ |
    |   |           |   (Entity)   | JPA        | |
    |   |   Member  | --Persist -->|  +-------+ | |
    |   |   DAO     |              |  | JDBC  | + +---(insert SQL) ---> DB
    |   |           |              |  +-------+ | |
    |   +-----------+              +------------+ |
    |                                             |
    +---------------------------------------------+

    저장
    +---------------------------------------------+
    |                                             |
    |   +-----------+              +------------+ |
    |   |           |              | JPA        | |
    |   |   Member  |--find(id)--->|  +-------+ | |                     +------+
    |   |   DAO     |              |  | JDBC  | + +---(insert SQL) ---> |  DB  |
    |   |           |    (entity)  |  | API   | | |<------ Ret -------- |      |
    |   |           |<-- (object) -|  +-------+ | |                     +------+
    |   +-----------+              +------------+ |
    |                                             |
    +---------------------------------------------+

JSON : JavaScript Object Notation

{
    "name": "홍길동",
    "age": 12,
}

{ "name": "홍길동", "age": 12, }

{
    "name": "홍길동",
    "age": 12,
    "company": {
        "name": "임픽스",
        "http": "http://impix.co.kr",
        "address": "서울",
    },
}

{
    "employee": {
        "name": "홍길동",
        "age": 12,
    },
    "company": {
        "name": "임픽스",
        "http": "http://impix.co.kr",
        "address": "서울",
    },
}

{
    "employee": [
        { "name": "홍길동", "age": 12, "home": "서울", },
        { "name": "이순신", "age": 22, "home": "경남", },
        { "name": "강감찬", "age": 32, "home": "강원", },
        { "name": "광개토", "age": 42, "home": "서울", },
    ],
    "company": {
        "name": "임픽스",
        "http": "http://impix.co.kr",
        "address": "서울",
    },
}