package pl.coderslab.medicalcheckupssender.MailSender;

public interface EmailSenderRepository {
    void sendEmail(String to, String subject, String message);
}
