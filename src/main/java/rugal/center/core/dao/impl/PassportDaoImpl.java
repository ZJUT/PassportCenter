package rugal.center.core.dao.impl;

import java.util.List;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.PassportDao;
import rugal.center.core.entity.Passport;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
@Transactional
public class PassportDaoImpl extends HibernateBaseDao<Passport, String> implements PassportDao
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
    public Passport findById(String id)
    {
        Passport entity = get(id);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public List<Passport> findByName(String name)
    {
        return super.findByPropertyVague("name", name);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Passport findByIDcard(String idcard)
    {
        return super.findUniqueByProperty("idcard", idcard);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Passport save(Passport bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Passport deleteById(String id)
    {
        Passport entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Passport> getEntityClass()
    {
        return Passport.class;
    }
}
