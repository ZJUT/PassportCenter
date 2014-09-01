package rugal.common.mail.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rugal.common.mail.pojo.AccountAuthenticator;
import rugal.common.mail.pojo.Mail;
import rugal.common.mail.pojo.Sender;

/**
 * This is a mail service provide mail sending function<BR/>
 * This class is designed as one to many mailing method<BR/>
 * you need to provide sender instance as constructor parameter<BR/>
 * then use <code>sent</code> method to send mail
 *
 * @author Rugal Bernstein
 */
@Service
public class SendMailService
{

    private static final String mail_smtp_auth = "mail.smtp.auth";

    private static final String mail_smtp_starttls_enable = "mail.smtp.starttls.enable";

    private static final String mail_smtp_host = "mail.smtp.host";

    private static final String mail_smtp_port = "mail.smtp.port";

    private Session session;

    private Sender sender;

    private final Properties properties = new Properties();

    private void init(Sender sender)
    {
        this.sender = sender;
        properties.put(mail_smtp_starttls_enable, "true");
        properties.put(mail_smtp_auth, "true");
        properties.put(mail_smtp_host, sender.getSmtpHostName());
        properties.put(mail_smtp_port, sender.getSmtpPort());
    }

    @Autowired
    public SendMailService(Sender sender)
    {
        init(sender);
    }

    public void send(Mail mail) throws MessagingException
    {
        if (null == session) {
            session = Session.getDefaultInstance(properties, new AccountAuthenticator(sender.getUsername(), sender.getPassword()));
        }
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender.getEmailAddress()));
        InternetAddress toAddress = new InternetAddress(mail.getRecipient());

        message.addRecipient(Message.RecipientType.TO, toAddress);

        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        Transport.send(message);
    }
}
