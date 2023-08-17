# 목차

1. [**서비스 소개**](#1)
2. [**기획 배경**](#2)
3. [**기능 소개**](#3)
4. [**기술 스택**](#5)
5. [**프로젝트 일정 및 산출물**](#6)
6. [**개발 멤버 및 회고**](#7)

<br/>

---

<br/>

<div id="1"></div>

# 서비스 소개

## 서비스 설명

### 개요

- 한줄 소개 : 혼자말고 함께 **공부**하자
- 서비스 명 : **`STVDY(스터디)`**

### 타겟 🎯

- 혼자서 공부를 하면 집중력이 저하되는 사람들

- 남들과 같이 공부를 하면서 의욕을 내고 싶은 사람들

- 집중력을 높이는 나만의 테마를 사용하고 싶은 사람들
  
  > 👉 \*\* **혼자서 공부를 하기보다 여럿이서 공부하고 싶은 모든 사람들** \*\*

<div id="2"></div>

# 기획 배경

## 배경

자기 계발의 시대. 언제 어디서든 나를 성장시키는 세상입니다. 많은 사람들이 공부를 하겠다 다짐하지만, 혼자서 하는 공부는 집중력이 저하되거나, 미루게 되곤 합니다.  `STVDY`은 이러한 집중력 저하와, 공부를 미루는 것에 대해 상호 경쟁과 타인의 의식을 통해서 집중력을 향상 시킬수 있습니다.
<br/>
<br/>
STVDY의 장점은 다음과 같습니다. 먼저. 자신만의 프로필을 통해 나를 은유적으로 드러낼 수 있으며, 나만의 집중력 테마를 이용해, 더욱 공부 및 회의에 집중할 수 있게 합니다.  또한 화상 스터디 또는 회의시 나의 내용을 공유하기 위한 화면 공유기능이 탑재되어 있으며, 이와 함께 기기 선택 변경도 가능합니다. 그리고 나를 드러내기 싫어하는 사람을 위한 캠 오프, 마이크 오프와 부담없이 서비스에 접근가능해, 사용자가 부담없이 사용가능하겠습니다.

## 목적 🥅

**스터디, 집중할 수 있는 분위기에서 하자**

## 의의

- 비대면 상황에서 어디서든지 다른 사람들과 함께스터디를 할 수 있는 온라인 비대면 스터디    
- 질문게시판으로 궁금증을 공유하고 서로 해결하며 공부 효과 상
- 프로필과 나만의 테마를 이용해 집중력 상승

<div id="3"></div>

# 기능 소개

### 메인

#### 1. 비로그인시

![비로그인시 메인화면.PNG](README_assets/0a494ff0114fcc9c9cafd70b2d3bd00ee98ab435.PNG)

![](README_assets/2023-08-17-11-57-55-image.png) 

#### 2. 로그인시

![로그인시 메인화면.PNG](README_assets/9698e1b0d7c9772913f689a475f10ad76875d653.PNG)

### 회원가입 및 로그인

#### 1. 로그인 창

![](README_assets/2023-08-17-11-58-20-image.png)

#### 2. 회원가입 창

![회원가입창 빈칸.PNG](README_assets/213b5a04786b0cb55b253cc6e55fcf64579b8be0.PNG)

###### a. 가입되지 않은 이메일인 경우

![로그인 이메일 전송.PNG](README_assets/799edfc98a41099ae65a70660c63d232f1ea735f.PNG)

###### b. 가입된 이메일인 경우

![로그인 이메일 전송 실패.PNG](README_assets/61a141c3a28e76b9adc6d249f21cbe72962fa6bb.PNG)

#### 3. 이메일로 전송받은 전송번호 기입 후 회원 가입

![회원가입창 칸채움.PNG](README_assets/04b7f5910e70d1af4fa51bc2163285c3073193b4.PNG)

### 질문게시판

#### 1. 최신질문순 질문 목록

![질문게시판1.PNG](README_assets/2641a142bcdcbda3660e1787ef86a4f4acde6961.PNG)

#### 2. 해결중인 질문 목록

![질문게시판 해결 중.PNG](README_assets/ee6d391912b78b4154cb8da80a474ee3947705cb.PNG)

#### 3. 답변 없는 질문 목록

![질문 게시판 답변 없음.PNG](README_assets/a43150f23d7b09511d1fb2c710c6401a4e179455.PNG)

#### 4. 질문 세부

![](README_assets/2023-08-17-10-35-34-image.png)

#### 5. 질문 작성

![](README_assets/2023-08-17-12-33-04-image.png)

### 프로필

#### 1. 이미지 변경 전

![프로필.PNG](README_assets/23dafcf1728b2571bd867c45a1b9dd0604e3d0fa.PNG)

#### 2. 이미지 변경 후

![프로필 이미지 변경.PNG](README_assets/0e86e0837c0896b723a66d3698ee733aab7d3bec.PNG)

### 테마 변경

#### 1. 테마 변경 전

![테마 변경.PNG](README_assets/46925547754389dd28dc1fd9e0c00f522da20fc0.PNG)

#### 2. 테마 변경 후

![테마 변경 후.PNG](README_assets/89af7fd3062317cb3eb613ed831c2f23261f5489.PNG)

### 스터디룸

### 챗 GPT

<div id="5"></div>

# 기술 스택

## 1. webRTC

### webRTC란?

<aside>
WebRTC (Web Real-Time Communication)는 웹 브라우저 간에 플러그인의 도움 없이 서로 통신할 수 있도록 설계된 API 입니다.
음성 통화, 영상 통화, P2P 파일 공유 등으로 활용될 수 있습니다.
![webRTC](https://cdn.ttgtmedia.com/rms/onlineimages/how_webrtc_works-f.png)

</aside>

### openvidu

WebRTC를 보다 간단하게 적용할 수 있고, 다양한 프레임워크와 호환성이 높은 openvidu를 사용하여 프로젝트를 진행했습니다.

### 적용

`STVDY` 에서는 화상회의 및 스터디를 하기 위해 openVidu를 사용합니다.

## 2. 개발 환경

1. BackEnd
   - openjdk 17.0.7
   - spring boot 3.1.2
   - mysql 8.0.34
   - redis 5.0.7
   - openvidu 2.28.0
   - intellij 2023.2
2. FrontEnd
   - node 18.16.1
   - vue 3.3.4
   - openai 3.3.0
   - vscode 1.79.2

## 3. System Architecture

![아키텍처.png](README_assets/9cd8cf173c268b33a24b49d8c99046fd7ec96b85.png)

<div id="6"></div>

# 프로젝트 일정 및 산출물

## 프로젝트 일정

```
프로젝트 기획 및 설계 : 23.07.04 ~ 23.07.21
개발 : 23.07.24 ~ 23.08.11
배포 : 23.08.07 ~ 23.08.11
테스트 : 23.08.14 ~ 23.08.17
시연 및 발표 : 23.08.18
```

## 프로젝트 진행

### 1. Git flow

---

Git flow 사용을 위해 `sourcetree` 프로그램을 사용하였고 우아한 형제들의 [git flow](https://techblog.woowahan.com/2553/)을 참고했습니다. front 와 back 으로 나누어 `faature`의 하위 브랜치를 사용하였으며 매일 오전 스크럼 이후 `back` 브랜치와 `front` 브랜치로 merge 하여 사용했습니다.<br>
`commit message`는 `feat(대기방): 채팅방 구현` 과 같이 통일하여 작성했습니다.<br>
<br><br><br>

![gitflow.png](README_assets/58cb25ff9a2568d6316274c81bb551aa8acb42bc.png)

### 2. Jira

---

매주 월요일 오전 회의에서 금주의 진행 이슈를 백로그에 등록했습니다. 전주에 완료하지 못한 이슈나, 앞으로 진행할 이슈들을 추가합니다.

- 에픽은 회원, 미팅, 설계 등으로 구성했습니다.
- 레이블은 BE, FE, full 으로 구성했습니다.
- 스토리는 명확한 전달을 위하여 `API 명세서 작성`와 같이 작성했습니다.
- 작업현황을 실시간으로 지라에 반영하여 현재 팀원이 어떤 작업을 하고있는지, 일정에 딜레이가 있는지 한 눈에 알아볼 수 있게 했습니다.<br>
  ![지라](README_assets/28b2f8fd1bb55986a124b06c1e5592e5a9a5a5f1.PNG)

## 프로젝트 산출물

### 1. Figma

[![피그마](./assets/피그마.PNG)](https://www.figma.com/file/fRBQDGRSSAqfpwLACFunn3/Material-You-for-web-apps.-Desktop-%26-mobile-templates-(Community)?node-id=9003%3A179932)
<br>

### 2. ERD

[![erd](./assets/erd.PNG)](https://www.erdcloud.com/d/tj8QmWP6ENjYwhnWX)
<br>

### 3. API 문서

[![api](README_assets/e62ce3d05e5e3b93e66248c74daad3c468420cbf.PNG)](https://fate-filament-e27.notion.site/1e6cdca64f51476c9f2656b93c3adc2a?v=18ca13453dd8437fb6e065fc83645fab)

<div id="7"></div>

# 개발 멤버 및 회고

- 안세혁 : 

- 이대근 : 

- 김소이 : 

- 윤우혁 : Backend Rest API 개발, Infra 및 CI/CD 구축을 담당하였습니다. 적용하고 싶은 기술적 요소는 많았으나 시간적인 부분에서 다소 아쉬운 점이 남습니다. 프로젝트 진행 과정에서 좋은 동료들과 함께 소통 측면과 개발 측면에서 큰 성장이 있어 뜻깊은 경험으로 남았습니다.

- 김범기 : 처음으로 다수와 함께 하는 프로젝트를 진행하게 되면서 소통의 중요성과 기획단계가 얼마나 중요한지에 대해서 몸소 체감했습니다. webRTC 기술의 front부분을 맡으면서, 해당 기술에 대해 지식이 전무해 학습하면서 프로젝트에 도입하는 것이 프로젝트 기간 동안 크나큰 고통이었지만, 이 고통이 성장에 도움이 되었습니다.
  
  - 안대현 : 
