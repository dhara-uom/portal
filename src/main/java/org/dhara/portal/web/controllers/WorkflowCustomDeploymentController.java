package org.dhara.portal.web.controllers;

import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dhara.portal.web.airavataClient.AiravataClientAPIService;
import org.dhara.portal.web.helper.WorkflowHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 8/9/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowCustomDeploymentController extends SimpleFormController {
    protected final Log log = LogFactory.getLog(getClass());

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
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
    }
}
