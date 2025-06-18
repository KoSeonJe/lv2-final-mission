package finalmission.dto.request;

import finalmission.domain.MeetingStatus;

public record MeetingAnswerRequest(
        Long meetingId,
        MeetingStatus answer
) {

}
