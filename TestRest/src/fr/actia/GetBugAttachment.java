package fr.actia;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
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
import org.xml.sax.SAXException;

public class GetBugAttachment {

	public static void main(String[] args) throws IOException, IOException, JSONException, SAXException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	       int idBug = 10;
        try {  
        	HttpGet httpget = new HttpGet("https://das01.actia.fr/bugzilla-caddie/rest.cgi/bug/" + idBug + "/attachment?login=yassine.yadine@actia.fr&password=caddie");
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
            JSONObject jsoBugs = jsoResult.getJSONObject("bugs");
            JSONArray jsaAttachments = jsoBugs.getJSONArray(idBug+"");
            System.out.println(jsaAttachments.getJSONObject(1).getString("file_name"));
            
            
            for(int i = 0; i < jsaAttachments.length(); i++)
            {
            	JSONObject jsoData = jsaAttachments.getJSONObject(i);
            	String dataEncoded = jsoData.getString("data");
            	//System.out.println(dataEncoded);
            	byte[] dataTraitement = Base64.decodeBase64(dataEncoded);
            	
            	//la variable dataDecoded contient le DOM xml bien form�
            	String dataDecoded = new String(dataTraitement);
            	//System.out.println(dataDecoded);
            	    
            }
           
        } finally {
            httpclient.close();
        }
	}
}
