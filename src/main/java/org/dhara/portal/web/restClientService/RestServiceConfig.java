package org.dhara.portal.web.restClientService;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 10/27/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestServiceConfig {

    private  String serverUrl;
    private  String userName;
    private  String password;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
