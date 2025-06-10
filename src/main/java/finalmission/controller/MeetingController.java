package finalmission.controller;

import finalmission.dto.request.CreateMeetingRequest;
import finalmission.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/meeting")
    public void create(@RequestBody CreateMeetingRequest createMeetingRequest) {
        Long crewId = 1L;
        meetingService.create(createMeetingRequest, crewId);
    }
}
