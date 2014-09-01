/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.dao;

import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.School;

/**
 *
 *
 * @author rugal
 */
public interface SchoolDao
{

    /**
     * delete a school with id
     *
     * @param id
     * @return
     */
    School deleteById(String id);

    /**
     * find a school by an exact id
     *
     * @param id
     * @return
     */
    School findById(String id);

    /**
     * find a list of school that contain name
     *
     * @param name
     * @return
     */
    List<School> findByName(String name);

    /**
     * fetch a page with table's original sequence
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Pagination getPage(int pageNo, int pageSize);

    /**
     * save a school into database
     *
     * @param bean
     * @return
     */
    School save(School bean);

    School updateByUpdater(Updater<School> updater);
}
