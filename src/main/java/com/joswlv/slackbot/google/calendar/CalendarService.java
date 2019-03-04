package com.joswlv.slackbot.google.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static com.joswlv.slackbot.google.GoogleOauth.getCalendarService;

public class CalendarService {
	//calendar Event List
	public List<Event> getCalendarList() {
		List<Event> items = new ArrayList<>();
		try {
			String pageToken = null;
			Calendar service = getCalendarService();
			Events events = service.events().list("calendarId").setPageToken(pageToken).execute();
			items = events.getItems();
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return items;
	}
}
