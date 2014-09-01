package rugal.center.core.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.PassportService;
import rugal.center.util.ReportString;
import rugal.common.Message;

/**
 *
 * @author Rugal Bernstein
 */
@Component
public class StudentPassportActivator implements PassportActivator
{

    @Autowired
    private PassportService passportService;

    @Override
    public boolean check(Passport bean, String key)
    {
        return bean.checkIDCard(key);
    }

    @Override
    public Object activate(Passport bean)
    {
        passportService.activate(bean);
        return Message.successMessage(ReportString.INFO_ACTIVATION_SUCCESS, bean);
    }

}
