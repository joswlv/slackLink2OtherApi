package com.joswlv.slackbot.slack;

import lombok.extern.slf4j.Slf4j;
import me.ramswaroop.jbot.core.slack.models.Attachment;
import me.ramswaroop.jbot.core.slack.models.RichMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.TimeZone;

@RestController
@Profile("slack")
@Slf4j
public class SlackSlashCommand {

	/**
	 * The token you get while creating a new Slash Command. You
	 * should paste the token in application.properties file.
	 */
	@Value("${slashCommandToken}")
	private String slackToken;

	@Value("${calendarId}")
	private String calendarId;

	private static ZoneId zoneId = TimeZone.getTimeZone("Asia/Seoul").toZoneId();

	/**
	 * Slash Command handler. When a user types for example "/app help"
	 * then com.datayanolja.slack sends a POST request to this endpoint. So, this endpoint
	 * should match the url you set while creating the Slack Slash Command.
	 *
	 * @param token
	 * @param teamId
	 * @param teamDomain
	 * @param channelId
	 * @param channelName
	 * @param userId
	 * @param userName
	 * @param command
	 * @param text
	 * @param responseUrl
	 * @return
	 */
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RichMessage onReceiveSlashCommand(@RequestParam("token") String token,
	                                         @RequestParam("team_id") String teamId,
	                                         @RequestParam("team_domain") String teamDomain,
	                                         @RequestParam("channel_id") String channelId,
	                                         @RequestParam("channel_name") String channelName,
	                                         @RequestParam("user_id") String userId,
	                                         @RequestParam("user_name") String userName,
	                                         @RequestParam("command") String command,
	                                         @RequestParam("text") String text,
	                                         @RequestParam("response_url") String responseUrl) throws IOException, GeneralSecurityException, ParseException {
		// validate token
		if (!token.equals(slackToken)) {
			return new RichMessage("Sorry! You're not lucky enough to use our com.datayanolja.slack command.");
		}

		RichMessage defaultRichMessage = new RichMessage("Slash Command 입니다.");
		defaultRichMessage.setResponseType("in_channel");

		//구글캘린더 연동
		if (command.equals("/schedule")) {
			if (text.equals("help")) {
				RichMessage richMessage = new RichMessage("데놀 일정 SlashCommand 도움말!!");
				richMessage.setResponseType("in_channel");
				// 도움말
				Attachment[] attachments = new Attachment[5];
				attachments[0] = new Attachment();
				attachments[0].setText("일정 추가기능만 있음..ㅎㅎ");
				attachments[1] = new Attachment();
				attachments[1].setText("`/schedule add,일정시작일(yyyy-mm-dd HH:mm 필수),일정종료일(yyyy-mm-dd HH:mm 옵션),일정-Summary(필수),일정-Description(필수)`");
				attachments[2] = new Attachment();
				attachments[2].setText("ex > `/schedule 추가,2019-03-01 09:00,데놀간담회,만나서 밥먹고 회의함`");
				attachments[3] = new Attachment();
				attachments[3].setText("");
				attachments[4] = new Attachment();
				attachments[4].setText("`/schedule list`  ==> 앞으로 남은 일정 List보여줌");
				richMessage.setAttachments(attachments);
				return richMessage.encodedMessage();
			}
		}
		return defaultRichMessage.encodedMessage(); // don't forget to send the encoded message to Slack
	}
}