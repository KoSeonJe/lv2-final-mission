package finalmission.dto.response;

import finalmission.domain.Coach;
import finalmission.domain.EducationPart;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record CoachResponse(
        String coachName,
        EducationPart educationPart,
        LocalTime startTime,
        LocalTime endTime,
        List<LocalDateTime> impossibleDateTime
) {

    public static CoachResponse from(Coach coach, List<LocalDateTime> impossibleDateTime) {
        return new CoachResponse(
                coach.getName(),
                coach.getEducationPart(),
                coach.getStartTime(),
                coach.getEndTime(),
                impossibleDateTime
        );
    }
}
