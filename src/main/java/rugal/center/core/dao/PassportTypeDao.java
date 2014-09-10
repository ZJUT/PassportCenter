package rugal.center.core.dao;

import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    PassportType findByID(Short id);

    /**
     *
     * @param bean
     * @return
     */
    PassportType save(PassportType bean);

}
