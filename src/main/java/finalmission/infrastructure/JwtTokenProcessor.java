package finalmission.infrastructure;

import finalmission.domain.TokenAuthRole;
import finalmission.service.TokenService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProcessor implements TokenService {

    //TODO : 토큰 생성 구현
    @Override
    public String createToken(TokenAuthRole tokenAuthRole) {
        return "";
    }
}
