package finalmission.service;

import finalmission.domain.AuthenticatedMember;
import finalmission.domain.TokenAuthRole;

public interface TokenService {

    String createToken(TokenAuthRole tokenAuthRole, Long id);

    AuthenticatedMember extract(String token);
}
