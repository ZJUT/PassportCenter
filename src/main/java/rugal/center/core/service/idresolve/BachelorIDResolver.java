package rugal.center.core.service.idresolve;

import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;

/**
 * resolution class designed for bachelor student
 *
 * @author rugal
 */
@Component
public class BachelorIDResolver extends IDResolver
{

    private static final String TYPE = "Bachelor";

    public BachelorIDResolver()
    {
    }

    /**
     * this is a entrance for polymorphic implementation of resolve for Bachelor degree<BR/>
     * as bachelor student id is easy to recognize<BR/>
     * sample 200926630722:<BR/>
     * first four digits is grade: 2009<BR/>
     * next two digits is school: 26<BR/>
     * next two is about major information, but we need to use it along with school id: 2663<BR/>
     * next two digits is class information, 07 is represented class 07<BR/>
     * last two digits is the number of this student in one's class. 22 is represented No.22 in class 07<BR/>
     *
     * with the help of data above, we can resolve all information concern about this bachelor student
     *
     * @param passport the passport information going to resolve
     * @return a resolvedID information about this passport, while null if not found or can not resolve
     */
    @Override
    public ResolvedID resolve(Passport passport)
    {
        ResolvedID id = new ResolvedID();

        id.setType(TYPE);

        id.put("grade", passport.getId().substring(0, 4));

        String sid = passport.getId().substring(4, 6);
        id.put("school", getSchoolService().findById(sid).getName());

        String mid = passport.getId().substring(4, 8);
        id.put("major", getMajorService().findById(mid).getName());

        return id;
    }

}
