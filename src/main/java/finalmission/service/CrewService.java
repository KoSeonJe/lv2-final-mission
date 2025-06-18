package finalmission.service;

import static finalmission.domain.TokenAuthRole.USER;

import finalmission.domain.Crew;
import finalmission.domain.Meeting;
import finalmission.dto.request.CrewLoginRequest;
import finalmission.dto.request.CrewSignUpRequest;
import finalmission.dto.response.CrewLoginResponse;
import finalmission.dto.response.MeetingAppliedCrewResponse;
import finalmission.repository.CrewRepository;
import finalmission.repository.MeetingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CrewService {

    private final CrewRepository crewRepository;
    private final TokenService tokenService;
    private final MeetingRepository meetingRepository;

    public CrewLoginResponse login(CrewLoginRequest request) {
        Crew crew = crewRepository.findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new IllegalArgumentException("크루의 이메일 혹은 비밀번호가 틀렸습니다."));
        String token = tokenService.createToken(USER, crew.getId());
        return new CrewLoginResponse(token);
    }

    @Transactional
    public void signUp(CrewSignUpRequest crewSignUpRequest) {
        Crew crew = crewSignUpRequest.toCrew();
        crewRepository.save(crew);
    }

    // TODO : N+1 문제 해결하기
    public List<MeetingAppliedCrewResponse> getAllMeetingApplicantByCoach(Long coachId) {
        List<Meeting> meetings = meetingRepository.findAllByCoachId(coachId);
        return meetings.stream()
                .map(MeetingAppliedCrewResponse::from)
                .toList();
    }
}
