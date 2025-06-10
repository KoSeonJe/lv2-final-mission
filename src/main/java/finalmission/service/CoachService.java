package finalmission.service;

import static finalmission.domain.TokenAuthRole.COACH;

import finalmission.domain.Coach;
import finalmission.domain.Meeting;
import finalmission.dto.request.CoachLoginRequest;
import finalmission.dto.response.AllCoachResponse;
import finalmission.dto.response.CoachLoginResponse;
import finalmission.dto.response.CoachResponse;
import finalmission.repository.CoachRepository;
import finalmission.repository.MeetingRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoachService {

    private final CoachRepository coachRepository;
    private final MeetingRepository meetingRepository;
    private final TokenService tokenService;

    public CoachLoginResponse login(CoachLoginRequest request) {
        if (!coachRepository.existsByAuthIdAndPassword(request.authId(), request.password())) {
            throw new IllegalArgumentException("코치의 이메일 혹은 비밀번호가 틀렸습니다.");
        }
        String token = tokenService.createToken(COACH);
        return new CoachLoginResponse(token);
    }

    public List<AllCoachResponse> getAll() {
        return coachRepository.findAll().stream()
                .map(AllCoachResponse::from)
                .toList();
    }

    public CoachResponse getById(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id로 코치를 찾을 수 없습니다."));
        List<Meeting> meetingsByCoachId = meetingRepository.findAllByCoachId(id);
        List<LocalDateTime> impossibleDateTime = extractImpossibleDateTime(meetingsByCoachId);
        return CoachResponse.from(coach, impossibleDateTime);
    }

    private static List<LocalDateTime> extractImpossibleDateTime(List<Meeting> meetings) {
        return meetings.stream()
                .map(Meeting::getDateTime)
                .filter(dateTime -> dateTime.isAfter(LocalDateTime.now()))
                .toList();
    }
}
