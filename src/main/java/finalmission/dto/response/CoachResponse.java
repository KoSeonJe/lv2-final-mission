package finalmission.dto.response;

import finalmission.domain.Coach;
import finalmission.domain.EducationPart;

public record CoachResponse(
        Long coachId,
        String coachName,
        EducationPart educationPart
) {

    public static CoachResponse from(Coach coach) {
        return new CoachResponse(
                coach.getId(),
                coach.getName(),
                coach.getEducationPart()
        );
    }
}
