package compilador_assembly;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class compilar {
    private final String entrada;
    private final String saida;
    private final String nome;
    private final String codigo_txt;
    private String imprime = new String();
    
    public compilar(String entrada, String saida, String nome, String codigo_txt){
        this.entrada    = entrada;
        this.saida      = saida;
        this.nome       = nome;
        this.codigo_txt = codigo_txt; 
    }
    
    public int str_int(String bin){
        char[] binario = bin.toCharArray();
        char comparar = '1';
        
        int posi = 0;
        int valor_final = 0;
        for(int i = (bin.length()-1); i >= 0; i--){
            if(binario[i] == comparar){
                valor_final += Math.pow(2, posi);
            }
            posi ++;
        }
        return valor_final;
    }
    
    public String execultar(){
        analise t = new analise(); 
        
        
        try{
            InputStream os = new FileInputStream(new File(entrada + codigo_txt));
            InputStreamReader ow = new InputStreamReader(os, "UTF-8");
            BufferedReader bw = new BufferedReader(ow);
 
            DataOutputStream out1 = new DataOutputStream(new FileOutputStream(saida + File.separator + nome + "1.bin"));
         
            DataOutputStream out2 = new DataOutputStream(new FileOutputStream(saida + File.separator + nome + "2.bin"));
            
            DataOutputStream out3 = new DataOutputStream(new FileOutputStream(saida + File.separator + nome + "3.bin"));

            String linha;
            
            int cont = 0;
            while((linha = bw.readLine()) != null){
                String comp = t.linha_para_bin(linha);
             
                
                if (comp != null){
                    
                    if (comp.length() == 17){
                        char[] compilado = comp.toCharArray(); 
                        
                        String comp1 = Character.toString(compilado[0]);
                        String comp2 = "";
                        String comp3 = "";
                        
                        for(int i = 1; i < 17; i++){
                            if (i <= 8){
                                comp2 += Character.toString(compilado[i]);
                            }
                            else{
                                comp3 += Character.toString(compilado[i]);
                            }
                        }
                        
                        compilar c = new compilar(entrada,saida,nome,codigo_txt);
                        
                        int c1 = c.str_int(comp1);
                        int c2 = c.str_int(comp2);
                        int c3 = c.str_int(comp3);
                        
                        out1.writeByte(c1);
                        out1.flush();
                        
                        out2.writeByte(c2);
                        out2.flush();
                        
                        out3.writeByte(c3);
                        out3.flush();
                        
                    }
                    else{
                        imprime = "erro de compilação" + "\nlinha " + cont + "\n" + comp;
                        

                        out1.close();
                        out2.close();
                        out3.close();
                        
                        File arq1 = new File(saida + File.separator + nome + "1.bin");
                        File arq2 = new File(saida + File.separator + nome + "2.bin");
                        File arq3 = new File(saida + File.separator + nome + "3.bin");
                        arq1.delete();
                        arq2.delete();
                        arq3.delete();
                        
                        os.close();
                        ow.close();
                        bw.close();
                        return (imprime);
                        
                    }
                }
                cont++;
            }
            if (linha == null){
                imprime = "compilado com sucesso";
                os.close();
                ow.close();
                bw.close();
                return imprime;
            }
        
        }catch (Exception e){
            return ("erro desconhecido\n" + e);
        }
        
        return (imprime);
        
    }
}
