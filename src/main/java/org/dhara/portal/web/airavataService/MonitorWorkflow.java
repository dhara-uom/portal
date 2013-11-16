/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.dhara.portal.web.airavataService;

import org.apache.airavata.client.api.AiravataAPI;
import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.ws.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MonitorWorkflow {
    private static final Logger log = LoggerFactory.getLogger(MonitorWorkflow.class);

    //TODO this method is already using in another class (correct it)  - WorkflowExecutionServlet.java
    public void monitor(final String experimentId,AiravataAPI airavataAPI) throws AiravataAPIInvocationException, URISyntaxException {
        MonitorListener monitorListener = new MonitorListener();
        Monitor experimentMonitor = airavataAPI.getExecutionManager().getExperimentMonitor(experimentId,
                monitorListener);
        log.info("Started the Workflow monitor");
        experimentMonitor.startMonitoring();
    }

    public static List<MonitorMessage> monitorWorkflow(final String experimentId,AiravataAPI airavataAPI) throws AiravataAPIInvocationException, URISyntaxException, IOException {
        MonitorListener monitorListener = new MonitorListener();
        Monitor experimentMonitor = airavataAPI.getExecutionManager().getExperimentMonitor(experimentId,
                monitorListener);
        log.info("Started the Workflow monitor");

//        writeToFile("Started the Workflow monitor");
        experimentMonitor.startMonitoring();
        airavataAPI.getExecutionManager().waitForExperimentTermination(experimentId);
        List<MonitorMessage> messages = monitorListener.getEvents();
        experimentMonitor.stopMonitoring();
        return messages;
    }

//    public static void writeToFile(String message) throws IOException {
//        String filename= "/home/nipuni/Desktop/Portal/newAddedRepo/portal/src/main/resources/logs";
//        FileWriter fw = new FileWriter(filename,true); //the true will append the new data
//        try
//        {
//            fw.write("\n"+message+"\n");//appends the string to the file
//        }
//        catch(IOException ioe)
//        {
//            System.err.println("IOException: " + ioe.getMessage());
//        }
//        finally {
//            fw.close();
//        }
//    }
}
