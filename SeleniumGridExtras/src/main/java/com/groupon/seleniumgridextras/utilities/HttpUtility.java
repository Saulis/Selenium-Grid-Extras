package com.groupon.seleniumgridextras.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA. User: dima Date: 7/8/14 Time: 3:23 PM To change this template use
 * File | Settings | File Templates.
 */
public class HttpUtility {

  private static Logger logger = Logger.getLogger(HttpUtility.class);


  public static String getRequestAsString(URI uri)
      throws IOException, URISyntaxException {
    return getRequestAsString(uri.toURL());
  }

  public static String getRequestAsString(URL url) throws IOException {

    HttpURLConnection conn = getRequest(url);

    if (conn.getResponseCode() == 200) {
      return StreamUtility.inputStreamToString(conn.getInputStream());
    } else {
      return "";
    }

  }

  public static HttpURLConnection getRequest(URI uri) throws IOException {
    return getRequest(uri.toURL());
  }

  public static HttpURLConnection getRequest(URL url) throws IOException {
    logger.info("Making GET request to " + url);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.setConnectTimeout(10000);
    conn.setReadTimeout(10000);

    logger.info("Response code is " + conn.getResponseCode());
    return conn;
  }

}
