package com.codeTest.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class incrementCounterClient {
    private static String url = "http://localhost:8080/counter/value";

    public static void main(String[] args)
    {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.addParameter("increment","5");
        method.addParameter("decrement","1");

        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_CREATED) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            System.out.println(new String(responseBody));

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