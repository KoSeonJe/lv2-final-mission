package finalmission.dto.request;


import finalmission.domain.Crew;

public record CrewSignUpRequest(
        String name,
        String email,
        String password,
        int period
) {

    public Crew toCrew() {
        return Crew.builder()
                .name(name)
                .email(email)
                .password(password)
                .period(period)
                .build();
    }
}
