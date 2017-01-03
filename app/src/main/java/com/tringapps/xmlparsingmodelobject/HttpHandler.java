package com.tringapps.xmlparsingmodelobject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by geethu on 1/12/16.
 */
public class HttpHandler {


    HttpHandler()
    {

    }


    public InputStream loadXmlFromNetwork(String reqUrl) {
        InputStream in = null;

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            in = con.getInputStream();



        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return in;
    }
}
