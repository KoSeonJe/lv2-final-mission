package finalmission.domain;

public record AuthenticatedMember(
        TokenAuthRole tokenAuthRole,
        Long id
) {

}
