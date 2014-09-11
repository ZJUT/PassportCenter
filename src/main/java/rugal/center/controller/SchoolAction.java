package rugal.center.controller;

import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.center.core.entity.School;
import rugal.center.core.service.SchoolService;
import rugal.common.Message;

/**
 * school web service action provided for outer usage.<BR/>
 * to query information about all schools or specified id or specified name.<BR/>
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/school")
public class SchoolAction
{

    @Autowired
    private SchoolService schoolService;

    /**
     * get all schools information with default page size 100 and default page number 0.<BR/>
     * GET /school/all HTTP/1.1
     *
     * @param pageNo   get the page of No. pageNo, default value is 0
     * @param pageSize get the page with size of pageSize, default value is 100
     * @return return all schools information
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object allSchool(@RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "100") int pageSize)
    {
        Pagination page = schoolService.getPage(pageNo, pageSize);
        if (page.getList().isEmpty())
        {
            return Message.failMessage(ExceptionAction.NOT_FOUND);
        }
        return Message.successMessage("", page.getList());
    }

    /**
     * find exact school by id<BR/>
     * GET /school/id/${id} HTTP/1.1
     *
     * @param id the id of school to be find
     * @return JSON data contain this id of school or NOT_FOUND status
     */
    @ResponseBody
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Object findSchoolByID(@PathVariable String id)
    {
        School bean = schoolService.findById(id);
        if (null == bean)
        {
            return Message.failMessage(ExceptionAction.NOT_FOUND);
        }
        return Message.successMessage("", bean);
    }

    /**
     * find school by vague match with name<BR/>
     * GET /school/name/${name} HTTP/1.1
     *
     * @param name key word for search
     * @return JSON data which contains this name, or NOT_FOUND status
     */
    @ResponseBody
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findSchoolByName(@PathVariable String name)
    {
        List<School> list = schoolService.findByName(name);
        if (null == list)
        {
            return Message.failMessage(ExceptionAction.NOT_FOUND);
        }
        return Message.successMessage("", list);
    }
}
