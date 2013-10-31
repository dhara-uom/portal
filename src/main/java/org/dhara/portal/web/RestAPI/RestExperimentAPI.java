package org.dhara.portal.web.RestAPI;

import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 10/31/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/data/experimentData.htm")
public class RestExperimentAPI {

    @Autowired
    private AiravataClientAPIService airavataAPIService;

    @RequestMapping(method = RequestMethod.GET)
    public Response handleRequestInternal(Model model, HttpServletRequest httpServletRequest) throws Exception {

        List<ExperimentData> experimentData = airavataAPIService.getExperimentData();

        Map paramMap = WebUtils.getParametersStartingWith(httpServletRequest, "d-");
        if (paramMap.size() == 0) {
            WebUtils.setSessionAttribute(httpServletRequest, "experiments", experimentData);
        }
        model.addAttribute("data", experimentData);


        return Response.status(Response.Status.OK).entity(experimentData).build();
    }
}
