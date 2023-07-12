/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_assembly;

/**
 *
 * @author lucas
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

    public class KeywordStyledDocument extends DefaultStyledDocument{
        private static final long serialVersionUID = 1L;
        private Style _defaultStyle;
        private Style _cwStyle;
        private int inicio = 0;
        private int fim = 0;
        private boolean achou = false;

        public KeywordStyledDocument(Style defaultStyle, Style cwStyle) {
            _defaultStyle =  defaultStyle;
            _cwStyle = cwStyle;
            
        }

         public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
             super.insertString(offset, str, a);
             refreshDocument();
         }

         public void remove (int offs, int len) throws BadLocationException {
             super.remove(offs, len);
             refreshDocument();
         }

         private synchronized void refreshDocument() throws BadLocationException {
             String text = getText(0, getLength());
             final List<HiliteWord> list = processWords(text);

             setCharacterAttributes(0, text.length(), _defaultStyle, true);   
             for(HiliteWord word : list) {
                 int p0 = word._position;
                 StyleConstants.setForeground(_cwStyle, word._color);
                 setCharacterAttributes(p0, word._word.length(), _cwStyle, true);
             }
         } 
         
        

         private List<HiliteWord> processWords(String content) {
             //System.out.println("olhar " + content);
             content += " ";
             List<HiliteWord> hiliteWords = new ArrayList<HiliteWord>();
             int lastWhitespacePosition = 0;
             String word = "";
             char[] data = content.toCharArray();
             
            for(int index=0; index < data.length; index++) {
                char ch = data[index];

                if(ch == '/'){
                    try{
                        if(data[index - 1] == '/'){
                            int coment = index - 1;
                            int fim = this.achaQuebra(index, data);
                            
                            if(fim == -1){
                                fim = data.length;
                            }
                            hiliteWords.add(new HiliteWord(content.substring(coment,fim),coment,Color.LIGHT_GRAY));
                        }else{}
                    }catch(Exception e){
                        System.out.println("erro");}  
                }
                if(!(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')) {
                     lastWhitespacePosition = index;
                     if(word.length() > 0) {
                         if(isReservedWordIN(word)) {
                             hiliteWords.add(0,new HiliteWord(word,(lastWhitespacePosition - word.length()),Color.BLUE));
                         }if(isReservedWordREG(word)) {
                             hiliteWords.add(0,new HiliteWord(word,(lastWhitespacePosition - word.length()),Color.GREEN));
                         }
                         word="";
                     }
                 }
                 else {
                     word += ch;
                 }
             }
            return hiliteWords;
         }
         
         public int achaQuebra(int pos,char[] data){
            int ret = -1;
            for(int index = pos; index < data.length; index++) {
                char ch = data[index];
                if(ch == '\n'){
                    ret = index;
                    break;
                }
            }
            return ret;
        }

         private static final boolean isReservedWordIN(String word) {
             return(word.toUpperCase().trim().equals("ADD") || 
                            word.toUpperCase().trim().equals("ADI") ||
                            word.toUpperCase().trim().equals("SUB") ||
                            word.toUpperCase().trim().equals("AND") ||
                            word.toUpperCase().trim().equals("ANDI") ||
                            word.toUpperCase().trim().equals("OR") ||
                            word.toUpperCase().trim().equals("ORI") ||
                            word.toUpperCase().trim().equals("EOR") ||
                            word.toUpperCase().trim().equals("EORI") ||
                            word.toUpperCase().trim().equals("NOT") ||
                            word.toUpperCase().trim().equals("INC") ||
                            word.toUpperCase().trim().equals("DEC") ||
                            word.toUpperCase().trim().equals("MUL") ||
                            word.toUpperCase().trim().equals("MULI") ||
                            word.toUpperCase().trim().equals("SHL") ||
                            word.toUpperCase().trim().equals("SHR") ||
                            word.toUpperCase().trim().equals("MOV") ||
                            word.toUpperCase().trim().equals("LDI") ||
                            word.toUpperCase().trim().equals("IN") ||
                            word.toUpperCase().trim().equals("OUT") ||
                            word.toUpperCase().trim().equals("JMP") ||
                            word.toUpperCase().trim().equals("RJMP") ||
                            word.toUpperCase().trim().equals("BRL") ||
                            word.toUpperCase().trim().equals("BRLE") ||
                            word.toUpperCase().trim().equals("BRE") ||
                            word.toUpperCase().trim().equals("BRNE") ||
                            word.toUpperCase().trim().equals("BRGE") ||
                            word.toUpperCase().trim().equals("BRG") ||
                            word.toUpperCase().trim().equals("BRZ") ||
                            word.toUpperCase().trim().equals("NOP") ||
                            word.toUpperCase().trim().equals("RESET"));
        }
         
        private static final boolean isReservedWordREG(String word) {
             return(word.toUpperCase().trim().equals("R0") || 
                            word.toUpperCase().trim().equals("R1") ||
                            word.toUpperCase().trim().equals("R2") ||
                            word.toUpperCase().trim().equals("R3") ||
                            word.toUpperCase().trim().equals("R4") ||
                            word.toUpperCase().trim().equals("R5") ||
                            word.toUpperCase().trim().equals("R6") ||
                            word.toUpperCase().trim().equals("R7") ||
                            word.toUpperCase().trim().equals("R8") ||
                            word.toUpperCase().trim().equals("R9") ||
                            word.toUpperCase().trim().equals("XL") ||
                            word.toUpperCase().trim().equals("XH"));
        }
    }