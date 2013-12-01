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
package org.dhara.portal.web.airavataService;

import org.apache.airavata.client.api.AiravataAPI;
import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.ws.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MonitorWorkflow extends Observable implements Observer{
    private static final Logger log = LoggerFactory.getLogger(MonitorWorkflow.class);
    private List<MonitorMessage> messages = new ArrayList<MonitorMessage>();

    /**
     * Start workflow monitoring for workflow execution
     * @param experimentId experiment Id of executed workflow
     * @param airavataAPI  API instance
     * @throws AiravataAPIInvocationException
     * @throws URISyntaxException
     */
    public void monitor(final String experimentId,AiravataAPI airavataAPI) throws AiravataAPIInvocationException, URISyntaxException {
        MonitorListener monitorListener = new MonitorListener();
        Monitor experimentMonitor = airavataAPI.getExecutionManager().getExperimentMonitor(experimentId,
                monitorListener);
        log.info("Started the Workflow monitor");
        experimentMonitor.startMonitoring();
    }

    /**
     * Start workflow monitoring for workflow execution
     * @param experimentId experiment Id of executed workflow
     * @param airavataAPI  API instance
     * @param monitorListener listener class instance which receiving the events
     * @throws AiravataAPIInvocationException
     * @throws URISyntaxException
     */
    public static void monitorWorkflow(final String experimentId,AiravataAPI airavataAPI,
                                       MonitorListener monitorListener) throws AiravataAPIInvocationException, URISyntaxException, IOException {
        Monitor experimentMonitor = airavataAPI.getExecutionManager().getExperimentMonitor(experimentId,
                monitorListener);

        experimentMonitor.startMonitoring();
        airavataAPI.getExecutionManager().waitForExperimentTermination(experimentId);
        experimentMonitor.stopMonitoring();

    }

    /**
     * Event notification
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        getMessages().add((MonitorMessage) arg);
        setChanged();
        notifyObservers(arg);
    }

    public List<MonitorMessage> getMessages() {
        return messages;
    }
}
