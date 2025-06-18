package finalmission.dto.request;

public record UpdateMeetingRequest(
        Long meetingId,
        String content
) {
}
