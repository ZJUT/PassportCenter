package rugal.center.core.service.idresolve;

import org.springframework.stereotype.Component;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;

/**
 * resolution class designed for teacher
 *
 * @author Rugal Bernstein
 */
@Component
public class TeacherIDResolver extends IDResolver
{

    public static final String TYPE = "Teacher";

    private static final String URL = "http://www.tdc.zjut.edu.cn/UTADB/teacher/search_one_intro.jsp?teacher_id=";

    public TeacherIDResolver()
    {
    }

    /**
     * this is a entrance for polymorphic implementation of resolve for teacher<BR/>
     * for teachers, we only provide the type and his/her introduction page on ZJUT web site<BR/>
     * for uniform regard, this information also filled in ResolvedID class.
     *
     * @param passport the passport information going to resolve
     * @return a resolvedID information about this passport, while null if not found or can not resolve
     */
    @Override
    public ResolvedID resolve(Passport passport)
    {
        ResolvedID id = new ResolvedID();

        id.setType(TYPE);

        id.put("URL", URL + passport.getId());

        id.put("name", passport.getName());

        if (passport.hasEmail()) {
            id.put("email", passport.getBindMail());
        }

        return id;
    }

}
