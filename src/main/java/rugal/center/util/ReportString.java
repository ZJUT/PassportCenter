package rugal.center.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rugal Bernnstein
 */
public class ReportString
{

    private static final Logger LOG = LoggerFactory.getLogger(ReportString.class.getName());

    public static final String ERROR_NOT_EXIST = "This passport does not exist";

    public static final String INFO_NEEDS_ACTIVATED = "This passport needs activated first";

    public static final String INFO_CHECK_EMAIL = "Please check email for activation link";

    public static final String INFO_NO_EMAIL = "You have no email for activation";

    public static final String INFO_ALREADY_ACTIVATED = "This passport is already activated";

    public static final String ERROR_INVALID_PASSPORT = "Please validate your passport status";

    public static final String INFO_ACCOUNT_FULL = "You have got enough account";

    public static final String INFO_ACCOUNT_OCCUPIED = "This account name was occupied";

    public static final String ERROR_PARAMETER_EMPTY = "missing required request parameters";

    public static final String INFO_ACTIVATION_SUCCESS = "Activation succeeded!";

    private ReportString()
    {
    }
}
