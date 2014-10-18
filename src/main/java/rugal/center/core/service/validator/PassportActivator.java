package rugal.center.core.service.validator;

import rugal.center.core.entity.Passport;
import rugal.common.Message;

/**
 *
 * @author Rugal Bernstein
 */
public interface PassportActivator
{

    public boolean check(Passport passport, String key);

    public Message activate(Passport passport);

}
