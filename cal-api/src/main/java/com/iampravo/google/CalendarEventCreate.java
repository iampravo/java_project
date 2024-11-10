/*
package com.iampravo.google;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarEventCreate {


    public static void addAttachment(Calendar calendarService, Drive driveService, String calendarId,
                                     String eventId, String fileId) throws IOException {
        File file = driveService.files().get(fileId).execute();
        Event event = calendarService.events().get(calendarId, eventId).execute();

        List<EventAttachment> attachments = event.getAttachments();
        if (attachments == null) {
            attachments = new ArrayList<EventAttachment>();
        }
        attachments.add(new EventAttachment()
                .setFileUrl(file.getAlternateLink())
                .setMimeType(file.getMimeType())
                .setTitle(file.getTitle()));

        Event changes = new Event()
                .setAttachments(attachments);
        calendarService.events().patch(calendarId, eventId, changes)
                .setSupportsAttachments(true)
                .execute();
    }

}
*/
