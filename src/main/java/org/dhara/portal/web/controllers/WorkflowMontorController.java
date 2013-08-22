package org.dhara.portal.web.controllers;

import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.helper.WorkflowDataHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 8/14/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowMontorController extends AbstractController{
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");

        List<NodeExecutionData> nodeExecutionData = airavataClientAPIService.getWorkflowExecutionData();
        List<WorkflowDataHelper> workflowDataHelperList = new ArrayList<WorkflowDataHelper>();

        for(int index = 0; index<nodeExecutionData.size();index++){
            WorkflowDataHelper helper = new WorkflowDataHelper();
            NodeExecutionData data = nodeExecutionData.get(index);

            helper.setExperimentId(data.getWorkflowInstanceNode().getWorkflowInstance().getExperimentId());
            helper.setType(data.getType().toString());
            helper.setInput(data.getInput());
            helper.setOutput(data.getOutput());
            workflowDataHelperList.add(helper);

        }

        ModelAndView model = new ModelAndView("workflowData");
        model.addObject("message", workflowDataHelperList);
        return model;

    }

}
