package rugal.center.core.service.impl;

import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.PassportDao;
import rugal.center.core.entity.Passport;
import rugal.center.core.service.PassportService;

/**
 *
 * @author rugal
 */
@Service
@Transactional
public class PassportServiceImpl implements PassportService
{

    private static final Logger LOG = LoggerFactory.getLogger(PassportServiceImpl.class.getName());

    @Autowired
    private PassportDao dao;

    @Override
    public Passport deleteById(String id)
    {
        return dao.deleteById(id);
    }

    @Override
    public Passport findById(String id)
    {
        return dao.findById(id);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public Passport findByIDcard(String idcard)
    {
        return dao.findByIDcard(idcard);
    }

    @Override
    public List<Passport> findByName(String name)
    {
        return dao.findByName(name);
    }

    @Override
    public Passport updatePassport(Passport bean)
    {
        Updater<Passport> updater = new Updater<>(bean);
//        LOG.debug(MessageFormat.format("Updating Passport id: {0}", bean.getId()));
        return dao.updateByUpdater(updater);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Passport activate(Passport bean)
    {
        bean.setActivated(1);
        return this.updatePassport(bean);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Passport deactivate(Passport bean)
    {
        bean.setActivated(0);
        return this.updatePassport(bean);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Passport save(Passport bean)
    {
        Passport inner = findById(bean.getId());
        if (null != inner)
        {
            return inner;
        }
        return dao.save(bean);
    }
}
