package rugal.center.core.dao;

import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Major;

/**
 *
 * Does not canceled the delete and save methods because of the need of create
 * and destroy majors might happen each year
 *
 * @author rugal
 */
public interface MajorDao
{

    /**
     * delete a major bean with id
     *
     * @param id
     * @return
     */
    Major deleteById(String id);

    /**
     * get a major that has id
     *
     * @param id
     * @return
     */
    Major findById(String id);

    /**
     * get list of majors that contain name
     *
     * @param name
     * @return
     */
    List<Major> findByName(String name);

    /**
     * fetch a page with database defined order
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination getPage(int pageNo, int pageSize);

    /**
     * save a major bean into database
     *
     * @param bean
     * @return
     */
    Major save(Major bean);

    Major updateByUpdater(Updater<Major> updater);
}
