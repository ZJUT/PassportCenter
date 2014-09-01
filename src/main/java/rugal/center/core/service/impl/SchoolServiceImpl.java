/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service.impl;

import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.SchoolDao;
import rugal.center.core.entity.School;
import rugal.center.core.service.SchoolService;

/**
 *
 * @author rugal
 */
@Service
@Transactional
public class SchoolServiceImpl implements SchoolService
{

    private static final Logger LOG = LoggerFactory.getLogger(SchoolServiceImpl.class.getName());

    @Autowired
    private SchoolDao dao;

    @Override
    public List<School> findByName(String name)
    {
        return dao.findByName(name);
    }

    @Override
    public School findById(String id)
    {
        return dao.findById(id);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public School save(School bean)
    {
        School inner = findById(bean.getSid());
        if (null != inner)
        {
            return inner;
        }
        return dao.save(bean);
    }
//    @Override
//    public School updateSchool(School bean) {
//        Updater<School> updater = new Updater<School>(bean);
//        LOG.info(MessageFormat.format("Updating School id: {0}", bean.getSid()));
//        return dao.updateByUpdater(updater);
//    }
}
