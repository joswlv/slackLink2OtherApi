# SlackBot - OtherAPI연동

## Slack API연동 설명

1. [https://api.slack.com/](https://api.slack.com/apps/)에서 `Create App`을 한다.
2. `Add features and functionality`에서 `Permissions`를 클릭하면 `OAuth Access Token`을 확인 할 수 있고 이를 `application.properties`에 `slackBotToken`의 value로 추가한다.
3. `Add features and functionality`에서 `Slash Commands`를 클릭하여 slash command를 제작하고 해당 봇이 올라갈 서버 도메인을 입력한다.
4. `App Credentials`에서 `Verification Token`값을 `application.properties`에 `slashCommandToken`의 value로 추가한다.
5. `Add features and functionality`에서 `Incoming Webhooks`를 클릭해 `Add New Webhook to Workspace`를 통해 webhook url를 발급 받는다.
6. 발급받은 webhook url를 `application.properties`에 `slackIncomingWebhookUrl`의 value로 추가한다.

## Slack API class설명

### 1. [SlackBot.java]()

- 대화형 Bot
- [https://api.slack.com/bot-users](https://api.slack.com/bot-users)

### 2. [SlackSlashCommand.java]()

- slack slash command에서 넘어온 param을 이용해 서비스를 제공할 수 있다.
- [https://api.slack.com/slash-commands](https://api.slack.com/slash-commands)

### 3. [SlackWebhooks.java]()

- 지정한 workspace에 hook을 보낼 수 있다.
-  [https://api.slack.com/incoming-webhooks](https://api.slack.com/incoming-webhooks)

## Google API연동

### GoogleOauth연동 - [GoogleOauth.java]()

1. [https://console.developers.google.com](https://console.developers.google.com)에 접속을하여 프로젝트를 생성한다.
2. 연동하고 싶은 API를 설성하고 `서비스 계정키`를 생성한다.([참고](https://handcoding.tistory.com/20))
3. `사용자 인증정보 만들기`에서 `Oauth 클라이언트 ID`를 클릭한다.
4. `애플리케이션 유형`을 기타로 하여 발급을 받고 josn파일을 다운받아 프로젝트 `resource`에 `calendar_secret.json`로 추가한다.(이름 변경을 하면 [여기]()를 수정한다.)
5. [Googleoauth.java]() api별 권한 scope를 설정한다. ([참고](https://developers.google.com/identity/protocols/googlescopes))
