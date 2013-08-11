package org.dhara.portal.web.codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.airavata.workflow.model.component.ws.WSComponentPort;
import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.airavata.workflow.model.wf.WorkflowInput;
import org.dhara.portal.web.airavataClient.AiravataClientAPIService;
import org.dhara.portal.web.exception.PortalException;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 7/26/13
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CodeGenServiceImpl implements CodeGenService{
    private AiravataClientAPIService airavataClientAPIService;

    public String getGeneratedClass(String workflowId) throws PortalException {
        Workflow workflow=airavataClientAPIService.getWorkflow(workflowId);
        List<WorkflowInput> workflowInputs = null;
        List<WSComponentPort> workflowOutputs = null;

        try {
            workflowInputs = workflow.getWorkflowInputs();
            workflowOutputs = workflow.getOutputs();
        } catch (Exception e) {
            throw new PortalException("Error getting workflow with id="+workflowId,e);
        }

        List<String> inputIds=new ArrayList<String>();
        List<String> outputIds=new ArrayList<String>();
        Map<String,String> inputBindings=new HashMap<String, String>();
        Map<String,String> outputBindings=new HashMap<String, String>();
        ArrayList<CodeGenInOutAssociations> inputs=new ArrayList<CodeGenInOutAssociations>();
        ArrayList<CodeGenInOutAssociations> outputs=new ArrayList<CodeGenInOutAssociations>();
        for (WorkflowInput workflowInput : workflowInputs) {
            inputIds.add(workflowInput.getName());
            CodeGenInOutAssociations codeGenInOutAssociations=new CodeGenInOutAssociations();
            if("int".equalsIgnoreCase(workflowInput.getType()) || "integer".equalsIgnoreCase(workflowInput.getType()) || "IntegerParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_INT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_INT_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else if ("string".equalsIgnoreCase(workflowInput.getType()) || "StringParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_STRING_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_STRING_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else if ("short".equalsIgnoreCase(workflowInput.getType()) || "ShortParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_SHORT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_SHORT_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else if ("double".equalsIgnoreCase(workflowInput.getType()) || "DoubleParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_DOUBLE_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_DOUBLE_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else if ("float".equalsIgnoreCase(workflowInput.getType()) || "FloatParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_FLOAT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_FLOAT_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else if ("boolean".equalsIgnoreCase(workflowInput.getType()) || "BooleanParameterType".equalsIgnoreCase(workflowInput.getType())) {
                inputBindings.put(workflowInput.getName()+"_TypeClass", CodegenUtils.LITERAL_BOOLEAN_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowInput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_BOOLEAN_BINDING);
                inputs.add(codeGenInOutAssociations);
            } else {
                throw new PortalException("Error in input bindings where"+workflowInput.getName()+"not compatible for any binding");
            }
        }

        for (WSComponentPort workflowOutput : workflowOutputs) {
            CodeGenInOutAssociations codeGenInOutAssociations=new CodeGenInOutAssociations();
            outputIds.add(workflowOutput.getName());
            if("int".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "integer".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "IntegerParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_INT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_INT_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else if ("string".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "StringParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_STRING_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_STRING_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else if ("short".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "ShortParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_SHORT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_SHORT_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else if ("double".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "DoubleParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_DOUBLE_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_DOUBLE_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else if ("float".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "FloatParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_FLOAT_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_FLOAT_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else if ("boolean".equalsIgnoreCase(workflowOutput.getType().getLocalPart()) || "BooleanParameterType".equalsIgnoreCase(workflowOutput.getType().getLocalPart())) {
                inputBindings.put(workflowOutput.getName()+"_TypeClass", CodegenUtils.LITERAL_BOOLEAN_BINDING);
                codeGenInOutAssociations.setIdentifier(workflowOutput.getName());
                codeGenInOutAssociations.setMappingClass(CodegenUtils.LITERAL_BOOLEAN_BINDING);
                outputs.add(codeGenInOutAssociations);
            } else {
                throw new PortalException("Error in output bindings where"+workflowOutput.getName()+"not compatible for any binding");
            }
        }
        String classContents=generateClassFromTemplate(inputIds,outputIds,workflow.getName(),CodegenUtils.defaultExtendingClass,inputBindings,outputBindings,inputs,outputs);
        return classContents;
    }

    public String getGeneratedClassForCustomDeployment(String worklfowId, Map<String, String> inputsMapping, Map<String, String> outputsMapping, String extendingAlgorithm) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String generateClassFromTemplate(List<String> inputIdentifiersList,List<String> outputIdentifiersList, String className,String extendingClass, Map<String,String> inputBindingsList,
                                             Map<String,String> outputBindingsList, ArrayList<CodeGenInOutAssociations> inputMappings,ArrayList<CodeGenInOutAssociations> outputMappings) throws PortalException {
        Configuration cfg = new Configuration();
        try {
            //Load template from source folder
            //String current = new java.io.File( "." ).getCanonicalPath();
            //fg.setDirectoryForTemplateLoading(new File(
            //current));
            Template template = cfg.getTemplate("wpstemplate.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("package", CodegenUtils.defaultPackage);
            data.put("processName", className);
            data.put("extendingClass", extendingClass);

            //List parsing
            List<String> imports = CodegenUtils.getImports();
            List<String> inputIdentifiers = inputIdentifiersList;
            List<String> outputIdentifiers = outputIdentifiersList;

            data.put("imports", imports);
            data.put("inputIdentifiers", inputIdentifiers);
            data.put("outputIdentifiers", outputIdentifiers);
            data.put("inputMappings",inputMappings);
            data.put("outputMappings",outputMappings);
            data.put("executionServlet",CodegenUtils.EXECUTION_SERVLET);

            Map<String,String> inputBindings =inputBindingsList;
            Set<Map.Entry<String, String>> entriesIn = inputBindings.entrySet();
            for ( Map.Entry<String, String> entry : entriesIn ) {
                if (!data.containsKey(entry.getKey())) {
                    data.put(entry.getKey(), entry.getValue());
                }
            }

            Map<String,String> outputBindings =outputBindingsList;
            Set<Map.Entry<String, String>> entriesOut = inputBindings.entrySet();
            for ( Map.Entry<String, String> entry : entriesOut ) {
                if (!data.containsKey(entry.getKey())) {
                    data.put( entry.getKey(), entry.getValue() );
                }
            }


            Writer file = new FileWriter(new File(className+".java"));
            template.process(data, file);
            file.flush();
            file.close();

            StringBuilder builder=new StringBuilder();
            String currentLine;
            BufferedReader br;
            br = new BufferedReader(new FileReader(className+".java"));
            while ((currentLine = br.readLine()) != null) {
                builder.append(currentLine);
            }
                return builder.toString();

        } catch (IOException e) {
            throw new PortalException("Error while file operations",e);
        } catch (TemplateException e) {
            throw new PortalException("Template exception when generating the template");
        }
    }
    public AiravataClientAPIService getAiravataClientAPIService() {
        return airavataClientAPIService;
    }

    public void setAiravataClientAPIService(AiravataClientAPIService airavataClientAPIService) {
        this.airavataClientAPIService = airavataClientAPIService;
    }
}
