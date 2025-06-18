package finalmission.domain;

import java.util.Objects;
import java.util.Set;

public enum TokenAuthRole {
    USER(Set.of()),
    ADMIN(Set.of(USER));

    private final Set<TokenAuthRole> possibleAccessRole;

    TokenAuthRole(Set<TokenAuthRole> possibleAccessRole) {
        this.possibleAccessRole = possibleAccessRole;
    }

    public boolean hasAccessRole(TokenAuthRole tokenAuthRole) {
        return Objects.equals(this, tokenAuthRole) || this.possibleAccessRole.contains(tokenAuthRole);
    }
}
