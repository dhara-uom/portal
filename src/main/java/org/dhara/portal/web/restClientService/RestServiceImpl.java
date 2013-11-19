package org.dhara.portal.web.restClientService;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 10/26/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestServiceImpl implements RestService {

    private RestServiceConfig restServiceConfig;
    private PortalConfiguration portalConfiguration;
    private RestClient restClient;

    public RestServiceImpl() {
        setRestClient(new RestClient());
    }

    @Override
    public  WorkflowInputHelper monitorExperiment() throws IOException {
        String response= getRestClient().getResponse(getRestServiceConfig().getServerUrl() + RestResourceUtils.INPUTDATA_RESOURCE);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, new TypeReference<WorkflowInputHelper>(){});
    }

    public RestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void setRestServiceConfig(RestServiceConfig restServiceConfig) {
        this.restServiceConfig = restServiceConfig;
    }

    public RestServiceConfig getRestServiceConfig() {
        return restServiceConfig;
    }

    public PortalConfiguration getPortalConfiguration() {
        return portalConfiguration;
    }

    public void setPortalConfiguration(PortalConfiguration portalConfiguration) {
        this.portalConfiguration = portalConfiguration;
        restServiceConfig=portalConfiguration.getRestServiceConfig();
    }
}
