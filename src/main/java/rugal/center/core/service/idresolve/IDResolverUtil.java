package rugal.center.core.service.idresolve;

import java.beans.Introspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;

/**
 * This is a ID resolver utility class for utilize the IDResolver abstract class.
 *
 * @author Rugal Bernstein
 */
@Component
public class IDResolverUtil
{

    private static final Logger LOG = LoggerFactory.getLogger(IDResolverUtil.class.getName());

    @Autowired
    private ApplicationContext context;

    /**
     * This passport feed the {@code IDResolver} object with required id and get resovledID as feedback.
     *
     * @param passport
     * @return ResolvedID as feedback, null object if can not find or internal error happened.
     */
    public ResolvedID resolve(Passport passport)
    {
        ResolvedID resolvedID = null;
        String beanName = Introspector.decapitalize(passport.getType().getName()) + IDResolver.class
            .getSimpleName();
        try
        {
            IDResolver resolver = (IDResolver) context.getBean(beanName);
            if (null != resolver)
            {
                resolvedID = resolver.doResolve(passport);
            }
        } catch (BeansException be)
        {
            LOG.error("Can not find this bean " + beanName, be);
        }
        return resolvedID;
    }
}
