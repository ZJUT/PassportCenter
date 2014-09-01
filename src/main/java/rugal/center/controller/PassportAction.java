package rugal.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.PassportService;
import rugal.center.core.service.validator.PassportActivator;
import rugal.center.util.Message;
import rugal.center.util.NameResolver;
import rugal.center.util.ReportString;

/**
 * this is a action that provide passport operation web service.<BR/>
 * the function includes activation, deactivation and updating of password
 *
 * @author Rugal Bernstein
 *
 */
@Controller
@RequestMapping(value = "/passport")
public class PassportAction implements ApplicationContextAware
{

    private ApplicationContext context;

    @Autowired
    private PassportService passportService;

    private static final Logger LOG = LoggerFactory.getLogger(PassportAction.class.getName());

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        this.context = context;
    }

    @ResponseBody
    @RequestMapping(value = "/activation/teacher/{id}/{number}")
    public Object activate(@PathVariable String id, @PathVariable String number)
    {
        Passport bean = passportService.findById(id);
        if (null == bean)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        if (bean.isActivated())
        {
            return Message.failMessage(ReportString.INFO_ALREADY_ACTIVATED);
        }
        if (!bean.getDomitory().equals(number))
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
        passportService.activate(bean);
        return Message.successMessage(ReportString.INFO_ACTIVATION_SUCCESS, bean);
    }

    /**
     * activate the passport for the first time.<BR/>
     * POST /passport/activation HTTP/1.1 <BR/>
     * _method=put&id=${id}&idcard=${idcard}&password=${password}
     *
     * @param id       given id number binded with a user
     * @param idcard   given idcard number binded with a user
     * @param password
     * @return correct passport bean if found, or error info when passport already activated
     */
    @ResponseBody
    @RequestMapping(value = "/activation", method = RequestMethod.PUT)
    public Object activate(@RequestParam String id, @RequestParam String idcard,
        @RequestParam String password)
    {
        Passport bean = passportService.findById(id);
        if (null == bean)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        if (bean.isActivated())
        {
            return Message.failMessage(ReportString.INFO_ALREADY_ACTIVATED);
        }

        String beanName = NameResolver.resolve(bean) + PassportActivator.class.getSimpleName();
        PassportActivator validator = (PassportActivator) context.getBean(beanName);
        if (!validator.check(bean, idcard))
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
        return validator.activate(bean);
    }

    /**
     * change password if verification passed<BR/>
     * POST /passport/password HTTP/1.1 <BR/>
     * _method=put&id=${id}&oldPWD=${oldPWD}&newPWD=${newPWD}
     *
     * @param id     given id number binded with a user
     * @param oldPWD type in old password for verification
     * @param newPWD the new password to change to
     * @return return correct bean information if changed, or get error JSON
     */
    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public Object changePassword(@RequestParam String id, @RequestParam String oldPWD,
        @RequestParam String newPWD)
    {
        Passport bean = passportService.findById(id);
        if (null == bean)
        {//passport not found
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        if (bean.getActivated() == 0)
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);
        }
        if (bean.noPassword())
        {
            return Message.failMessage(ReportString.INFO_NO_PASSWORD);
        }
        //change password only when have a previous password
        if (bean.checkPassword(oldPWD))
        {
            // password valid
            bean.setPassword(newPWD);
            LOG.info("change passport for passport: " + bean.getId());
            passportService.updatePassport(bean);
            return Message.successMessage("password changed", bean);
        } else
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
    }

    /**
     * deactivate the passport for the if user do not want to use it any more.<BR/>
     * POST /passport/deactivation HTTP/1.1 <BR/>
     * _method=put&id=${id}&idcard=${idcard}&password=${password}
     *
     * @param id     given id number binded with a user
     * @param idcard given idcard number binded with a user
     * @return correct passport bean if successfully deactivated, or error info.
     */
    @ResponseBody
    @RequestMapping(value = "/deactivation", method = RequestMethod.PUT)
    public Object deActivate(@RequestParam String id, @RequestParam String idcard)
    {
        Passport bean = passportService.findById(id);
        if (null == bean)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        if (!bean.isActivated())
        {
            return Message.failMessage(ReportString.INFO_NEEDS_ACTIVATED);

        }
        if (!bean.checkIDCard(idcard))
        {
            return Message.failMessage(ReportString.WARN_INVALID);
        }
        /*
         * we might add more procedure like mailling verification
         */
        //TODO
        bean.setActivated(0);
        LOG.info("deactivate passport for: " + bean.getId());
        passportService.updatePassport(bean);
        return Message.successMessage("Deactivation succeeded!", bean);
    }
}
