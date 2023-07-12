/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_assembly;


/**
 *
 * @author gundo
 */
public class Compilador_assembly {
    public static void main(String[] args) {
        Compilador i = new Compilador();
        i.setVisible(true);
        String b = "b \n b \n b \n \n";
        System.err.println(b);
        System.out.println(b.split("\n").length);
        try{
            while(true){
                i.seta();
                Thread.sleep(250);
            }
        } catch(Exception w){
            System.out.println("fim de jogo");
        }
        
    }
    
}
