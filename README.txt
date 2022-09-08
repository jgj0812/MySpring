- 강사님 깃허브 프로젝트
https://github.com/eurekasolution/MySpring

- 실습자 깃허브 프로젝트
https://github.com/jgj0812/MySpring

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


    프로그램 개발 순서 DTO -> Service -> Controller

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
        spring.h2.console.enabled = true 설정후 Application을 실행
        http://localhost:8080/h2-console 에 접속
B013 : domain.BaseTimeEntity.java 추상 클래스
B014 : Posts.java가 BaseTimeEntity 클래스를 상속 받도록 기능 추가
B015 : application.java에 Auditing을 알려준다.(Annotation으로)
B016 : PostsRepositoryTest.java에서 Auditing을 단위 테스트
Mustache를 위한 설정 확인
    File > Setting > Editor > File Types > Handlebars/Mustache > File Name Patters에
    *.mustache 이 등록되어 있어야 한다.
B017 : study.web.IndexController.java
B018 : IndexControllerTest.java 단위테스트
B019 : src/main/resources/templates/index.mustache 파일 생성

C001 : index.mustache 글쓰기 버튼
C002 : posts-save.mustache 글쓰기 화면을 위한 HTML 페이지(Mustache Page)
C003 : IndexController.java에 /posts/save를 등록
C004 : /resources/static/css/style.css
C005 : /resources/static/js/index.js
C006 : footer.mustache에 index.js 파일 실행 코드 추가
        글쓰기 성공하면 http://localhost:8080/h2-console에 접속해 데이터 확인
C007 : index.mustache에 글 목록 보여주기
C008 : posts.PostsRepository.java 목록에 보여줄 Posts 객체 만들기
C009 : dto.PostsListResponseDto.java 리스트 객체를 전송하는 Dto를 만든다.
C010 : PostsService.java에서 posts 객체를 만들어서 보낸다.
C011 : IndexController.java : Service에서 전달한 DTO를 가져온 결과를 posts로 index.mustache에 전달하는 코드 추가
C012 : index.mustache 파일에서 제목에 링크 추가, 내용보기 작성
C013 : IndexController.java : /posts/show/1 처리를 해준다.
C014 : posts-show.mustache 글 내용보기하는 페이지 작성
C015 : posts-show.mustache 수정 버튼에 이동할 페이지 링크 작성
C016 : posts-update.mustache 페이지를 만드는데, post-save.mustache와 거의 같다. (post/update)
C017 : index.js 실제 수정처리를 한다.
C018 : IndexController.java : 글 수정하기 화면을 구성하는 처리
C019 : index.js 삭제 처리를 한다.
C020 : PostsService.java 삭제하는 것을 처리할 예정
C021 : PostsApiController.java 삭제를 위한 DELETE
C022 : BaseTimeEntity.java 글 저장, 변경된 시간 정보를 내가 원하는 형태로 변경
C023 : web.dto.PostsListsResponseDto.java 날짜 데이터가 바뀌었으므로 이곳을 String으로 변경

    현재까지 테스트는 H2DB를 이용했다. 메모리가 삭제되는 문제가 발생했다.
    MariaDB로 대체를 해보겠습니다.
    https://www.apachefriends.org/download.html XAMPP
    http://localhost/phpmyadmin/
    DBName : spring, user : spring, pw: 1111

C024 : build.gradle h2db -> Maria db 변경 -> sync
C025 : Applicaton.properties : H2 -> Maria
C026 : posts 테이블은 만들어 줘야함. JPA가 MariaDB를 바로 제어하지 못함.

create table posts (
    id  bigint(20) auto_increment,
    title varchar(200),
    content text(4000),
    author char(200),
    created_date datetime,
    modified_date datetime,
    primary key(idx)
);


D001 : MariaDB -> H2DB 복원해서 동작확인
D002 : 클라우드를 위한 실행파일 만들기
            Gradle -> Task -> build -> bootJar 더블클릭 (Success)
            프로젝트 트리 -> build -> libs -> jar파일이 클라우드에 FTP로 올려서 실행할 파일
                파일명은 build.gradle의 상단에 있는 SNAPSHOT...에서 결정된다.

D003 : AWS 보안 설정
        FTP : 20, 21 TCP
        HTTP : 8080 TCP
        SSH : 22 TCP (기본설정)

        PUTTY-GEN 다운받아서 설치
            KEY-PAIR(AWS.pem이라고 가정하면)

        https://www.puttygen.com/download-putty

            Generate -> Load(Aws.pem) 넣고, --> Save Private Key 버튼 클릭해서 AWS.ppk로 저장

        PUTTY
            connect -> SSH -> Auth -> Browse에서 PPK파일 선택
            Session -> Host Name : ec2-43-XXX-XX-XXX.ap-northeast-2.compute.amazonaws.com
                    Save Session 에 사용할 이름 -> Save
            해당 이름을 더블클릭 했을때 AWS에 콘솔 접속이 되어야 한다.

        FileZilla로 jar파일 업로드
            파일 밑에 있는 컴퓨터 그림 -> 새 사이트
                        -> 프로토콜 : SFTP
                        호스트 : 아마존 주소(Public 도메인)
                        로그인 유형 : 키 파일 선택
                        사용자 : ubuntu
                        키 파일 : AWS.ppk 선택
                        -> 확인

        AWS 설정은 되었다고 가정하고, 파일질라로 SFTP로 jar파일을 올렸다고 가정 후 할일
              sudo apt-get update
              sudo apt-get install openjdk-8-jdk
              java -version
              java -jar (jar 파일) &
              ps aux
              sudo kill -9 (프로세스 번호 PID)
              ps aux
              sudo nohup java -jar (jar 파일) &
              history

D004 : MariaDB를 사용할 수 있도록 변경(build.gradle, application.properties)
D005 : Posts.java : hit 기능 추가
D006 : PostsRepository.java 읽을 때 마다 hit 증가
D007 : PostsService.java : hit를 업데이트하는 updateHit() 정의
D008 : PostsListResponseDto.java 에서 hit 기능 추가
D009 : IndexController.java 에서 hit 기능 추가
D010 : index.mustache 에서 화면에 hit 기능 추가
    Application 실행시 에러가 난다. MariaDB는 JPA에서 바로 컨트롤이 안되기 때문에
    hit 필드를 다음과 같이 명령으로 추가한다.
    alter table posts add hit int(10) default '0' after author;
    실행하면 읽을 때 마다 hit값이 증가한다.
D011 : posts-show.mustache 에 읽기 값을 표현
D012 : PostsResponseDto.java 에 hit를 추가한다. 원래는 D007 이후에 작업을 했어야함.
D013 : index.mustache에 로그인 여부에 따라서 로그인/로그아웃 버튼 작성
D014 : Posts.java (D005 흉내내기)
D015 : PostsRepository.java (D006)
D016 : PostsService.java (D007)
D017 : PostsListResponseDto.java (D008)
D018 : PostsApiController.java (D009 변경, 왜냐하면 Api쪽이라서)
D019 : posts-show.mustache에 추천버튼
D020 : index.js 추천 기능 추가
D021 : posts-show.mustache 추천 표시부 처리
D022 : PostsResponseDto 에 rec 추가후, DB 추가

material-icons 아이콘 있는곳 : https://fonts.google.com/icons
<span class="material-icons">아이콘</span>
Inserting the icon 에서 태그안의 글자를 입력한다.

Mustache 간단한 문법 정리
{{! comment }}

1. 변수 : {{ 변수이름 }}

{
    "name": "홍길동",
    "age": 12,
    "title": <table>
}

{{ name }} -> {{! 홍길동 }}
{{ age }} -> {{! 12 }}
{{ company }} -> {{! none }}
{{ title }} -> {{! &lt;table&gt; }}
{{{ title }}} -> <table> 태그를 그대로 써준다.

{
    "name": "홍길동",
    "age": 12,
    "company": {
        "name": "임픽스",
        "http": "http://impix.co.kr",
        "address": "서울",
    },
}

{{ name }} : 홍길동
{{ age }} : 12
{{ company.name }} : 임픽스
{{ company.http }} : http://impix.co.kr

2. Section : 조건문, 반복문
    {{ #변수명 }}
    {{ #posts }}

    2-1 조건문
    {
        "name": "홍길동"
    }

    {{#name}}
        이 사람의 이름은 {{name}}
    {{/name}}
    {{^name}}
        사람 정보가 없습니다.
    {{/name}}

    {
        "person": {
            "name": "홍길동",
            "age": 12,
        },
        "company": {
            "name": "임픽스"
        },
    }

    {{#person}}
        {{name}}의 회사명은 {{company.name}}입니다.
    {{/person}}

    2-2 반복문
    {
        "member": [
            { "name": "홍길동", "age": 12 },
            { "name": "이순신", "age": 34 },
            { "name": "광개토", "age": 56 },
        ]
    }

    {{#member}}
        {{name}}
    {{/member}}

    2-3 반전 섹션 : not ^
    {
        "member": []
    }

    {{#member}}
        {{name}}
    {{/member}}
    {{^member}}
        사람 없음
    {{/member}}

3. 부분 템플릿 : Partial Template 기호 : > , include
    java : import
    C, PHP : include

    {{>layout/header}}

    {{#name}}
        {{>user}}
    {{/name}}

4. 구분자 : {{ , }}
    일반적으로 사용할 때, {{variablename}}

    JSP : <%  %>
    PHP : <?  ?>

    {{=<%  %>=}}

Mustache : 매우 간단한 템플릿 엔진
    React 처럼 화면 틀을 미리 만들어 놓고, 특정 영역에 데이터베이스 정보를 출력
    자바 : 서버 템플릿 엔진을 사용
    JavaScript : 클라이언트 템플릿 엔진

    장점 : 문법이 매우 단순 View 역할만 수행
        즉, 화면에 출력하는 부분만 담당
        IntelliJ에서는 plugin으로 설치 간단

        Ctrl + Shift + a : 검색어 plugins
            mustache 검색해서 install한다.

            gradle.build에 추가해야하는데, 우리는 이미 추가해 놓았다.

    BootStrap
        w3schools.com

게시판
    id, title, content, author, 글쓴 시간, 글 최종 수정시간, 비밀번호, 사용자id, ....
    JPA Auditing 기술 제공
        글쓴 시간, 글 수정한 시간정보를 관리해야할 때, 시스템이 처리해주는 방법
        생성, 수정을 자동으로 감시(Auditing)하면서 데이터베이스를 이용하면 편리하다.

    insert into posts (title, content, author, time) values ('aaa', 'bbb', 'ccc', now());
    domain패키지에 BaseTimeEntity클래스 생성해야한다.

H2DB CONSOLE 링크 : localhost:8080/h2-console

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

번외 : d3js.org (데이터 시각화 관련 자바스크립트 라이브러리)
       프로그래밍씽킹 (책)
