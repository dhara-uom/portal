package org.dhara.portal.web.RestAPI;

import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.log4j.Logger;
import org.dhara.portal.web.airavataService.AiravataClientAPIService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 10/29/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/portal/experiments")
public class ExperimentAPI {

    static Logger log = Logger.getLogger(ExperimentAPI.class);

    @Autowired
    private AiravataClientAPIService airavataClientAPIService;

    @GET
    public Response handleRequestInternal() throws Exception {

        List<ExperimentData> experimentData = airavataClientAPIService.getExperimentData();

        //This gives a null pointer exception since airavataClientAPIService object is a null object
        return Response.status(Response.Status.OK).entity(experimentData).build();
    }
}
