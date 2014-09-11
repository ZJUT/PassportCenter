package rugal.center.core.service.idresolve;

import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;

/**
 * resolution class designed for graduate or master student
 *
 * @author rugal
 */
@Component
public class MasterIDResolver extends IDResolver
{

    private static final String TYPE = "Master";

    private static final String YEAR_PREFIX = "20";

    private static final String TYPE_PREFIX = "211";

    public MasterIDResolver()
    {
    }

    /**
     * this is a entrance for polymorphic implementation of resolve for graduate or master degree<BR/>
     * as graduate student id is not easy to recognize in major field, we just skip major information<BR/>
     * sample 2111201001:<BR/>
     * first three digits is tagged as graduate: 211<BR/>
     * next two digits is grade for short, 12 means it is in year 2012 that entered<BR/>
     * next two is school id: 01<BR/>
     * next three digits is student number in this school, 001 means this is No.001 student in this school<BR/>
     *
     * with the help of data above, we can resolve some information concern about this graduate student
     *
     * @param passport the passport information going to resolve
     * @return a resolvedID information about this passport, while null if not found or can not resolve
     */
    @Override
    public ResolvedID resolve(Passport passport)
    {
        ResolvedID id = new ResolvedID();

        id.setType(TYPE);

        id.put("grade", YEAR_PREFIX + passport.getId().substring(3, 5));

        String sid = TYPE_PREFIX + passport.getId().substring(5, 7);
        id.put("school", getSchoolService().findById(sid).getName());

        return id;
    }

}
