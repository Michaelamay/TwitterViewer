/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import java.awt.Checkbox;
import javafx.scene.control.CheckBox;

/**
 *
 * @author michaeljonathanamay
 */

public class TableSetterGetter {
    
    CheckBox save;
    CheckBox pop;
    StringBuilder summary;
    
    StringBuilder user;
    
    public TableSetterGetter(CheckBox save, CheckBox pop, StringBuilder summary,StringBuilder user) {
        this.save = save;
        this.pop = pop;
        this.summary = summary;
        this.user = user;
    }
    
    public CheckBox getSave() {
        return save;
    }
    
    public void setSave(CheckBox save) {
        this.save = save;
    }
    
    public CheckBox getPop() {
        return pop;
    }
    
    public void setPop(CheckBox pop) {
        this.pop = pop;
    }

    public StringBuilder getSummary() {
        return summary;
    }

    public void setSummary(StringBuilder summary) {
        this.summary = summary;
    }

    public StringBuilder getUser(){
        return user;
    }
    
    public void setUser(StringBuilder user){
        this.user = user;
    }
    
}
