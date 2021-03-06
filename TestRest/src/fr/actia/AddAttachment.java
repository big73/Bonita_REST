package fr.actia;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddAttachment {

	public static void main(String[] args) throws JSONException, IOException {

		JSONObject jso = new JSONObject();
		
		JSONArray jsaIdsValue = new JSONArray();
		jsaIdsValue.put(3);
		
		String fileName = "data_updated_ticket_5.xml";
		
		String summary = "le nouveau fichier XML";
		
		String contentType = "text/plain";
				
		//File file = new File("final\\data_updated_ticket_5.xml");
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/yassine/Developpement/Repositories/Bonita_REST/TestRest/src/fr/actia/final/data_updated_ticket_5.xml")));
		String line;
		StringBuilder fluxXML = new StringBuilder();

		while((line=br.readLine())!= null){
		    fluxXML.append(line);
		}
		br.close();
		
		byte[]   dataEncoded = Base64.encodeBase64(fluxXML.toString().getBytes());
		
		jso.put("ids", jsaIdsValue);
		jso.put("file_name", fileName);
		jso.put("summary", summary);
		jso.put("content_type", contentType);
		jso.put("data", new String(dataEncoded));
		
		System.out.println(jso.toString(4));
		//System.out.println(fluxXML.toString());
		
		/*
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("https://das01.actia.fr/bugzilla-caddie/rest.cgi/bug/3/attachment?login=yassine.yadine@actia.fr&password=caddie&product=TestProduct");
		StringEntity params =new StringEntity(jso.toString());
		request.addHeader("content-type", "application/json");
		request.addHeader("accept", "application/json");
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);
		
		System.out.println(response.getStatusLine());
		*/
	}

}
