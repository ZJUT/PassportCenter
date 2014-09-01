package rugal.center.controller;

import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.center.core.entity.Account;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.AccountService;
import rugal.center.core.service.ParametersService;
import rugal.center.core.service.PassportService;
import rugal.center.util.ReportString;
import rugal.center.util.Message;

/**
 * this is a action that handle with operation of account.<BR/>
 * approximately includes creation and deletion.<BR/>
 * as account information is just a mask of passport, hence here is no need to create password for it,<BR/>
 * to omit verification deliberately, and conveniently manage passport information
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/account")
public class AccountAction
{

    @Autowired
    private AccountService accountService;

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private PassportService passportService;

    private static final Logger LOG = LoggerFactory.getLogger(AccountAction.class.getName());

    /**
     * create an account without password verification, a passport can only create "account number" of account<BR/>
     *
     * POST /account HTTP/1.1 <BR/>
     * id=${id}&username=${username}
     *
     * @param id       the passport id that used to create belonging to it
     * @param username the new account name
     * @return the created account bean in JSON
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object create(@RequestParam String id, @RequestParam String username)
    {
        Passport bean = passportService.findById(id);
        if (null == bean)
        {//passport not found
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        //needs to verify activation status of this passport
        if (0 == bean.getActivated())
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);
        }
        if (null != accountService.findByName(username))
        {
            return Message.failMessage(ReportString.INFO_ACCOUNT_OCCUPIED);
        }
        Integer value = parametersService.getInt("account.number.per.passport", 3);
        //count the number of account that this passport has
        Integer numberOfAccount = accountService.countAccount(bean);
        if (value <= numberOfAccount)
        {
            return Message.failMessage(ReportString.INFO_ACCOUNT_FULL);
        }
        //see if not exceeded, just add one
        Account account = new Account(username);
        account.setId(bean);
        accountService.save(account);
        return Message.successMessage(MessageFormat
            .format("Now you have {0} accounts", numberOfAccount + 1), account);
    }

    /**
     * delete an account if passed verification.<BR/>
     * GET /account?id=${id}&username=${username}&_method=DELETE HTTP/1.1 <BR/>
     *
     * @param username the id of an account
     * @param password the password used to verify the authentication
     * @return return empty string with nothing in it but not null String if succeeded
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Object delete(@RequestParam String username, @RequestParam String password)
    {
        Account account = accountService.findByName(username);
        if (null == account)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        //verify the passwor of this account
        Passport bean = account.getId();
        if (!bean.checkPassword(password))
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
        accountService.deleteByName(username);
        return Message.successMessage("delete successfully", username);
    }
}
