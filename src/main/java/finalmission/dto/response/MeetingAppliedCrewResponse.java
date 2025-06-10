package finalmission.dto.response;

import finalmission.domain.EducationPart;
import finalmission.domain.MeetingStatus;
import finalmission.repository.dto.MeetingWithCrew;
import java.time.LocalDateTime;

public record MeetingAppliedCrewResponse(
        Long crewId,
        String crewName,
        EducationPart educationPart,
        LocalDateTime meetingDateTime,
        String content,
        MeetingStatus meetingStatus
) {

    public static MeetingAppliedCrewResponse from(MeetingWithCrew meetingWithCrew) {
        return new MeetingAppliedCrewResponse(
                meetingWithCrew.getCrewId(),
                meetingWithCrew.getCrewName(),
                meetingWithCrew.getEducationPart(),
                meetingWithCrew.getMeetingDateTime(),
                meetingWithCrew.getContent(),
                meetingWithCrew.getMeetingStatus()
        );
    }
}
