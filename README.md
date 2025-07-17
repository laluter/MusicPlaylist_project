🎵 음원 검색 사이트 
프로젝트 소개
음원 검색 사이트는 콘솔 기반의 음악 검색 및 즐겨찾기 서비스입니다.

사용자는 음원을 검색, 찜(즐겨찾기), 최근/인기 검색어 확인/관리 및 회원가입/로그인 등 다양한 기능을 이용할 수 있습니다.

관리자는 회원 관리, 전곡/검색 통계, 음원 등록/수정/삭제 등 시스템 전체를 관리할 수 있습니다.

실행 방법
1. Oracle DB에 전체 테이블/시퀀스/트리거 쿼리를 실행해 초기화

2. /src/util/DBUtil.java 내 DB 접속 정보(계정, 비번 등) 설정

3. 필요한 라이브러리(JDBC Driver) 포함

4. Main.java 실행

5. 콘솔에서 메뉴 선택 후 회원가입/검색/즐겨찾기/관리자 메뉴 등 사용

시연 영상 링크
https://youtu.be/xsAjxP-0nbI
   
주요 기능
회원

회원가입/로그인/로그아웃

최근 검색어 및 즐겨찾기 관리

음원

곡명, 아티스트, 음원ID 검색

상세정보(앨범, 유튜브/스포티파이 링크) 확인

즐겨찾기

즐겨찾기 추가/삭제 및 목록 확인 (음원ID와 곡명 동시 출력)

검색 통계

최근 검색어, 인기 검색어 확인

관리자

회원 목록 조회

전체 검색 통계/음원 데이터 추가/수정/삭제

src/
  ├─ model/
  │    ├─ Music.java
  │    └─ User.java
  ├─ dao/
  │    ├─ MusicDao.java / MusicDaoImpl.java
  │    ├─ UserDao.java / UserDaoImpl.java
  │    ├─ FavoriteDao.java / FavoriteDaoImpl.java
  ├─ service/
  │    ├─ MusicService.java
  │    ├─ UserService.java
  │    ├─ AdminService.java
  │    └─ SearchStatisticsService.java
  ├─ view/
  │    ├─ ConsoleMenu.java
  │    ├─ AdminMenu.java
  │    ├─ AdminView.java
  │    ├─ MusicView.java
  │    └─ SearchMenu.java
  ├─ util/
  │    └─ DBUtil.java
  └─ main/
        └─ Main.java

<img width="635" height="489" alt="ERD" src="https://github.com/user-attachments/assets/da5d30de-c13d-4f37-b682-7818085a7349" 



기술스택: java, jdbc, oracle db

