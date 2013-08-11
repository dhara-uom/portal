package org.dhara.portal.web.controllers;

import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dhara.portal.web.airavataClient.AiravataClientAPIService;
import org.dhara.portal.web.airavataClient.AiravataClientAPIServiceImpl;
import org.dhara.portal.web.codegen.CodeGenService;
import org.dhara.portal.web.helper.WorkflowHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 7/29/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowDeploymentController extends AbstractController {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");
        List<Workflow> workflowList=airavataClientAPIService.getAllWorkflows();
        List<WorkflowHelper> workflowHelpers=new ArrayList<WorkflowHelper>();
        for(Workflow workflow:workflowList)
        {
            WorkflowHelper workflowHelper=new WorkflowHelper();
            workflowHelper.setName(workflow.getName());
            workflowHelpers.add(workflowHelper);
        }
        CodeGenService codeGenService= (CodeGenService) context.getBean("codeGenService");
       // String s=codeGenService.getGeneratedClass("EchoWorkflow");
        ModelAndView model = new ModelAndView("workflowListForm");
        model.addObject("workflowList", workflowHelpers);
        return model;

    }

    /*protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String text = "Not used";
        log.debug("Returning hello world text: " + text);
        return text;
    }

    protected Map<String,Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String,Object> modelMap=new HashMap<String, Object>();
        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");
        List<Workflow> workflowList=airavataClientAPIService.getAllWorkflows();
        List<WorkflowHelper> workflowHelpers=new ArrayList<WorkflowHelper>();
        for(Workflow workflow:workflowList)
        {
            WorkflowHelper workflowHelper=new WorkflowHelper();
            workflowHelper.setName(workflow.getName());
            workflowHelpers.add(workflowHelper);
        }
        modelMap.put("workflowList",workflowHelpers);
        return modelMap;
    }*/
}
