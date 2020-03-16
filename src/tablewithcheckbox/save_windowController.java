/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javapack.SERVLET.fetchUsername;
import static tablewithcheckbox.DatabaseManager.statprovider;

/**
 * FXML Controller class
 *
 * @author michaeljonathanamay
 */

public class save_windowController implements Initializable {
    
    @FXML
    private TableView<SaveSetterGetter> tableView ;
    
    @FXML
    private TableColumn<SaveSetterGetter,CheckBox> Delete;
    
    @FXML
    private TableColumn<SaveSetterGetter,StringBuilder> Username;
    
    @FXML
    private TableColumn<SaveSetterGetter,StringBuilder> Saved ;
    
    @FXML
    private Button delete;
    
    ObservableList<SaveSetterGetter> savelist = FXCollections.observableArrayList();
    
    CheckBox anything;
    
     
    private Statement stmt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String sql = "SELECT * FROM TWEETS";
        
        stmt = statprovider(); 
        
        try {
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                //System.out.println(rs.getString("tweet"));
                anything = new CheckBox("");
                StringBuilder tweet = new StringBuilder(rs.getString("tweet"));
                StringBuilder username = new StringBuilder(rs.getString("username"));
                savelist.add(new SaveSetterGetter(anything,tweet,username));
            }
        } catch (SQLException ex) {
            Logger.getLogger(save_windowController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        // TODO
        //anything = new CheckBox("");
        //StringBuilder tweet = new StringBuilder("Tweet Saved!");
        
        //savelist.add(new SaveSetterGetter(anything,tweet));
        tableView.setItems(savelist);        
        Delete.setCellValueFactory(new PropertyValueFactory<SaveSetterGetter,CheckBox>("Delete"));
        Saved.setCellValueFactory(new PropertyValueFactory<SaveSetterGetter,StringBuilder>("Saved"));
        Username.setCellValueFactory(new PropertyValueFactory<SaveSetterGetter,StringBuilder>("Username"));
        
    }
    
    @FXML
    void handledeleteButtonAction (ActionEvent event) throws SQLException{
        System.out.println("I WAS CLICKED ON!@!");
        
        int i = 0;
        
        for(SaveSetterGetter currenttab : savelist){
            
            if(currenttab.getDelete().isSelected()){
                System.out.println(i+"");
                                
                //grab selected username
                
                //System.out.println(currenttab.getUsername());
                                                            
                StringBuilder useri = new StringBuilder(currenttab.getUsername());
                
                String sql = "DELETE FROM TWEETS where username = '"+ useri + "'";
                
                stmt.execute(sql);
            }
            i++;
        }
    }
    
}
