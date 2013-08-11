package org.dhara.portal.web.airavataClient;

import org.apache.airavata.client.AiravataAPIFactory;
import org.apache.airavata.client.api.AiravataAPI;
import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.client.api.WorkflowManager;
import org.apache.airavata.registry.api.PasswordCallback;
import org.apache.airavata.registry.api.exception.worker.ExperimentLazyLoadedException;
import org.apache.airavata.registry.api.impl.WorkflowExecutionDataImpl;
import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.apache.airavata.registry.api.workflow.OutputData;
import org.apache.airavata.rest.client.PasswordCallbackImpl;
import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.airavata.workflow.model.wf.WorkflowInput;
import org.dhara.portal.web.exception.PortalException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 6/7/13
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class AiravataClientAPIServiceImpl implements AiravataClientAPIService{

    private AiravataConfig airavataConfig;

    public List<Workflow> getAllWorkflows() throws PortalException {
        List<Workflow> workflows = null;
        AiravataAPI airavataAPI=getAiravataAPI();
        WorkflowManager workflowManager=airavataAPI.getWorkflowManager();
        try {
            workflows=workflowManager.getWorkflows();
        } catch (AiravataAPIInvocationException e) {
            e.printStackTrace();
        }
        return workflows;
    }

    public List<ExperimentData> getExperimentData() throws PortalException, AiravataAPIInvocationException {
        AiravataAPI airavataAPI=getAiravataAPI();
        List<ExperimentData> data = airavataAPI.getProvenanceManager().getWorkflowExperimentDataList();

        return data;
    }

    public Workflow getWorkflow(String identifier) throws PortalException {
        AiravataAPI airavataAPI=getAiravataAPI();
        Workflow workflow = null;
        WorkflowManager workflowManager=airavataAPI.getWorkflowManager();
        try {
            workflow=workflowManager.getWorkflow(identifier);
        } catch (AiravataAPIInvocationException e) {
            e.printStackTrace();
        }
        return workflow;
    }

    public Map<String,Object> executeWorkflow(Map<String, Object> inputs, String workflowId) throws Exception {
        AiravataAPI airavataAPI=getAiravataAPI();
        Workflow workflow = airavataAPI.getWorkflowManager().getWorkflow(workflowId);
        List<WorkflowInput> workflowInputs = workflow.getWorkflowInputs();
        for (WorkflowInput workflowInput : workflowInputs) {
            Object value=inputs.get(workflowInput.getName());
            if ("int".equals(workflowInput.getType())||"integer".equals(workflowInput.getType())) {
                workflowInput.setValue((Integer)value);
            } else if("String".equals(workflowInput.getType())){
                workflowInput.setValue((String)value);
            } else {
                workflowInput.setValue((Object)value);
            }
        }

        String experimentId=airavataAPI.getExecutionManager().runExperiment(workflowId, workflowInputs);
        Map<String,Object> outputs=new HashMap<String, Object>();
        MonitorWorkflow monitorWorkflow=new MonitorWorkflow();
        monitorWorkflow.monitor(experimentId,airavataAPI);
        airavataAPI.getExecutionManager().waitForExperimentTermination(experimentId);
        ExperimentData experimentData =airavataAPI.getProvenanceManager().getExperimentData(experimentId);
        List<WorkflowExecutionDataImpl> workflowInstanceData = experimentData.getWorkflowExecutionDataList();
        for (WorkflowExecutionDataImpl executionDataImpl : workflowInstanceData) {
            List<NodeExecutionData> nodeDataList = executionDataImpl.getNodeDataList();
            for (NodeExecutionData nodeExecutionData : nodeDataList) {
                List<OutputData> outputData = nodeExecutionData.getOutputData();
                for (OutputData data : outputData) {
                    outputs.put(data.getName(),data.getValue());
                }
            }
        }
        return outputs;
    }

    private AiravataAPI getAiravataAPI() throws PortalException {
            AiravataAPI airavataAPI;
            int port = airavataConfig.getPort();
            String serverUrl = airavataConfig.getServerUrl();
            String serverContextName = airavataConfig.getServerContextName();
            String username = airavataConfig.getUserName();
            String password = airavataConfig.getPassword();
            String gatewayName = airavataConfig.getGatewayName();
            String registryURL = "http://" + serverUrl + ":" + port + "/" + serverContextName + "/api";
            AiravataAPI api= null;
            try{
                PasswordCallback passwordCallback = new PasswordCallbackImpl(username, password);
                api = AiravataAPIFactory.getAPI(new URI(registryURL), gatewayName, username, passwordCallback);
                airavataAPI = api;
            } catch (Exception e) {
                throw new PortalException("Error creating airavata api instance",e);
            }
            return airavataAPI;
    }

    public void setAiravataConfig(AiravataConfig airavataConfig) {
        this.airavataConfig = airavataConfig;
    }
}
