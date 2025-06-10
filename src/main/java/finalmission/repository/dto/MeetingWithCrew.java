package finalmission.repository.dto;

import finalmission.domain.MeetingStatus;
import java.time.LocalDateTime;

public interface MeetingWithCrew {

    Long getCrewId();

    String getCrewName();

    LocalDateTime getMeetingDateTime();

    String getContent();

    MeetingStatus getMeetingStatus();
}
