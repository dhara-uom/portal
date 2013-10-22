package org.dhara.portal.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 10/22/13
 * Time: 6:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowMonitorController extends AbstractController {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //TODO recieve inputs and workflowid use getParameter() method
        String workflowName=request.getParameter("workflowId");

        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");

        ModelAndView model = new ModelAndView("redirect:/workflowList.htm");
        return model;
    }
}