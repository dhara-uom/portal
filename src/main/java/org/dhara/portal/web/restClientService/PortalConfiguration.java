package org.dhara.portal.web.restClientService;

import org.dhara.portal.web.exception.PortalException;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/14/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortalConfiguration {

    private RestServiceConfig restServiceConfig;

    public PortalConfiguration() throws PortalException {

        setRestServiceConfig(new RestServiceConfig());
        setDefaultConfig();
    }

    /**
     * Set defualt config
     * @throws org.dhara.portal.web.exception.PortalException
     */
    private void setDefaultConfig() throws PortalException {


        //rest default service configs
        this.getRestServiceConfig().setPassword("canonical");
        this.getRestServiceConfig().setUserName("canonical");
        this.getRestServiceConfig().setServerUrl("http://localhost:8080/portal/app/");


    }



    public RestServiceConfig getRestServiceConfig() {
        return restServiceConfig;
    }

    public void setRestServiceConfig(RestServiceConfig restServiceConfig) {
        this.restServiceConfig = restServiceConfig;
    }


}
