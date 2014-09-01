/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.ParametersDao;
import rugal.center.core.entity.Parameters;

/**
 *
 * @author rugal
 */
@Repository
public class ParametersDaoImpl extends HibernateBaseDao<Parameters, String> implements ParametersDao
{

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Parameters findByName(String name)
    {
        Parameters entity = get(name);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Parameters save(Parameters bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Parameters deleteByName(String name)
    {
        Parameters entity = super.get(name);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Parameters> getEntityClass()
    {
        return Parameters.class;
    }
}
