package com.codeTest.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class getCounterClient
{
    private static String url = "http://localhost:8080/counter/value";

      public static void main(String[] args)
      {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);

          try
          {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
              System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            System.out.println("counter is " + new String(responseBody));
              
          } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
          } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
          } finally {
            method.releaseConnection();
          }
      }
}