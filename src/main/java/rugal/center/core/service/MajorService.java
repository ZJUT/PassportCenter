/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service;

import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Major;

/**
 *
 * @author rugal
 */
public interface MajorService
{

    Major deleteById(String id);

    Major findById(String id);

    Pagination getPage(int pageNo, int pageSize);

    Major save(Major bean);

    List<Major> findByName(String name);

    Major updateMajor(Major bean);
}
