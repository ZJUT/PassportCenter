/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.common.mail.pojo;

import org.springframework.stereotype.Component;

/**
 *
 * @author Rugal Bernstein
 */
@Component
public class Sender
{

    private String emailAddress = "ryujin@163.com";

    private String password = "900811";

    private String smtpHostName;

    private String smtpPort = "25";

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getUsername()
    {
        return emailAddress.split("@")[0];
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSmtpHostName()
    {
        return smtpHostName;
    }

    public void setSmtpHostName(String smtpHostName)
    {
        this.smtpHostName = smtpHostName;
    }

    public String getSmtpPort()
    {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort)
    {
        this.smtpPort = smtpPort;
    }

    public Sender()
    {
        resolveSmtpHost();
    }

    public Sender(String emailAddress)
    {
        this.emailAddress = emailAddress;
        resolveSmtpHost();
    }

    private void resolveSmtpHost()
    {
        this.smtpHostName = "smtp." + this.emailAddress.split("@")[1];
    }
}
