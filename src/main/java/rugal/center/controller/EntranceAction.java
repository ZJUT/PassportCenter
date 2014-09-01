package rugal.center.controller;

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
import rugal.center.core.service.PassportService;
import rugal.center.util.Message;
import rugal.center.util.ReportString;

/**
 * this is an outer API used for outer request Rather than inner.<BR/>
 * this is a web service action that used to verify login information of passport and account<BR/>
 * with compatible login URL format, this web service can provide legacy URL web service
 *
 * @author Rugal Bernstein
 *
 */
@Controller
public class EntranceAction
{

    @Autowired
    private PassportService passportService;

    @Autowired
    private AccountService accountService;

    private static final Logger LOG = LoggerFactory.getLogger(EntranceAction.class.getName());

    /**
     * Populate passport from an ID, if it is a account id, then get its associated passport, or get use passport it represented.
     * Or return NULL if both not matched.
     *
     * @param id
     * @return
     */
    private Passport populatePassport(String id)
    {
        Passport passport = passportService.findById(id);
        Account account = accountService.findByName(id);
        if (null == account && null == passport)
        {
            return null;
        }

        if (null != account)
        {
            passport = account.getId();
        }
        return passport;
    }

    /**
     * passport or account login by id and passport<BR/>
     *
     * GET /entrance?id=${id}&password=${password} HTTP/1.1<BR/>
     *
     * @param id       given id number belonging to user
     * @param password password which already created by user
     * @return the right passport or account bean information in JSON
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(@RequestParam String id, @RequestParam String password)
    {
        Passport bean = populatePassport(id);
        if (null == bean)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        if (bean.isActivated())
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);
        }
        if (null == bean.getPassword() || bean.getPassword().isEmpty())
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);
        }
        if (!bean.checkPassword(password))
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
        return Message.successMessage("", bean);
    }
}
