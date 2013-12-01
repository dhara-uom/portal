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
package org.dhara.portal.web.restAPI;

import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.airavataService.AiravataClientAPIServiceImpl;
import org.dhara.portal.web.airavataService.MonitorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Rest service class for workflow monitoring
 */
@Deprecated
@Controller
public class RestWorkflowMonitorAPI implements Observer {

    private static List<MonitorMessage> events = new ArrayList<MonitorMessage>();

    @Autowired
    private AiravataClientAPIService airavataClientAPIService;

    private static String workflowId;


    /**
     * Workflow monitoring REST service
     * @param name
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/monitorData/{workflowId}", "/monitorData/{workflowId}/"}, method = RequestMethod.GET)
    @ResponseBody
    public String handleRequestInternal(@PathParam("workflowId") String name, Model model, HttpServletRequest httpServletRequest) throws Exception {

        workflowId = name;
        String html ="" ;

        for(MonitorMessage message: events){
            String timestamp = message.getTimestamp().toString();
            String status = message.getStatusText();
            String msg = message.getMesssage();
            html = html+"<tr>" +
                    "<td>"+timestamp+"</td>" +
                    "<td>"+status+"</td>" +
                    "<td>"+msg+"</td>" +
                    "</tr>";
        }


         events = new ArrayList<MonitorMessage>();
        return html;
    }

    public void getEvents(AiravataClientAPIService airavataClientAPIService,int[] ints, String workflowName) throws Exception {
         events = new ArrayList<MonitorMessage>();
        ((AiravataClientAPIServiceImpl)airavataClientAPIService).addObserver(this);
        workflowId = workflowName;
        airavataClientAPIService.monitorWorkflow(ints,workflowName);
    }


    public void update(Observable o, Object arg) {

        events.add((MonitorMessage)arg);

    }
}
