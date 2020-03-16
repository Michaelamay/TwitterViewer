/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import java.awt.Checkbox;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
//import twitter4j.Twitter;
//import twitter4j.TwitterFactory;
//import twitter4j.conf.ConfigurationBuilder;
import java.util.*;
//import twitter4j.Status;
//import twitter4j.TwitterException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javapack.SERVLET;
import static javapack.SERVLET.fetchUsername;
import static javapack.SERVLET.fetchfeedTweet;
import static javapack.SERVLET.jsonresponse;
import static javapack.TwitterInfo.requestBearerToken;
import javax.net.ssl.HttpsURLConnection;
//import static org.apache.tomcat.jni.File.stat;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import static tablewithcheckbox.DatabaseManager.stat;
import static tablewithcheckbox.DatabaseManager.statprovider;

/**
 *
 * @author michaeljonathanamay
 */

public class FXMLDocumentController implements Initializable {
    
    /**FIRST WINDOWN PROPERTIES**/
    @FXML
    private TableColumn<TableSetterGetter, String> summary;
    
    @FXML
    private TableColumn<TableSetterGetter,String> user;
    
    @FXML
    private TableColumn<TableSetterGetter, CheckBox> save;
    
    @FXML
    private TableColumn<TableSetterGetter, CheckBox> pop;
    
    @FXML
    private TableView<TableSetterGetter> tableView;
           
    @FXML
    private TextField searchfield;
        
    ObservableList<TableSetterGetter> list = FXCollections.observableArrayList();
    
    CheckBox sv;
    
    CheckBox popup;
    /**FIRST WINDOWN PROPERTIES**/  

    public static int j;
    
    public FXMLDocumentController() {
       
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        
    }
    
    //SEARCH
    @FXML
    void handlesearchButtonAction(ActionEvent event) throws IOException, ParseException, org.json.simple.parser.ParseException{
        
        tableView.getItems().clear();
        
        //System.out.println("SEARCH BUTTON WAS PRESSED!");
        
        String searchVar = searchfield.getText();
        
        HttpsURLConnection connection = null;
        
        //WHERE IS THIS requestBearerToken coming from? javapack.TwitterInfo.requestBearerToken;
        String bearerToken = requestBearerToken("https://api.twitter.com/oauth2/token");
                        
	try {
                                             
                for(int i =0; i < 20; i++){
                    
                    sv = new CheckBox("");
                    
                    popup = new CheckBox("");
                                       
                    //Where is this method coming from? javapack.JSONObj.fetchfeedTweet;
                    StringBuilder tweet = new StringBuilder(fetchfeedTweet(i,searchVar));
                    
                    StringBuilder useri = new StringBuilder(fetchUsername(i));
                    
                    for(int j=0; j < tweet.length();j++){
                        
                        if(j == 75){
                            tweet.insert(j,"\n");
                        }
                        
                    }
                    
                    list.add(new TableSetterGetter(sv,popup,tweet,useri));
                    
                    
                }
                
                tableView.setItems(list);
                save.setCellValueFactory(new PropertyValueFactory<TableSetterGetter,CheckBox>("save"));
                pop.setCellValueFactory(new PropertyValueFactory<TableSetterGetter,CheckBox>("pop"));
                summary.setCellValueFactory(new PropertyValueFactory<TableSetterGetter,String>("summary"));
                user.setCellValueFactory(new PropertyValueFactory<TableSetterGetter,String>("user"));
                
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
    
    //Pop up button // "SECOND_WINDOW"
    @FXML
    void handleButtonAction(ActionEvent event){
                     
        int i = 0;
        
        for (TableSetterGetter currenttab : list){
                        
            if(currenttab.getPop().isSelected()){
                
                //System.out.println("handleButtonAction\ni: "+ i);
                
                j = i;
                //LOAD THE SOURCE                       
                try{
                    
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Second_window.fxml"));
                     
                     Parent root1 = (Parent) fxmlLoader.load();
                     
                     Stage stage = new Stage();
                                                                                                      
                     //stage.setTitle("Second window");
                     
                     stage.setScene(new Scene(root1));
                                                              
                     stage.show();
                                                             
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Cant load new window");
                }
            }
           i++;
        }
                    
    }
    
    //Save button //NO ADDITONAL WINDOW
    @FXML
    void handlesaveButtonAction(ActionEvent event){
        
        int k = 0;
        
        for (TableSetterGetter currenttab : list){
            
            if(currenttab.getSave().isSelected()){
                                               
                //System.out.println("Save button is selected");
                
                //System.out.println("k: "+ k);
                
                //STORE ITEMS ON H2 DATABASE "SERVER"
                                                                            
                org.json.JSONObject jsonObject = new org.json.JSONObject(jsonresponse);
                
                org.json.JSONArray jsonArray = jsonObject.getJSONArray("statuses");
                
                StringBuilder tweet = new StringBuilder(jsonArray.getJSONObject(k).getString("text"));
                
                StringBuilder username = new StringBuilder(jsonArray.getJSONObject(k).getJSONObject("user").getString("screen_name"));
                
                Statement statupdate = statprovider();
                                               
                try{
                 //String sql = "INSERT INTO TWEETS (TWEET,USERNAME) VALUES ('"+ tweet + "')"; 
                 String sql = "INSERT INTO TWEETS (TWEET,USERNAME) VALUES (' "+ tweet +" '," + "'"+ username + "')";
                 
                 statupdate.execute(sql);
                }
                catch(SQLException ex){
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null,ex);
                }
                
            }
            k++;
        }
        
        
    }
    
    //ARCHIVE BUTTON
    @FXML
    void handleArchivedButtonAction (ActionEvent event) throws IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("save_window.fxml"));
                     
        Parent root1 = (Parent) fxmlLoader.load();
                     
                    
        Stage stage = new Stage();

                     
        stage.setScene(new Scene(root1));
                                                              
                     
        stage.show();
        
        
                                          
    }

    
    
}
