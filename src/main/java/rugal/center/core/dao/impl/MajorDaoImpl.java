/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.dao.impl;

import java.util.List;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.MajorDao;
import rugal.center.core.entity.Major;

/**
 *
 * @author rugal
 */
@Repository
public class MajorDaoImpl extends HibernateBaseDao<Major, String> implements MajorDao
{

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("mid"));
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Major> findByName(String name)
    {
        return super.findByPropertyVague("name", name);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Major findById(String id)
    {
        Major entity = get(id);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Major save(Major bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Major deleteById(String id)
    {
        Major entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Major> getEntityClass()
    {
        return Major.class;
    }
}
