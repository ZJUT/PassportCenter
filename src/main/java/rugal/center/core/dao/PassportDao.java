package rugal.center.core.dao;

import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Passport;

/**
 *
 * @author Rugal Bernstein
 */
public interface PassportDao
{

    /**
     * get one passport entity by id
     *
     * @param id
     * @return
     */
    Passport findById(String id);

    /**
     * fetch a page according to the sequence of original table
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination getPage(int pageNo, int pageSize);

    /**
     * delete a passport in terms of id
     *
     * @param id
     * @return
     */
    Passport deleteById(String id);

    /**
     * Persist a Java bean into database.
     *
     * @param bean
     * @return
     */
    Passport save(Passport bean);

    /**
     * Find a Passport by ID card number.
     *
     * @param idcard
     * @return
     */
    Passport findByIDcard(String idcard);

    Passport updateByUpdater(Updater<Passport> updater);

    /**
     * get a list of passports in terms of vague search of name field
     *
     * @param name
     * @return
     */
    List<Passport> findByName(String name);
}
