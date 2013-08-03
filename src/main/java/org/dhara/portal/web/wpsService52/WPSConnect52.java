package org.dhara.portal.web.wpsService52;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class WPSConnect52 {

    private final static String USER_AGENT = "Mozilla/5.0";
    private static String url = "http://localhost:8093/52n-wps-webapp-3.2.0-SNAPSHOT/webAdmin/DynamicDeployProcesstest.jsp";


    public static void uploadClass() throws IOException {

        String encoded = URLEncoder.encode(generatedClass.dynamicDeployer, "UTF8");
        String inputAdjusted = "input=" + encoded;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        // optional default is GET
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", "" + Integer.toString(inputAdjusted.getBytes().length));
        con.setUseCaches(false);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(inputAdjusted);
        wr.flush();
        wr.close();
        con.disconnect();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + encoded);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    public static void setURL(String url52) {
        url = url52;
    }


}
