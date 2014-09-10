package rugal.center.core.dao.impl;

import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.AccountDao;
import rugal.center.core.entity.Account;
import rugal.center.core.entity.Passport;

/**
 *
 * @author rugal
 */
@Repository
@Transactional
public class AccountDaoImpl extends HibernateBaseDao<Account, String> implements AccountDao
{

    /**
     * {@inheritDoc }
     */
    @Override
    @Transactional(readOnly = true)
    public Integer countAccount(Passport bean)
    {
        return super.countByProperty("id", bean.getId());
    }

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
    public Account findByName(String name)
    {
        Account entity = get(name);
        return entity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Account save(Account bean)
    {
        getSession().save(bean);
        return bean;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Account deleteByName(String name)
    {
        Account entity = super.get(name);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Account> getEntityClass()
    {
        return Account.class;
    }
}
