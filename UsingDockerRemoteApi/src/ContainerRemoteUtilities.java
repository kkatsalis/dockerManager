import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ContainerRemoteUtilities {
	
	public static boolean checkReturnCode(int retCode){
		
		boolean response=false;
		
		switch(retCode){
	    
			case 201:
		    	System.out.println("No error");
		    	response = true;
		    	break;
			case 204:
				System.out.println("No error");
				response = true;
				break;
			case 304:
		    	System.out.println("Container already started");
		    	break; 	 
		    case 404:
		    	System.out.println("No such container");
		    	break;
		    case 406:
		    	System.out.println("Imposible to attach (Container not running)");
		    	break;
		    case 500:
		    	System.out.println("Server error");
		    	break;   	
		    	
	    }
		
		return response;	
		
	}

	
	public static String createContainer(ContainerCharacteristics cont) throws IOException{

				String containerId = null;
				//transform object to json format
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				String jsonbody = gson.toJson(cont);
				System.out.println("Trying to create a container ...");
				System.out.println(jsonbody);
				
				//execute post with the specified body above 	
				CloseableHttpClient httpClient=null;
				try {
			         httpClient = HttpClients.createDefault();
			         URI uri = new URIBuilder()
			                 .setScheme("http")
			                 .setHost("localhost:4243")
			                 .setPath("/containers/create")
			                 .build();
			         
			        HttpPost httpPost=new HttpPost(uri);  
			        StringEntity params =new StringEntity(jsonbody);
			        httpPost.setEntity(params);
			        httpPost.addHeader("content-type", "application/json");    
			        HttpResponse httpResponse = httpClient.execute(httpPost);
			        
			        System.out.println("----------------------------------------");
			        System.out.println("Status Line :");
				    System.out.println(httpResponse.getStatusLine());
			        System.out.println("----------------------------------------");
				    System.out.println("Headers :");
				    Header[] headers = httpResponse.getAllHeaders();
				      
				    for (int i = 0; i < headers.length; i++) {
				    	System.out.println(headers[i]);
				    }
				    // Checking response from the server;
			        System.out.println("----------------------------------------");

				   
				    if(!checkReturnCode(httpResponse.getStatusLine().getStatusCode())){
				    	containerId = "Could not create container";
				    }
				    else{
				    	
					    System.out.println("Container created sucessfully");
	
					    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					    String j_resp_str = reader.readLine();
					    JSONObject json = new JSONObject(j_resp_str);
				        System.out.println("Container created with ID :"+json.get("Id"));
				        System.out.println("And warnings: "+json.get("Warnings"));
				        
				        containerId= json.get("Id").toString();
				    
				    }
				 }catch (URISyntaxException ex) {
			    	 ex.printStackTrace();
			     } catch(UnsupportedEncodingException ex){
			    	 ex.printStackTrace();
			     } catch (ClientProtocolException ex){
			    	 ex.printStackTrace();
			     } catch(IOException ex){
			    	 ex.printStackTrace();
			     } catch(JSONException ex){
			    	 ex.printStackTrace();
			     }
			    finally{
			    	httpClient.close();
			    }	
				
				return containerId;
	}
	
	public static boolean runContainer(String containerId) throws IOException{
		
		boolean runcont = false;
		
		System.out.println("Now trying to make it running...");
		CloseableHttpClient httpClient=null;
        try{
        	httpClient = HttpClients.createDefault();
        	URI uri = new URIBuilder()
        				  .setScheme("http")
        				  .setHost("localhost:4243")
        				  .setPath("/containers/"+containerId+"/start")
                          .build();
        
        	HttpPost httpPost=new HttpPost(uri);  
        	httpPost.addHeader("content-type", "application/json");
        	HttpResponse httpResponse = httpClient.execute(httpPost);
        	
        	System.out.println("----------------------------------------");
	        System.out.println("Status Line :");
		    System.out.println(httpResponse.getStatusLine());
	        System.out.println("----------------------------------------");
		    System.out.println("Headers :");
		    Header[] headers = httpResponse.getAllHeaders();
		      
		    for (int i = 0; i < headers.length; i++) {
		    	System.out.println(headers[i]);
		    }
		    // Checking response from the server;
	        System.out.println("----------------------------------------");

		   
		    if(!checkReturnCode(httpResponse.getStatusLine().getStatusCode())){
		    	runcont = false;
		    }
		    else{
		    	runcont = true;
		    }
        	
        }catch (URISyntaxException ex) {
	    	 ex.printStackTrace();
	     }
        finally{
        	httpClient.close();
        }

	    return runcont;  
	}
	
	public static void getContainerStats(String containerId) throws IOException{
		
		System.out.println("----------------------------------------");
        System.out.println("Now trying to check stats...");
        CloseableHttpClient httpClient=null;
        System.out.println(containerId);
        try{
        	httpClient = HttpClients.createDefault();
        	HttpHost target = new HttpHost("localhost", 4243, "http");
        	HttpGet getRequest = new HttpGet("/containers/"+containerId+"/stats");
        	CloseableHttpResponse httpResponse = httpClient.execute(target, getRequest);
        	
        	if(checkReturnCode(httpResponse.getStatusLine().getStatusCode())){
		    	System.out.println("An error occured check message above");
		    }
        	else{
        		String output;
        		System.out.println("Cpu stats:");
        		
        		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        	    JSONObject js;
        	    /*
        	    while ((output = reader.readLine()) != null) {
        	    	js = new JSONObject(output);
        	    	System.out.println(js);
                }

        		*/
        	}
        }/*catch(JSONException ex){
        	ex.printStackTrace();
        }*/
        finally{
        	httpClient.close();
        }
        
            

	    
	

	    
		
		
		
		
	}
	
	

}
