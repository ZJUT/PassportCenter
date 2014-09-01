package rugal.mail.service;

import javax.mail.MessagingException;
import org.junit.Before;
import org.junit.Test;
import rugal.common.mail.pojo.Mail;
import rugal.common.mail.pojo.Sender;
import rugal.common.mail.service.SendMailService;

/**
 *
 * @author Rugal Bernstein
 */
public class SendMailServiceTest
{

    private Mail mail;

    private Sender sender;

    private SendMailService instance;

    @Before
    public void setUp()
    {

    }

    /**
     * Test of send method, of class MailSender.
     *
     * @throws javax.mail.MessagingException
     */
    @Test
    public void testSend() throws MessagingException
    {
        System.out.println("begin");
//        instance.send(mail);
        System.out.println("end");
    }
}
