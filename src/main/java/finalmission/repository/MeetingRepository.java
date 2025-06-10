package finalmission.repository;

import finalmission.domain.Meeting;
import finalmission.repository.dto.MeetingWithCrew;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//TODO : SQL 재확인
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query(value = """
            SELECT c.id AS crewId,
                   c.name AS crewName,
                   c.education_part,
                   m.date_time AS meetingDateTime,
                   m.content,
                   m.status AS meetingStatus
                FROM meeting m
            LEFT JOIN crew c
            ON m.crew_id = c.id
            WHERE m.coach_id = :coachId
            """, nativeQuery = true)
    List<MeetingWithCrew> getAllMeetingWithCrewNameByCoachId(@Param("coachId") Long coachId);
}
