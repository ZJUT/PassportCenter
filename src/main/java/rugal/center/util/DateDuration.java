package rugal.center.util;

import java.util.Date;

/**
 *
 * @author Rugal Bernstein
 */
public class DateDuration
{

    private DateDuration()
    {

    }

    public static long getCurrentInternal(int internal)
    {
        long time = (new Date()).getTime() / 1000 - internal;
        return time;
    }
}
