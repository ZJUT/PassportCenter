/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.common.mail.pojo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Rugal Bernstein
 */
public class AccountAuthenticator extends Authenticator
{

    private String id = null;

    private String password = null;

    public AccountAuthenticator(String id, String password)
    {
        this.id = id;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(id, password);
    }

}
