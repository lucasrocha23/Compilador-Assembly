/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_assembly;

import java.awt.Color;
import javax.swing.JTextPane;

/**
 *
 * @author lucas
 */
public class threadCarrega extends Thread{
    String result;
    JTextPane resposta;
    
    public threadCarrega(JTextPane resposta, String result){
        this.resposta = resposta;
        this.result   = result;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        try{
            this.resposta.setText(" ");
            Thread.sleep (300);
            this.resposta.setText(result);
            
            this.resposta.setForeground(Color.BLUE);
                if (result.equals("compilado com sucesso") == false){
                    this.resposta.setForeground(Color.RED);
                }
            
        }catch (InterruptedException e){};
    }
}
