package rugal.center.core.dao;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Account;
import rugal.center.core.entity.Passport;

/**
 *
 * @author rugal
 */
public interface AccountDao
{

    /**
     * Count the total account number of a passport.
     *
     * @param bean
     * @return
     */
    Integer countAccount(Passport bean);

    /**
     * delete a account with id
     *
     * @param name
     * @return
     */
    Account deleteByName(String name);

    /**
     * get a list of accounts that have the name
     *
     * @param name
     * @return
     */
    Account findByName(String name);

    /**
     * get a page with database defined order
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination getPage(int pageNo, int pageSize);

    /**
     * save a account bean into database
     *
     * @param bean
     * @return
     */
    Account save(Account bean);

    Account updateByUpdater(Updater<Account> updater);
}
