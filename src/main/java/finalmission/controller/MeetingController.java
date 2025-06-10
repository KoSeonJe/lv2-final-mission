package finalmission.controller;

import finalmission.dto.request.CreateMeetingRequest;
import finalmission.service.MailService;
import finalmission.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    private final MailService mailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/meetings")
    public void create(@RequestBody CreateMeetingRequest createMeetingRequest) {
        Long crewId = 1L;
        meetingService.create(createMeetingRequest, crewId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/coach/meetings/{meetingId}/accept")
    public void accept(@PathVariable("meetingId") Long meetingId) {
        String acceptedEmail = meetingService.accept(meetingId);
        mailService.sendAcceptEmail(acceptedEmail);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/coach/meetings/{meetingId}/deny")
    public void deny(@PathVariable("meetingId") Long meetingId) {
        String deniedEmail = meetingService.deny(meetingId);
        mailService.sendDenyEmail(deniedEmail);
    }
}
