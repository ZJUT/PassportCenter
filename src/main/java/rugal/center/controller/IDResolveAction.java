package rugal.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.center.core.entity.Passport;
import rugal.center.core.pojo.ResolvedID;
import rugal.center.core.service.PassportService;
import rugal.center.core.service.idresolve.IDResolverUtil;
import rugal.center.util.ReportString;
import rugal.common.Message;

/**
 * ID doResolve service provided for outer usage. A very convenient web service.<BR/>
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/id")
public class IDResolveAction
{

    private static final Logger LOG = LoggerFactory.getLogger(IDResolveAction.class.getName());

    @Autowired
    private IDResolverUtil idResolverUtil;

    @Autowired
    private PassportService passportService;

    /**
     * provide web service that only doResolve those id that are valid and existed in database
     *
     * GET /id/${id}?content={scope} HTTP/1.1<BR/>
     * Where scope is to determine use real individual passport data or just doResolve as general format
     *
     *
     * @param id         given id to be resolved
     * @param individual given doResolve to individual or just general format
     * @return will return a JSON that contain resolved information for given id if successfully executed, whilst return
     *         null either can not find in database or format invalid
     */
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object resolve(@PathVariable String id,
        @RequestParam(required = false, value = "false") boolean individual)
    {
        //TODO this method needs to enhance for individual.
        Passport passport = passportService.findById(id);
        if (null == passport)
        {
            return Message.failMessage(ReportString.ERROR_NOT_EXIST);
        }
        ResolvedID resolvedID = idResolverUtil.resolve(passport);
        if (null == resolvedID)
        {
            return Message.failMessage("Unable to resolve this ID: " + id);
        }
        return Message.successMessage("Here you are", resolvedID);
    }

}
