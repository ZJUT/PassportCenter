/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.dao.impl;

import java.util.List;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.SchoolDao;
import rugal.center.core.entity.School;

/**
 *
 * @author rugal
 */
@Repository
public class SchoolDaoImpl extends HibernateBaseDao<School, String> implements SchoolDao
{

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<School> findByName(String name)
    {
        return super.findByPropertyVague("name", name);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public School findById(String id)
    {
        School entity = get(id);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public School save(School bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public School deleteById(String id)
    {
        School entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<School> getEntityClass()
    {
        return School.class;
    }
}
