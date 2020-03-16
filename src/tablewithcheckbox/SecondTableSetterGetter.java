/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

/**
 *
 * @author michaeljonathanamay
 */
public class SecondTableSetterGetter {
    
    StringBuilder source;
    
    public SecondTableSetterGetter(StringBuilder source){
        this.source = source;
    }
    
    public StringBuilder getSource(){
        return source;
    }
    
    public void setSource(StringBuilder source){
        this.source = source;
    }
    
}
