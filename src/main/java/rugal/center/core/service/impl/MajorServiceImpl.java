package rugal.center.core.service.impl;

import java.text.MessageFormat;
import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.MajorDao;
import rugal.center.core.entity.Major;
import rugal.center.core.service.MajorService;

/**
 *
 * @author rugal
 */
@Service
@Transactional
public class MajorServiceImpl implements MajorService
{

    private static final Logger LOG = LoggerFactory.getLogger(MajorServiceImpl.class.getName());

    @Autowired
    private MajorDao dao;

    @Override
    public Major deleteById(String id)
    {
        return dao.deleteById(id);
    }

    @Override
    public List<Major> findByName(String name)
    {
        return dao.findByName(name);
    }

    @Override
    public Major findById(String id)
    {
        return dao.findById(id);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public Major save(Major bean)
    {
        Major inner = dao.findById(bean.getMid());
        if (null != inner)
        {
            return inner;
        } else
        {
            return dao.save(bean);
        }

    }

    @Override
    public Major updateMajor(Major bean)
    {
        Updater<Major> updater = new Updater<Major>(bean);
        LOG.info(MessageFormat.format("Updating Major id: {0}", bean.getMid()));
        return dao.updateByUpdater(updater);
    }
}
