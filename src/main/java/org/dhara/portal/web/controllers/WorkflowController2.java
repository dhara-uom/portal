package org.dhara.portal.web.controllers;

import org.apache.airavata.workflow.model.wf.Workflow;
import org.dhara.portal.web.airavataClient.AiravataClientAPIServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 8/10/13
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowController2 extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIServiceImpl airavataAPIService=(AiravataClientAPIServiceImpl) context.getBean("airavataAPIService");
        List<Workflow> workflowList =  airavataAPIService.getAllWorkflows();

        ModelAndView model = new ModelAndView("workflows");
        model.addObject("message", workflowList.size());

        return model;
    }
}
