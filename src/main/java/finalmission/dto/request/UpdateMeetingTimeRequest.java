package finalmission.dto.request;

import java.time.LocalTime;

public record UpdateMeetingTimeRequest(
        Long authenticatedCoachId,
        LocalTime startTime,
        LocalTime endTime
) {

}
