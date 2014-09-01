/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service.idresolve;

import org.springframework.beans.factory.annotation.Autowired;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;
import rugal.center.core.service.MajorService;
import rugal.center.core.service.SchoolService;

/**
 * an abstract class for polymorphic usage
 *
 * @author rugal
 */
public abstract class IDResolver
{

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    public IDResolver()
    {
    }

    /**
     * this is a entrance for polymorphic implementation of resolve for different type of degree
     *
     * @param passport the passport information going to resolve
     * @return a resolvedID information about this passport, while null if not found or can not resolve
     */
    public abstract ResolvedID resolve(Passport passport);

    /**
     * @return the schoolService
     */
    public SchoolService getSchoolService()
    {
        return schoolService;
    }

    /**
     * @param schoolService the schoolService to set
     */
    public void setSchoolService(SchoolService schoolService)
    {
        this.schoolService = schoolService;
    }

    /**
     * @return the majorService
     */
    public MajorService getMajorService()
    {
        return majorService;
    }

    /**
     * @param majorService the majorService to set
     */
    public void setMajorService(MajorService majorService)
    {
        this.majorService = majorService;
    }

}
