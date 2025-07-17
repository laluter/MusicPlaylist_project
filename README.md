# 🎵 음원 검색 사이트
콘솔 기반 음악 검색 & 즐겨찾기 자바 프로젝트입니다.

## 프로젝트 소개
음원 검색 사이트는 콘솔(Java) 기반의 음악 검색 및 즐겨찾기 서비스입니다.
사용자는 음원을 검색/찜(즐겨찾기)/최근&인기 검색어 확인·관리, 회원가입/로그인 등을 할 수 있습니다.
관리자는 회원 관리, 전곡/검색 통계, 음원 등록/수정/삭제 등 시스템 전체를 관리할 수 있습니다.

## 기술 스택
Java

JDBC

Oracle DB

## 실행 방법
Oracle DB에 전체 테이블/시퀀스/트리거 쿼리 실행해 DB 초기화
/src/util/DBUtil.java에서 자신의 DB 접속 정보(계정, 비번 등) 세팅
프로젝트에 JDBC 라이브러리(Oracle JDBC Driver 등) 추가
Main.java 파일 실행 (콘솔 환경)
콘솔 메뉴에서 회원가입/검색/즐겨찾기/관리자 메뉴 등 자유롭게 사용

## 시연 영상
https://youtu.be/xsAjxP-0nbI

## 주요 기능
🧑‍💻 회원
회원가입/로그인/로그아웃
최근 검색어 및 즐겨찾기 관리

🎵 음원
곡명, 아티스트명, 음원ID로 검색
상세정보: 앨범, 유튜브링크, 스포티파이링크 확인

⭐ 즐겨찾기
즐겨찾기(찜) 추가/삭제
즐겨찾기 목록에 음원ID와 곡명 동시 출력

📈 검색 통계
최근 검색어, 인기 검색어 확인

🛠️ 관리자
회원 목록 조회
전체 검색 통계
음원 데이터 추가/수정/삭제

## 프로젝트 폴더 구조

<img width="718" height="548" alt="image" src="https://github.com/user-attachments/assets/b0dd66f9-f280-470c-98f4-c5dbcc52ee67" />


## 데이터베이스 구조 (ERD)

<img width="635" height="489" alt="ERD" src="https://github.com/user-attachments/assets/109f0e6c-2120-46a3-be6f-5ce5a6e4367d" />

