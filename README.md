Dhara- A geoscinece gateway

Backend Web Application for access Apache Airvata API. Since our main portal conflicts with versions of dependency jars use in 
Apache Airavata, we built this web application as a proxy for Access Apache Airavata API and expose it as REST API.

Build instructions

1) Clone portal for your environment

2) Execute "mvn clean install" inside the clone directory.

3) Then you can find war distribution of this web application inside target folder.

4) Configurations are kept in a configuration file inside conf folder of the web application.

5) Change it for meet with your configurations
