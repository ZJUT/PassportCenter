package rugal.common.mail.pojo;

/**
 *
 * @author Rugal Bernstein
 */
public class Mail
{

    private final String recipient;

    private String subject;

    public String getRecipient()
    {
        return recipient;
    }

    private String content = "";

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Mail(String recipient)
    {
        this.recipient = recipient;
    }

    public Mail(String recipient, String subject, String content)
    {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

}
