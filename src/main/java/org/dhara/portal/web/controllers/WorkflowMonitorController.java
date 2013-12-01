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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.airavataService.AiravataConfig;
import org.dhara.portal.web.airavataService.MonitorMessage;
import org.dhara.portal.web.helper.InputHelper;
import org.dhara.portal.web.helper.MonitorThread;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Deprecated
public class WorkflowMonitorController extends AbstractController {

    protected final Log log = LogFactory.getLog(getClass());
    private List<MonitorMessage> events = new ArrayList<MonitorMessage>();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String workflowName=request.getParameter("workflowId");
        List<InputHelper> inputHelperList = new ArrayList<InputHelper>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){

            String temp = (String) enumeration.nextElement();
            if(temp.equalsIgnoreCase("workflowId"));
            else {
                 InputHelper inputHelper = new InputHelper();
                 inputHelper.setRawName(temp);
                 inputHelper.setName(temp.split("/")[0]);
                 inputHelper.setType(temp.split("/")[1]);
                 inputHelper.setValues(request.getParameterValues(temp));
                 inputHelperList.add(inputHelper);
            }
        }

        List<Integer> inputs = new ArrayList<Integer>();
        for (InputHelper in:inputHelperList){
            if(in.getType().equalsIgnoreCase("(int)")){
                for (int i=0;i<in.getValues().length;i++){
                    inputs.add(Integer.parseInt(in.getValues()[i]));
                }
            }
        }

        int[] ints = ArrayUtils.toPrimitive(inputs.toArray(new Integer[inputs.size()]));

        ApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AiravataClientAPIService airavataClientAPIService= (AiravataClientAPIService) context.getBean("airavataAPIService");

        Runnable monitorThread = new MonitorThread(workflowName,airavataClientAPIService,ints);
        AiravataConfig.executor.execute(monitorThread);

        ModelAndView model = new ModelAndView("monitoring");
        model.addObject("events", events);
        return model;
    }


}