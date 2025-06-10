package finalmission.repository.dto;

import finalmission.domain.EducationPart;
import finalmission.domain.MeetingStatus;
import java.time.LocalDateTime;

public interface MeetingWithCrew {

    Long getCrewId();

    String getCrewName();

    EducationPart getEducationPart();

    LocalDateTime getMeetingDateTime();

    String getContent();

    MeetingStatus getMeetingStatus();
}
