package finalmission.service;

import finalmission.domain.Coach;
import finalmission.domain.Crew;
import finalmission.domain.Meeting;
import finalmission.domain.MeetingStatus;
import finalmission.dto.request.CreateMeetingRequest;
import finalmission.repository.CoachRepository;
import finalmission.repository.CrewRepository;
import finalmission.repository.MeetingRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingService {

    private static final int MEETING_TIME = 1;

    private final MeetingRepository meetingRepository;
    private final CoachRepository coachRepository;
    private final CrewRepository crewRepository;

    @Transactional
    public void create(Long crewId, CreateMeetingRequest request) {
        validateOverlappedDateTime(LocalDateTime.of(request.date(), request.time()));
        MeetingStatus meetingStatus = MeetingStatus.PENDING;
        Coach coach = getCoachById(request.coachId());
        Crew crew = getCrewById(crewId);

        Meeting meeting = request.toMeeting(meetingStatus, coach, crew);
        meetingRepository.save(meeting);
    }

    @Transactional
    public String accept(Long meetingId) {
        Meeting meeting = getMeetingById(meetingId);
        meeting.updateStatusTo(MeetingStatus.ACCEPT);
        return meeting.getCrew().getEmail();
    }

    @Transactional
    public String deny(Long meetingId) {
        Meeting meeting = getMeetingById(meetingId);
        meeting.updateStatusTo(MeetingStatus.DENY);
        return meeting.getCrew().getEmail();
    }

    private void validateOverlappedDateTime(LocalDateTime dateTime) {
        LocalDateTime overlappedPossibleStartTime = dateTime.minusHours(MEETING_TIME);
        LocalDateTime overlappedPossibleEndTime = dateTime.plusHours(MEETING_TIME);
        if (meetingRepository.existsMeetingByDateTimeBetween(overlappedPossibleStartTime, overlappedPossibleEndTime)) {
            throw new IllegalArgumentException("현재 겹치는 미팅시간이 존재합니다.");
        }
    }

    private Meeting getMeetingById(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID로 미팅을 찾을 수 없습니다"));
    }

    private Coach getCoachById(Long coachId) {
        return coachRepository.findById(coachId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID로 코치를 찾을 수 없습니다"));
    }

    private Crew getCrewById(Long crewId) {
        return crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID로 크루를 찾을 수 없습니다."));
    }
}
