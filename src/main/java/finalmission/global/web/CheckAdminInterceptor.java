package finalmission.global.web;

import finalmission.domain.AuthenticatedMember;
import finalmission.domain.TokenAuthRole;
import finalmission.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class CheckAdminInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String rawToken = request.getHeader("Authorization");
        AuthenticatedMember authenticatedMember = tokenService.extract(rawToken);
        if (Objects.equals(TokenAuthRole.ADMIN, authenticatedMember.tokenAuthRole())) {
            return true;
        }
        response.setStatus(403);
        return false;
    }
}
