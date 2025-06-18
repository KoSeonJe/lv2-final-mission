package finalmission.controller;

import finalmission.domain.AuthenticatedMember;
import finalmission.dto.request.CreateMeetingRequest;
import finalmission.dto.request.MeetingAnswerRequest;
import finalmission.global.web.Authenticated;
import finalmission.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/meetings")
    public void create(
            @Authenticated AuthenticatedMember authenticatedMember,
            @RequestBody CreateMeetingRequest createMeetingRequest
    ) {
        meetingService.create(authenticatedMember.id(), createMeetingRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/admin/meetings")
    public void answer(@RequestBody MeetingAnswerRequest meetingAnswerRequest) {
        meetingService.updateAnswer(meetingAnswerRequest);
    }
}
