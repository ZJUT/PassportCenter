/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.util;

import java.util.HashMap;
import java.util.Map;
import rugal.center.core.entity.Passport;

/**
 *
 * @author Rugal Bernstein
 */
public class NameResolver
{

    public static final String TEACHER = "teacher";

    public static final String BACHELOR = "bachelor";

    public static final String GRADUATE = "graduate";

    public static final String DOCTOR = "doctor";

    private static final Map<String, String> map = new HashMap<>(4);

    static {
        map.put(DOCTOR.substring(0, 1), DOCTOR);
        map.put(BACHELOR.substring(0, 1), BACHELOR);
        map.put(GRADUATE.substring(0, 1), GRADUATE);
        map.put(TEACHER.substring(0, 1), TEACHER);
    }

    public static String resolve(String shorten)
    {
        return map.get(shorten.toLowerCase());
    }

    public static String resolve(Passport passport)
    {
        return resolve(passport.getType());
    }

    private NameResolver()
    {
    }
}
