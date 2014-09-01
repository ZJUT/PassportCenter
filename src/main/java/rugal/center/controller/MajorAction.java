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
import rugal.center.core.entity.Major;
import rugal.center.core.service.MajorService;
import rugal.common.Message;

/**
 * major web service action provided for outer request.<BR/>
 * to query information about all majors or specified id or specified name.<BR/>
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/major")
public class MajorAction
{

    @Autowired
    private MajorService majorService;

    /**
     * get major information by page, with default page size of 100 and page number 1<BR/>
     * GET /major/all HTTP/1.1
     *
     * @param pageNo   get the page of No. pageNo, default value is 0
     * @param pageSize get the page with size of pageSize, default value is 100
     * @return return JSON format data with status in it
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object allMajors(@RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "20") int pageSize)
    {
        Pagination page = majorService.getPage(pageNo, pageSize);
        return Message.successMessage("", page.getList());
    }

    /**
     * get given id major information.<BR/>
     * GET /major/id/${id} HTTP/1.1
     *
     * @param id the id that to find
     * @return JSON that contain given id or NOT_FOUND status
     */
    @ResponseBody
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Object findMajorByID(@PathVariable String id)
    {
        Major bean = majorService.findById(id);
        return Message.successMessage("", bean);
    }

    /**
     * find majors information by vague match with give key word name<BR/>
     * GET /major/name/${name} HTTP/1.1
     *
     * @param name the key word to be searched
     * @return return JSON data contain the majors information, or NOT_FOUND status
     */
    @ResponseBody
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Object findMajorByName(@PathVariable String name)
    {
        List<Major> list = majorService.findByName(name);
        return Message.successMessage("", list);
    }
}
