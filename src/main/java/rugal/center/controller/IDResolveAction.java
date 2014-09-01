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
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;
import rugal.center.core.service.PassportService;
import rugal.center.core.service.idresolve.IDResolver;
import rugal.center.util.NameResolver;
import rugal.center.util.ReportString;
import rugal.center.util.Message;

/**
 * ID resolve service provided for outer usage. A very convenient web service.<BR/>
 *
 * @author Rugal Bernstein
 */
@Controller
public class IDResolveAction implements ApplicationContextAware
{

    private ApplicationContext context;

    private static final Logger LOG = LoggerFactory.getLogger(IDResolveAction.class.getName());

    @Autowired
    private PassportService passportService;

    /**
     * provide web service that only resolve those id that are valid and existed in database
     *
     * GET /idresolver/${id} HTTP/1.1<BR/>
     *
     * @param id given id to be resolved
     * @return will return a JSON that contain resolved information for given id if successfully executed, whilst return
     *         null either can not find in database or format invalid
     */
    @ResponseBody
    @RequestMapping(value = "/idresolver/{id}", method = RequestMethod.GET)
    public Object resolve(@PathVariable String id)
    {
        Passport passport = passportService.findById(id);
        if (null == passport)
        {
            return Message.failMessage(ReportString.WARN_NOT_EXIST);
        }
        ResolvedID resolvedID = null;
        String beanName = NameResolver.resolve(passport) + IDResolver.class.getSimpleName();
        try
        {
            IDResolver resolver = (IDResolver) context.getBean(beanName);
            if (null != resolver)
            {
                resolvedID = resolver.resolve(passport);
            }
        } catch (BeansException be)
        {
            LOG.error("Can not find this bean " + beanName, be);
        }
        return Message.successMessage("", resolvedID);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        this.context = context;
    }
}
