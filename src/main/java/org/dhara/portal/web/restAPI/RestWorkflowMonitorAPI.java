package org.dhara.portal.web.restAPI;

import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.dhara.portal.web.airavataService.AiravataClientAPIServiceImpl;
import org.dhara.portal.web.airavataService.MonitorMessage;
import org.dhara.portal.web.restClientService.RestServiceImpl;
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
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/16/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RestWorkflowMonitorAPI implements Observer {

    private static List<MonitorMessage> events = new ArrayList<MonitorMessage>();

    @Autowired
    private AiravataClientAPIService airavataClientAPIService;

    private static String workflowId;

    @Autowired
    private RestServiceImpl restService;

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
