package finalmission.service;

import finalmission.dto.request.CoachLoginRequest;
import finalmission.dto.response.CoachLoginResponse;
import finalmission.dto.response.CoachResponse;
import finalmission.repository.CoachRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoachService {

    private final CoachRepository coachRepository;
    private final TokenService tokenService;

    public CoachLoginResponse login(CoachLoginRequest request) {
        if (!coachRepository.existsByAuthIdAndPassword(request.authId(), request.password())) {
            throw new IllegalArgumentException("코치의 이메일 혹은 비밀번호가 틀렸습니다.");
        }
        String token = tokenService.createToken();
        return new CoachLoginResponse(token);
    }

    public List<CoachResponse> getAll() {
        return coachRepository.findAll().stream()
                .map(CoachResponse::from)
                .toList();
    }
}
