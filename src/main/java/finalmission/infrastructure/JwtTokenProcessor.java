package finalmission.infrastructure;

import finalmission.domain.AuthenticatedMember;
import finalmission.domain.TokenAuthRole;
import finalmission.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProcessor implements TokenService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    public String createToken(TokenAuthRole tokenAuthRole, Long id) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .claim("role", tokenAuthRole.name())
                .claim("id", id)
                .compact();
    }

    @Override
    public AuthenticatedMember extract(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJwt(token)
                .getBody();

        return new AuthenticatedMember(
                body.get("role", TokenAuthRole.class),
                body.get("id", Long.class)
        );
    }
}
