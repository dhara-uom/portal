package org.dhara.portal.web.wpsService52;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.dhara.portal.web.codegen.CodegenUtils;
import org.dhara.portal.web.exception.PortalException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 8/10/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class WPS52NorthConfig {
    private  String serverUrl;
    private  String userName;
    private  String password;
    private  String defaultPackage;

    public WPS52NorthConfig() throws PortalException {
        if(isWPS52NorthConfigurationExists()) {
            set52NorthConfiguration();
        } else {
            setDefaultConfig();
        }
    }

    private boolean isWPS52NorthConfigurationExists() {
        File file=new File("wps-52north.xml");
        return file.exists();
    }

    private void setDefaultConfig() throws PortalException {
        this.setPassword("admin");
        this.setUserName("admin");
        this.setServerUrl("http://localhost:8093/52n-wps-webapp-3.3.0-SNAPSHOT/webAdmin/DynamicDeployProcesstest.jsp");
        this.setDefaultPackage(CodegenUtils.defaultPackage);
    }

    private void set52NorthConfiguration() throws PortalException {
        File file= new File("wps-52north.xml");
        FileInputStream fis;
        XMLInputFactory xif;
        XMLStreamReader reader;
        StAXOMBuilder builder;
        try {
            fis= new FileInputStream(file);
            xif= XMLInputFactory.newInstance();
            reader= xif.createXMLStreamReader(fis);
            builder= new StAXOMBuilder(reader);
        } catch (FileNotFoundException e) {
            throw new PortalException(e.getMessage(),e);
        } catch (XMLStreamException e) {
            throw new PortalException(e.getMessage(),e);
        }
        OMElement documentElement= builder.getDocumentElement();
        OMElement airavataConfiguration=documentElement.getFirstElement();
        OMElement server=airavataConfiguration.getFirstElement();
        this.setPassword(server.getFirstChildWithName(new QName("username")).toString());
        this.setUserName(server.getFirstChildWithName(new QName("password")).toString());
        this.setServerUrl(server.getFirstChildWithName(new QName("server-url")).toString());
    }

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

    public String getDefaultPackage() {
        return defaultPackage;
    }

    public void setDefaultPackage(String defaultPackage) {
        this.defaultPackage = defaultPackage;
    }
}
