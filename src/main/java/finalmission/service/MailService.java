package finalmission.service;

public interface MailService {

    void sendAcceptEmail(String acceptedEmail);

    void sendDenyEmail(String deniedEmail);
}
