package rugal.center.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rugal.center.core.dao.ParametersDao;
import rugal.center.core.entity.Parameters;
import rugal.center.core.service.ParametersService;

/**
 *
 * @author rugal
 */
@Service
@Transactional
public class ParametersServiceImpl implements ParametersService
{

    private static final Logger LOG = LoggerFactory.getLogger(ParametersServiceImpl.class.getName());

    @Autowired
    private ParametersDao dao;

    @Override
    public Parameters findByName(String name)
    {
        return dao.findByName(name);
    }

    @Override
    public String get(String name)
    {
        Parameters parameter = dao.findByName(name);
        if (null == parameter)
        {
            return null;
        }
        return parameter.getValue();
    }

    @Override
    public Integer getInt(String name)
    {
        Parameters parameter = dao.findByName(name);
        if (null == parameter)
        {
            return null;
        }
        Integer value = null;
        try
        {
            value = Integer.parseInt(parameter.getValue());
        } catch (NumberFormatException e)
        {
            value = null;
        }
        return value;
    }

    @Override
    public Integer getInt(String name, Integer defaultValue)
    {
        Integer value = getInt(name);
        if (null == value)
        {
            value = defaultValue;
        }
        return value;
    }

}
