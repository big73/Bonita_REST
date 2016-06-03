package fr.actia;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetBugList {

	public static void main(String[] args) throws IOException, IOException, JSONException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	       
        try {
  
        	HttpGet httpget = new HttpGet("https://das01.actia.fr/bugzilla-caddie/rest.cgi/bug?login=yassine.yadine@actia.fr&password=caddie&product=TestProduct");
        	//HttpGet httpget = new HttpGet("https://das01.actia.fr/bugzilla-caddie/rest.cgi/version");
  
            //System.out.println("Executing request " + httpget.getRequestLine());

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
            //System.out.println("----------------------------------------");
            //System.out.println(responseBody);
            
            JSONArray collectionATraiter = new JSONArray(); 
            
            JSONObject jsoEntity = new JSONObject(responseBody);
            JSONObject jsoResult = jsoEntity.getJSONObject("result");            
            JSONArray jsaBugs = jsoResult.getJSONArray("bugs");
            
            for(int i = 0; i < jsaBugs.length(); i++)
            {
            	JSONObject jsoIterator = jsaBugs.getJSONObject(i);
            	int id = jsoIterator.getInt("id"); 
            	String whiteboard = jsoIterator.getString("whiteboard");
            	
            	if(whiteboard.isEmpty())
            	{
            		System.out.println("le bug : "+id+" n'a pas de whiteboard");
            	}
            	else
            	{
            		System.out.println(whiteboard);
            	}
            	/*
            	String status = jsoIterator.getString("status");
            	int id = jsoIterator.getInt("id");            	
            	if(status.equals("IN_PROGRESS"))
            	{
            		System.out.println(id);
            		collectionATraiter.put(jsoIterator);
            	}*/
            }
            
           //System.out.println(collectionATraiter.toString(4));
           //System.out.println(collectionATraiter.length());
           
            
            
            
        } finally {
            httpclient.close();
        }
	}
}
