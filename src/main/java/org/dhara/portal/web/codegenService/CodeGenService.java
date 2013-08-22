package org.dhara.portal.web.codegenService;

import org.dhara.portal.web.exception.PortalException;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 7/26/13
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CodeGenService {

    public String getGeneratedClass(String workflowId) throws PortalException;

    public String getGeneratedClassForCustomDeployment(String worklfowId,Map<String,String> inputsMapping, Map<String,String> outputsMapping,String extendingAlgorithm);
}
