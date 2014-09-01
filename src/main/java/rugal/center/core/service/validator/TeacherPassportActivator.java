package rugal.center.core.service.validator;

import java.text.MessageFormat;
import java.util.Random;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.PassportService;
import rugal.common.Message;
import rugal.center.util.ReportString;
import rugal.common.mail.pojo.Mail;
import rugal.common.mail.service.SendMailService;

/**
 *
 * @author Rugal Bernstein
 */
@Component
public class TeacherPassportActivator implements PassportActivator
{

    private static final Logger LOG = LoggerFactory.getLogger(TeacherPassportActivator.class
        .getName());

    private static final String message = "Dear teacher {0}:\n    Please click this URL to activate you passport in ZJUT\n";

    private static final String URL = "{0}/activation/teacher/{1}/{2}";

    private static final String DOMAIN_NAME = "http://lo:8080";

    private final Random random = new Random();

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private PassportService passportService;

    @Override
    public boolean check(Passport bean, String key)
    {
        return bean.checkName(key);
    }

    /**
     * In this method, we use dormitory as storage field for verification number.
     *
     * @param bean
     * @return
     */
    @Override
    public Object activate(Passport bean)
    {
        //if this passport is belong to teacher, we need mail to verify
        //There are several problems
            /*
         * 1. where to fill the mail content
         * 2. how to generate verification number
         * 3. what kind of verification number it will be
         * 4. how to do the vertification
         * 5. where the verfication number to store
         * 6. how is the storage strategy
         */
        if (null == bean.getBindMail() || bean.getBindMail().isEmpty())
        {
            return Message.failMessage(ReportString.INFO_NO_EMAIL);
        }
        int verificationNumber = random.nextInt(99999999);
        bean.setDomitory("" + verificationNumber);
        try
        {
            Mail mail = generateMail(bean);
            sendMailService.send(mail);
            passportService.updatePassport(bean);
        } catch (MessagingException ex)
        {
            LOG.error("Fail to send email", ex);
            return Message.failMessage(ReportString.ERROR_500);
        }
        return Message.successMessage(ReportString.INFO_CHECK_EMAIL, bean.getBindMail());
    }

    private Mail generateMail(Passport bean)
    {
        Mail mail = new Mail("ryujin@163.com");
        mail.setSubject("Activation Code");
        StringBuilder sb = new StringBuilder("");
        sb.append(MessageFormat.format(message, bean.getName()));
        sb.append(MessageFormat.format(URL, DOMAIN_NAME, bean.getId(), bean.getDomitory()));
        mail.setContent(sb.toString());
        return mail;
    }

}
