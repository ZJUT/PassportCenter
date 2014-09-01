/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service;

import rugal.center.core.entity.Parameters;

/**
 *
 * @author rugal
 */
public interface ParametersService
{

    Parameters findByName(String name);

    String get(String name);

    Integer getInt(String name);

    Integer getInt(String name, Integer defaultValue);

}
