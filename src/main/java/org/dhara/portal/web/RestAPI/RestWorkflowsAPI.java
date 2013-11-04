package org.dhara.portal.web.RestAPI;

import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.airavata.workflow.model.wf.WorkflowInput;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.helper.InputHelper;
import org.dhara.portal.web.helper.WorkflowHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/3/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RestWorkflowsAPI {

    @Autowired
    private AiravataClientAPIService airavataAPIService;

    @RequestMapping(value = "/workflowData", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkflowHelper> handleRequestInternal(Model model, HttpServletRequest httpServletRequest) throws Exception {

        List<Workflow> workflowList = airavataAPIService.getAllWorkflows();

        List<WorkflowHelper> workflowHelperList = buildHelperList(workflowList);

        return workflowHelperList;
    }

    public  List<WorkflowHelper> buildHelperList(List<Workflow> workflowList) throws Exception {

        List<WorkflowHelper> workflowHelpers=new ArrayList<WorkflowHelper>();
        for(int index = 0; index<workflowList.size();index++){
            WorkflowHelper helper = new WorkflowHelper();
            Workflow workflow = workflowList.get(index);
            helper.setName(workflow.getName());
            List<InputHelper> inputs = new ArrayList<InputHelper>();
            for(WorkflowInput workflowInput:workflow.getWorkflowInputs()) {
                InputHelper inputHelper = new InputHelper();
                inputHelper.setName(workflowInput.getName());
                inputHelper.setType(workflowInput.getType());
                inputs.add(inputHelper);
            }
            helper.setInputs(inputs);
            //TODO get author and created data (need to implement methods)
            workflowHelpers.add(helper);
        }
        workflowHelpers = reverseList(workflowHelpers);

        return workflowHelpers;
    }

    public ArrayList<WorkflowHelper> reverseList(List<WorkflowHelper> list){

        if(list==null || list.isEmpty())
        {
            return null;
        }

        ArrayList<WorkflowHelper> riversedlist = new ArrayList<WorkflowHelper>(list);

        Collections.reverse(riversedlist);
        return riversedlist;

    }

}
