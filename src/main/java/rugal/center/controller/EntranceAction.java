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
import rugal.center.core.entity.Major;
import rugal.center.core.entity.Passport;
import rugal.center.core.entity.School;
import rugal.center.core.pojo.ResolvedID;
import rugal.center.core.service.AccountService;
import rugal.center.core.service.MajorService;
import rugal.center.core.service.PassportService;
import rugal.center.core.service.SchoolService;
import rugal.center.core.service.idresolve.IDResolverUtil;
import rugal.center.util.ReportString;
import rugal.common.Message;

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
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private PassportService passportService;

    @Autowired
    private IDResolverUtil idResolverUtil;

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
     * GET /login?id=${id}&password=${password} HTTP/1.1<BR/>
     * This mapping will tackle with transfer student and normal one.
     * For transfer student, it will pick up their academic information.
     * For normal student, it will resolve their id and fill according to the field
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
            return Message.failMessage(ReportString.ERROR_NOT_EXIST);
        }
        if (!bean.isActivated())
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);
        }
        if (!bean.checkPassword(password))
        {
            return Message.failMessage(ReportString.ERROR_INVALID_PASSPORT);
        }
        //We assume a transfered student must be a bachelor student
        //so just let it be as it already contain all information we need to present.
        if (!bean.isTransfered())
        {
            ResolvedID resolvedID = idResolverUtil.resolve(bean);
            if (null == resolvedID)
            {
                return Message.failMessage("Unable to resolve this ID: " + id);
            }
            //No need for considering passport type since no matching is a null
            bean.setMid((Major) resolvedID.getMap().get("major"));
            bean.setSid((School) resolvedID.getMap().get("school"));
        }
        return Message.successMessage("", bean);
    }
}
