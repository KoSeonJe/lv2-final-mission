package finalmission.infrastructure;

import finalmission.service.TokenService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProcessor implements TokenService {

    @Override
    public String createToken() {
        return "";
    }
}
