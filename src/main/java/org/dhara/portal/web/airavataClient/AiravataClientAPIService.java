package org.dhara.portal.web.airavataClient;

import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.registry.api.exception.worker.ExperimentLazyLoadedException;
import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.airavata.workflow.model.wf.Workflow;
import org.dhara.portal.web.exception.PortalException;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 6/7/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AiravataClientAPIService {
    public List<Workflow> getAllWorkflows() throws PortalException;
    public Workflow getWorkflow(String identifier) throws PortalException;
    public Map<String,Object> executeWorkflow(Map<String,Object> inputs,String workflowId) throws Exception;
    public List<ExperimentData> getExperimentData() throws PortalException, AiravataAPIInvocationException;
}
