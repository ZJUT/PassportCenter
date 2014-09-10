package rugal.center.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.PassportTypeDao;
import rugal.center.core.entity.PassportType;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class PassportTypeDaoImpl extends HibernateBaseDao<PassportType, Short> implements
    PassportTypeDao
{

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public PassportType findByID(Short id)
    {
        PassportType entity = get(id);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PassportType save(PassportType bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PassportType deleteByID(Short id)
    {
        PassportType entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<PassportType> getEntityClass()
    {
        return PassportType.class;
    }
}
