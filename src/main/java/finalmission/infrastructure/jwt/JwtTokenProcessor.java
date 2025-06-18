package finalmission.infrastructure.jwt;

import finalmission.domain.AuthenticatedMember;
import finalmission.domain.TokenAuthRole;
import finalmission.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProcessor implements TokenService {

    private final JwtProperties jwtProperties;

    @Override
    public String createToken(TokenAuthRole tokenAuthRole, Long id) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(jwtProperties.getSignKey())
                .claim("role", tokenAuthRole.name())
                .claim("id", id)
                .compact();
    }

    @Override
    public AuthenticatedMember extract(String rawToken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSignKey())
                .build()
                .parseClaimsJwt(rawToken.split(" ")[1])
                .getBody();

        return new AuthenticatedMember(
                body.get("role", TokenAuthRole.class),
                body.get("id", Long.class)
        );
    }
}
