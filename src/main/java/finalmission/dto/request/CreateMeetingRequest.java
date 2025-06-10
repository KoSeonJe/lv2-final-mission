package finalmission.dto.request;

import finalmission.domain.Coach;
import finalmission.domain.Crew;
import finalmission.domain.Meeting;
import finalmission.domain.MeetingStatus;
import java.time.LocalDateTime;

public record CreateMeetingRequest(
        Long coachId,
        LocalDateTime meetingDateTime,
        String content
) {

    public Meeting toMeeting(MeetingStatus meetingStatus, Coach coach, Crew crew) {
        return Meeting.builder()
                .dateTime(meetingDateTime)
                .content(content)
                .coach(coach)
                .crew(crew)
                .status(meetingStatus)
                .build();
    }
}
