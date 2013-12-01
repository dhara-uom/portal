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
package org.dhara.portal.web.helper;

import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.restAPI.RestWorkflowMonitorAPI;

/**
 * Monitoring helper class
 */
public class MonitorThread implements Runnable {

    private String name;

    private AiravataClientAPIService airavataClientAPIService;
    private int[] inputs;

    public MonitorThread(String threadName, AiravataClientAPIService airavataClientAPIService, int[] inputs){
        this.name = threadName;
        this.airavataClientAPIService = airavataClientAPIService;
        this.inputs = inputs;
    }

    public void run(){
        try {
            RestWorkflowMonitorAPI restWorkflowMonitorAPI = new RestWorkflowMonitorAPI();
            restWorkflowMonitorAPI.getEvents(airavataClientAPIService,inputs,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AiravataClientAPIService getAiravataClientAPIService() {
        return airavataClientAPIService;
    }

    public void setAiravataClientAPIService(AiravataClientAPIService airavataClientAPIService) {
        this.airavataClientAPIService = airavataClientAPIService;
    }

    public int[] getInputs() {
        return inputs;
    }

    public void setInputs(int[] inputs) {
        this.inputs = inputs;
    }
}

