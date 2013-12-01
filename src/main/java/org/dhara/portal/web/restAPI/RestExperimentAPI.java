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

import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.registry.api.exception.worker.ExperimentLazyLoadedException;
import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.exception.PortalException;
import org.dhara.portal.web.helper.ExperimentHelper;
import org.dhara.portal.web.helper.Nodehelper;
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
 * RREST API class for get experiment data
 */
@Controller
public class RestExperimentAPI {

    @Autowired
    private AiravataClientAPIService airavataAPIService;

    /**
     * REST service for get experiment data
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/experimentData", method = RequestMethod.GET)
    @ResponseBody
    public List<ExperimentHelper> handleRequestInternal(Model model, HttpServletRequest httpServletRequest) throws Exception {

        List<ExperimentData> experimentData = airavataAPIService.getExperimentData();

        List<ExperimentHelper> experimentHelpers = buildHelperList(experimentData);

        return experimentHelpers;
    }

    /**
     * Fill helper class with data
     * @param experimentData experiment data to be returned
     * @return
     * @throws ExperimentLazyLoadedException
     * @throws AiravataAPIInvocationException
     * @throws PortalException
     */
    public  List<ExperimentHelper> buildHelperList(List<ExperimentData> experimentData) throws ExperimentLazyLoadedException, AiravataAPIInvocationException, PortalException {

        List<ExperimentHelper> experimentHelpers=new ArrayList<ExperimentHelper>();
        for(int index = 0; index<experimentData.size();index++){
            ExperimentHelper helper = new ExperimentHelper();
            ExperimentData data = experimentData.get(index);
            helper.setName(data.getExperimentName());
            helper.setUpdatedTime(data.getStatusUpdateTime());
            helper.setAuthor(data.getUser());
            helper.setState(data.getState().toString());

            //retrieve experiment data by id
            List<NodeExecutionData> nodeExecutionDataList = airavataAPIService.getWorkflowExperimentData(data.getExperimentId());
            List<Nodehelper> nodehelperList = new ArrayList<Nodehelper>();

            for(int i = 0; i< nodeExecutionDataList.size();i++){

                Nodehelper nodehelper = new Nodehelper();
                nodehelper.setType(nodeExecutionDataList.get(i).getType().name());
                nodehelper.setInput(nodeExecutionDataList.get(i).getInput());
                nodehelper.setOutput(nodeExecutionDataList.get(i).getOutput());
                nodehelper.setWorkflowInstanceNodeId(nodeExecutionDataList.get(i).getWorkflowInstanceNode().getNodeId());
                nodehelperList.add(nodehelper);
            }

            helper.setNodehelperList(nodehelperList);
            experimentHelpers.add(helper);

        }
        experimentHelpers =  reverseList(experimentHelpers);

        return experimentHelpers;
    }



    public ArrayList<ExperimentHelper> reverseList(List<ExperimentHelper> list){

        if(list==null || list.isEmpty())
        {
            return null;
        }

        ArrayList<ExperimentHelper> riversedlist = new ArrayList<ExperimentHelper>(list);

        Collections.reverse(riversedlist);
        return riversedlist;

    }

}
