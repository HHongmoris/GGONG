 # GGONG

![ggong_logo](./img/찐로고.png)

> [0. 프로젝트 개요](#0-프로젝트-개요)
> 
> [1. 서비스 및 기능 소개](#1-서비스-및-기능-소개)
> 
> [2. 서비스 화면](#2-서비스-화면)
>   - [로그인 / 가입](#로그인--가입)
>   - [메인화면](#메인화면)
>   - [메뉴바](#메뉴바)
>   - [마이페이지](#마이페이지)
>   - [지도](#지도)
>   - [투표 화면](#투표-화면)
>   - [포인트 관련 화면](#포인트-관련-화면)
>   - [통계](#통계)
>   - [기기화면](#기기화면)
>   
> [3. 시스템 아키텍처](#3-시스템-아키텍처)
> 
> [4. 컴포넌트 구성도](#4-컴포넌트-구성도)
> 
> [5. ERD](#5-erd)
> 
> [6. 서비스 및 기술 특장점](#6-서비스-및-기술-특장점)
> 
> [7. 멤버 및 역할](#7-멤버-및-역할)

## 0. 프로젝트 개요

✔ 프로젝트명 : GGONG(꽁수래 꽁수거)

✔ 한줄 소개 : Web IoT를 기반으로 한 투표식 담배꽁초 수거함

✔ 개발 기간 : 23.07.10 ~ 23.08.18 (6주)

✔ 팀원 : 정예지 김동현 민병기 전주영 정의석 홍성민

✔ 사용 기술스택 : SpringBoot, React, Docker, AWS + (4. 시스템 아키텍처 참고)

## 1. 서비스 및 기능 소개

✔ 실시간 투표 
- 담배 꽁초를 이용해 실시간 투표를 진행한다.
- 1시간마다 질문이 바뀐다.

## 2. 서비스 화면

### 웹
#### 로그인 / 가입

![login](./img/초기화면.png)

#### 메인화면

![main](./img/메인페이지.png)

![큐알](./img/큐알.png)

#### 메뉴바

![메뉴바화면](./img/메뉴바화면.png)

#### 마이페이지

![마이페이지](./img/마이페이지.png)

#### 지도

![지도페이지](./img/지도페이지.png)

#### 투표 화면

![현재 진행중인 투표](./img/현재_진행중인_투표.png)

![지난투표](./img/지난투표.png)

![상세보기 연령별](./img/상세보기_연령별.png)

#### 포인트 관련 화면

![포인트샵](./img/포인트샵.png)

![구매모달](./img/구매모달.png)

![포인트내역](./img/포인트내역.png)

![구매내역](./img/구매내역.png)

#### 통계

![통계페이지](./img/통계페이지.png)

### IoT
#### 멤버화면

![멤버화면](./img/멤버화면.png)

#### 카메라

![카메라](./img/카메라.png)

#### 메인화면

![메인화면](./img/메인화면.png)

## 3. 시스템 아키텍처

![servicearchitecture](./img/시스템아키텍처.png)


## 4. 컴포넌트 구성도

![rdb-component](https://github.com/ian813/ggong/assets/118112177/9b26d9e4-0c3d-4efa-9439-e13df0b54ae6)

## 5. ERD

![mysql erd](./img/erd.png)

## 6. 서비스 및 기술 특장점

1. 

## 7. 멤버 및 역할

<table>
  <tr>
    <td align="center"><a href="https://github.com/ityeji"><img src="https://avatars.githubusercontent.com/u/110680436?v=4?s=100" width="100px;" alt=""/><br /><sub><b>정예지</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/hannernos"><img src="https://avatars.githubusercontent.com/u/82031197?v=4?s=100" width="100px;" alt=""/><br /><sub><b>김동현</b></sub></a><br /></td>     
    <td align="center"><a href="https://github.com/bmincof"><img src="https://avatars.githubusercontent.com/u/104330984?v=4?s=100" width="100px;" alt=""/><br /><sub><b>민병기</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/juuyoungjeon"><img src="https://avatars.githubusercontent.com/u/44489852?v=4?s=100" width="100px;" alt=""/><br /><sub><b>전주영</b></sub></a><br /></td>      
    <td align="center"><a href="https://github.com/ian813"><img src="https://avatars.githubusercontent.com/u/118112177?v=4?s=100" width="100px;" alt=""/><br /><sub><b>정의석</b></sub></a><br /></td>     
      <td align="center"><a href="https://github.com/HHongmoris"><img src="https://avatars.githubusercontent.com/u/122426101?v=4?s=100" width="100px;" alt=""/><br /><sub><b>홍성민</b></sub></a><br /></td>   
  </tr>
</table>


✔ 정예지 
- 팀장
- PM
- Jira 관리
- Backend Entity 및 API 구현
- Backend Spring Data JPA 적용
- Backend 에러 및 API 디버깅
- Backend SSE 구현
- ERD 설계 및 Backend RDBMS(MySQL) 적용
- DB 더미데이터 생성

✔ 김동현 
- EC2 DB설치 및 환경 설정
- IoT 프로세스 로직 설계
- IoT H/W 디자인
- IoT 디렉토리 구조 설계
- IoT QT 화면 디자인
- IoT  QT 시그널 설계
- IoT  QT 쓰레드 설계
- IoT  MQTT5 구현
- IoT MQTT5 토픽 설계
- IoT AWS IoT 연결
- IoT AWS SQS 연결
- IoT AWS Lambda 연결
- IoT 카메라를 통한 QR 인식 구현
- IoT  초음파센서를 통한 투표 구현
- IoT  데이터베이스 연결
- IoT  데이터 Object 설계
- IoT  질문 알고리즘 구현
- IoT 타이머 구현
     

✔ 민병기 
- Frontend 컴포넌트 설계
- Frontend 버튼, 모달, 리스트 컴포넌트 구현
- Frontend 차트 구현을 통한 데이터 시각화
- Frontend api 통신 구현
- 카카오 api 활용 소셜 로그인, 지도 페이지 구현
- Backend, Frontend SSE 통신 구현
- 빌드 및 배포
     

✔ 전주영
- Spring Boot를 활용한 REST API 구축
- JWT와 Spring Security를 활용한 로그인 구현
- OAuth2 카카오 로그인 api 구현
- 자체 로그인 회원인 경우 비밀번호 암호화 알고리즘 구현
- Spring Boot JPA를 활용한 DB 구축
- Backend Entity 및 API 구현
- Docker 및 Jenkins를 활용한 CI/CD 구축
- Docker container에 DB 구축
- Docker 및 AWS를 활용한 서버 배포
- Nginx를 활용한 프론트 웹서버 구축, 백엔드 서버 구축 및 리버스 프록시 설정 및 적용
     

✔ 정의석 
- Figma를 활용한 Prototype 제작
- Frontend 카드, 드롭다운, 메뉴바 컴포넌트 구현
- Frontend 메인페이지 구현
- Frontend 투표페이지 구현
- Frontend 통계페이지 구현
- Frontend Chart.js를 이용한 꺾은선 그래프 구현
- Frontend Redux 적용
- DB 더미데이터 생성
     

✔ 홍성민
- Backend Entity 및 API 구현
- Backend Spring Data JPA 적용
- Backend 에러 및 API 디버깅
- ERD 설계 및 Backend RDBMS(MySQL) 적용
- Figma를 활용한 와이어프레임 및 목업 디자인
- 서비스 로고 제작
- IoT 기기 제작
- UCC 대본 작성 및 촬영 편집
- 프로젝트 발표
