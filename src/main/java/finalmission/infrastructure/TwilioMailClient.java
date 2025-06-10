package finalmission.infrastructure;

import finalmission.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class TwilioMailClient implements MailService {

    private final RestClient restClient;

    @Override
    public void sendAcceptEmail(String acceptedEmail) {

    }

    @Override
    public void sendDenyEmail(String deniedEmail) {

    }
}
