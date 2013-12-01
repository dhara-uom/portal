/***********************************************************************************************************************
 *
 * Dhara- A Geoscience Gateway
 * ==========================================
 *
 * Copyright (C) 2013 by Dhara
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/
package org.dhara.portal.web.controllers;

import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.airavata.workflow.model.wf.WorkflowInput;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.helper.InputHelper;
import org.dhara.portal.web.helper.WorkflowHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
public class WorkflowController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");

        List<Workflow> workflowList = airavataClientAPIService.getAllWorkflows();

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


        Map paramMap = WebUtils.getParametersStartingWith(httpServletRequest, "d-");
        if (paramMap.size() == 0) {
            WebUtils.setSessionAttribute(httpServletRequest, "workflows", workflowHelpers);
        }

        ModelAndView model = new ModelAndView("workflows");
        model.addObject("message", workflowHelpers);
        return model;
    }


}
