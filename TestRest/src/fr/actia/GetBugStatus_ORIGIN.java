package fr.actia;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
public class GetBugStatus_ORIGIN {

    public final static void main(String[] args) throws Exception {
    	
    	/*
    	Properties systemProps = System.getProperties();
        //systemProps.put("javax.net.ssl.keyStorePassword","changeit");
        //systemProps.put("javax.net.ssl.keyStore","changeit");
        systemProps.put("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.7.0_75\\jre\\lib\\security\\cacerts");
        //systemProps.put("javax.net.ssl.trustStorePassword","passwordForTrustStore");
        System.setProperties(systemProps);
        */
     	   	
    	CloseableHttpClient httpclient = HttpClients.createDefault();
       
        try {
  
        	HttpGet httpget = new HttpGet("https://das01.actia.fr/bugzilla-caddie/rest.cgi/bug/1?login=emmanuel.vinel@ext.actia.fr&password=actia31");
        	//HttpGet httpget = new HttpGet("https://das01.actia.fr/bugzilla-caddie/rest.cgi/version");
  
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            
        } finally {
            httpclient.close();
        }
    }

}

