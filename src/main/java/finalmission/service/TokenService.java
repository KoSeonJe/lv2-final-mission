package finalmission.service;

import finalmission.domain.TokenAuthRole;

public interface TokenService {

    String createToken(TokenAuthRole tokenAuthRole);
}
