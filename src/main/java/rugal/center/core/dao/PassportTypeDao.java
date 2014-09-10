package rugal.center.core.dao;

import rugal.center.core.entity.PassportType;

/**
 *
 * @author Rugal Bernstein
 */
public interface PassportTypeDao
{

    /**
     *
     * @param id
     * @return
     */
    PassportType deleteByID(Short id);

    /**
     *
     * @param id
     * @return
     */
    PassportType findByID(Short id);

    /**
     *
     * @param bean
     * @return
     */
    PassportType save(PassportType bean);

}
