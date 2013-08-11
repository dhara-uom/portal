package org.dhara.portal.web.NorthToAiravataConnect;

import org.dhara.portal.web.airavataClient.AiravataClientAPIService;
import org.dhara.portal.web.airavataClient.AiravataClientAPIServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class MainServlet extends javax.servlet.http.HttpServlet {

    private static Map<String, Object> outputs;


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter pw = response.getWriter();

        String input = request.getParameter("input");       //set the inputs to input1 and input2..

        try {


            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            AiravataClientAPIService airavataClientAPIService = (AiravataClientAPIService) context.getBean("airavataAPIService");
            Map<String, Object> inputs = new HashMap<String, Object>();
            inputs.put("input", Integer.parseInt(input));
            String experimentId = airavataClientAPIService.executeWorkflow(inputs, "52NorthTest");      //     _52NorthExecuteWorkFlowTest
            outputs = airavataClientAPIService.getWorkflowOutputs(experimentId);

            JSONObject jsonObject = new JSONObject(outputs);

            PrintWriter printWriter = response.getWriter();

            printWriter.write(jsonObject.toJSONString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
