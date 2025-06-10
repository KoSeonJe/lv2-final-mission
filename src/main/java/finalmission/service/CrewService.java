package finalmission.service;

import finalmission.domain.Crew;
import finalmission.dto.request.CrewLoginRequest;
import finalmission.dto.request.CrewSignUpRequest;
import finalmission.dto.response.CrewLoginResponse;
import finalmission.dto.response.MeetingAppliedCrewResponse;
import finalmission.repository.CrewRepository;
import finalmission.repository.MeetingRepository;
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
        if (!crewRepository.existsByEmailAndPassword(request.email(), request.password())) {
            throw new IllegalArgumentException("크루의 이메일 혹은 비밀번호가 틀렸습니다.");
        }
        String token = tokenService.createToken();
        return new CrewLoginResponse(token);
    }

    @Transactional
    public void signUp(CrewSignUpRequest crewSignUpRequest) {
        Crew crew = crewSignUpRequest.toCrew();
        crewRepository.save(crew);
    }

}
