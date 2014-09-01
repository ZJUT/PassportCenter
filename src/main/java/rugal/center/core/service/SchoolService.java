/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service;

import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.School;

/**
 *
 * @author rugal
 */
public interface SchoolService
{

//	School deleteById(Integer id);
    School findById(String id);

    Pagination getPage(int pageNo, int pageSize);

    School save(School bean);

    List<School> findByName(String name);
//    School updateSchool(School bean);
}
