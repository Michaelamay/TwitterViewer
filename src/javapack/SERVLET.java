/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javapack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.*;
import java.io.FileReader;
import java.util.Iterator;
import static javapack.TwitterInfo.requestBearerToken;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author michaeljonathanamay
 */

public class SERVLET {
    
    public static String jsonresponse;
    
    public SERVLET(String j){
        this.jsonresponse = j;
        
    }
        
    private static String readResponse(HttpsURLConnection connection) {
        
	try {
		StringBuilder str = new StringBuilder();
			
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = "";
                
		while((line = br.readLine()) != null) {
			str.append(line + System.getProperty("line.separator"));
		}
                
                //System.out.println(str);
		return str.toString();
	}
	catch (IOException e) { return new String(); }
    }
    
    public static HttpsURLConnection goodConnection(int i, String q) throws IOException{
        
         HttpsURLConnection connection = null;
         
         String bearerToken = requestBearerToken("https://api.twitter.com/oauth2/token");
         
         try{
		URL url = new URL("https://api.twitter.com/1.1/search/tweets.json?q="+q+"&result_type=mixed&count=25"); 
		connection = (HttpsURLConnection) url.openConnection();           
		connection.setDoOutput(true);
		connection.setDoInput(true); 
		connection.setRequestMethod("GET"); 
		connection.setRequestProperty("Host", "api.twitter.com");
		connection.setRequestProperty("User-Agent", "Your Program Name");
		connection.setRequestProperty("Authorization", "Bearer " + bearerToken);//Using the bearerToken in a get request.
		connection.setUseCaches(false);
             
                return connection;
             
         }
 	catch (MalformedURLException e) {
		throw new IOException("Invalid endpoint URL specified.", e);
	}
	finally {
		if (connection != null) {
			connection.disconnect();
		}
	}        
         
         
    }
        
    // Fetches the first tweet from a given user's timeline
    public static StringBuilder fetchfeedTweet(int i, String q) throws IOException, ParseException, org.json.simple.parser.ParseException {
	
        HttpsURLConnection connection = null;
                
	try {
                
                HttpsURLConnection connected = goodConnection(i,q);
                
                jsonresponse = readResponse(connected);//A FIXED RESPONSE WITH 20 TEXT
                
                JSONObject jsonObject = new JSONObject(jsonresponse);
                
                JSONArray jsonArray = jsonObject.getJSONArray("statuses");
                                              
                StringBuilder tweet = new StringBuilder(jsonArray.getJSONObject(i).getString("text"));
                
                System.out.println(jsonArray.getJSONObject(i).getJSONObject("user").getString("screen_name"));
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(jsonArray.getJSONObject(i));//ITEM
                                                                             
                return tweet;
	}
	catch (MalformedURLException e) {
		throw new IOException("Invalid endpoint URL specified.", e);
	}
	
    }
    
    public static StringBuilder fetchUsername(int i){
        JSONObject jsonObject = new JSONObject(provideJSON());
        
        JSONArray jsonArray = jsonObject.getJSONArray("statuses");
        
        StringBuilder username = new StringBuilder(jsonArray.getJSONObject(i).getJSONObject("user").getString("screen_name"));
        
        //jsonArray.getJSONObject(i).getJSONObject("user").getString("screen_name");
        
        return username;
        
    }
    
    public static String provideJSON(){
      return jsonresponse;
    }
    
    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException{
        
        //System.out.println("Hello world!");
        
        System.out.println(fetchfeedTweet(0,"quotes"));
        //fetchfeedTweet(0,"quotes");
        //System.out.println("method: "+ provideJSON());
        
    }
    
}
