/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.pojo;

import java.util.HashMap;

/**
 * This is not a entity class. resovedID is designed to return to user to show detailed information of given ID in
 * query.<BR/>
 * all resolved ID is consisted by type, school, major and grade.<BR/>
 * But not all type of passport can have complete information in these four data.<BR/>
 * for instance, DOCTOR type does not have major field, because in their metadata is lack of arrangement in these
 * information
 *
 * @author Rugal Bernstein
 */
public class ResolvedID
{

    private final HashMap<String, Object> map;

    private String type;

    public ResolvedID()
    {
        this.map = new HashMap<>(4);
    }

    public Object put(String k, Object v)
    {
        return map.put(k, v);
    }

    public HashMap<String, Object> getMap()
    {
        return map;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
