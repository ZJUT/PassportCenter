package rugal.center.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.AccountDao;
import rugal.center.core.entity.Account;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.AccountService;

/**
 *
 * @author rugal
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService
{

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    private AccountDao dao;

    @Override
    public Integer countAccount(Passport bean)
    {
        return dao.countAccount(bean);
    }

    @Override
    public Account deleteByName(String name)
    {
        return dao.deleteByName(name);
    }

    @Override
    public Account findByName(String name)
    {
        return dao.findByName(name);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public Account save(Account bean)
    {
        return dao.save(bean);
    }

    @Override
    public Account updateAccount(Account bean)
    {
        Updater<Account> updater = new Updater<>(bean);
//        LOG.info(MessageFormat.format("Updating Account username: {0}", bean.getUsername()));
        return dao.updateByUpdater(updater);
    }
}
