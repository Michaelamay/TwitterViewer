/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import static javapack.SERVLET.jsonresponse;
import static javapack.SERVLET.provideJSON;
import org.json.JSONArray;
import org.json.JSONObject;
import static tablewithcheckbox.FXMLDocumentController.j;

/**
 * FXML Controller class
 *
 * @author michaeljonathanamay
 */

public class Second_windowController implements Initializable {
    
    @FXML
    private ScrollPane scrollPan;
    
    @FXML
    private TableColumn<SecondTableSetterGetter, StringBuilder> source; 
    
    @FXML
    private TableView<SecondTableSetterGetter> tableView;
    
    ObservableList<SecondTableSetterGetter> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO                   
        JSONObject jsonObject = new JSONObject(jsonresponse);//The ACTUAL RESPONSE
        
        JSONArray jsonArray = jsonObject.getJSONArray("statuses");
               
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        //ITEM I SOURCE
        String json = gson.toJson(jsonArray.getJSONObject(j));
        
        StringBuilder sourcecode = new StringBuilder(json);
                     
        list.add(new SecondTableSetterGetter(sourcecode));
        
        tableView.setItems(list);
        
        source.setCellValueFactory(new PropertyValueFactory<SecondTableSetterGetter,StringBuilder>("source"));
                           
        
    }    
    
}
