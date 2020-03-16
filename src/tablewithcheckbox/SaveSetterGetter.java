/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import javafx.scene.control.CheckBox;

/**
 *
 * @author michaeljonathanamay
 */
public class SaveSetterGetter {
    
    CheckBox Delete;
    StringBuilder Saved;
    
    StringBuilder Username;
    
    public SaveSetterGetter(CheckBox Delete, StringBuilder Saved, StringBuilder Username){
        this.Saved = Saved;
        this.Delete = Delete;
        this.Username = Username;
    }
    
    public CheckBox getDelete(){
        return Delete;
    }
    
    public void setDelete(CheckBox Delete){
        this.Delete = Delete;
    }
    
    public StringBuilder getSaved(){
        return Saved;
    }
    
    public void setSaved(StringBuilder Saved){
        this.Saved = Saved;
    }
    
    public StringBuilder getUsername(){
        return Username;
    }
    
    public void setUsername(StringBuilder Username){
        this.Username = Username;
    }
    
}
