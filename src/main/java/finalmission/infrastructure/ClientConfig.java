package finalmission.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Value("${twilio.base-url}")
    private String baseUrl;

    @Value("${twilio.secret-key}")
    private String secretKey;

    @Bean
    public TwilioMailClient twilioMailClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + secretKey)
                .build();
        return new TwilioMailClient(restClient);
    }
}
