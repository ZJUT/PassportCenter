/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.dao;

import rugal.center.core.entity.Parameters;

/**
 *
 * @author rugal
 */
public interface ParametersDao
{

    /**
     * delete a parameter with parameter name.
     *
     * @param name
     * @return
     */
    Parameters deleteByName(String name);

    /**
     * get a parameter with parameter name, it must be identical name in database rather than vague one .
     *
     * @param name
     * @return
     */
    Parameters findByName(String name);

    /**
     * save a account bean into database
     *
     * @param bean
     * @return
     */
    Parameters save(Parameters bean);

}
