package rugal.center.core.service.validator;

import rugal.center.core.entity.Passport;

/**
 *
 * @author Rugal Bernstein
 */
public interface PassportActivator
{

    public boolean check(Passport passport, String key);

    public Object activate(Passport passport);

}
