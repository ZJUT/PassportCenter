package rugal.center.core.service;

import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Passport;

/**
 *
 * @author rugal
 */
public interface PassportService
{

    Passport deleteById(String id);

    Passport findById(String id);

    Pagination getPage(int pageNo, int pageSize);

    List<Passport> findByName(String name);

    Passport updatePassport(Passport bean);

    Passport findByIDcard(String idcard);

    /**
     * First check if this passport already exists in database by its id, if do, just return it.
     * Or persist it if not exists.
     *
     * @param bean
     * @return
     */
    Passport save(Passport bean);

    /**
     * Deactivate an passport by update its activation field with false.
     *
     * @param bean
     * @return
     */
    Passport deactivate(Passport bean);

    /**
     * Activate an passport by update its activation field with true.
     *
     * @param bean
     * @return
     */
    Passport activate(Passport bean);
}
