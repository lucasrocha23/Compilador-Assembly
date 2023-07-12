package compilador_assembly;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public final class analise {
    Map<String, String> opcodes            = new HashMap<>();
    Map<String, Integer> operandos_opcode  = new HashMap<>();
    Map<String, String> registradores      = new HashMap<>();
    Map<String, Integer> constantes_opcode = new HashMap<>();
    
    String opcode;
    String destino;
    String operando_A = "";
    String operando_B = "";


    public analise(){
        this.config_opCodes();
        this.config_operandos_opcode();
        this.config_registradores();
    }
    void config_opCodes (){
        opcodes.put("ADD",    "00000");
        opcodes.put("ADI",    "00001");
        opcodes.put("SUB",    "00010");
        opcodes.put("SUBI",   "00011");
        opcodes.put("SUBI1",  "00100");
        opcodes.put("AND",    "00101");
        opcodes.put("ANDI",   "00110");
        opcodes.put("OR",     "00111");
        opcodes.put("ORI",    "01000");
        opcodes.put("EOR",    "01001");
        opcodes.put("EORI",   "01010");
        opcodes.put("NOT",    "01011");
        opcodes.put("INC",    "01100");
        opcodes.put("DEC",    "01101");
        opcodes.put("MUL",    "01110");
        opcodes.put("MULI",   "01111");
        opcodes.put("SHL",    "10000");
        opcodes.put("SHR",    "10001");
        opcodes.put("MOV",    "10010");
        opcodes.put("LDI",    "10011");
        opcodes.put("INPUT",  "10100");
        opcodes.put("OUTPUT", "10101");
        opcodes.put("JMP",    "10110");
        opcodes.put("BRL",    "10111");
        opcodes.put("BRLE",   "11000");
        opcodes.put("BRE",    "11001");
        opcodes.put("BRNE",   "11010");
        opcodes.put("BRGE",   "11011");
        opcodes.put("BRG",    "11100");
        opcodes.put("BRZ",    "11101");
        opcodes.put("NOP",    "11110");
        opcodes.put("RESET",  "11111");
    }

    void config_operandos_opcode(){
        operandos_opcode.put("ADD",    3);
        operandos_opcode.put("ADI",    2);
        operandos_opcode.put("SUB",    3);
        operandos_opcode.put("SUBI",   2);
        operandos_opcode.put("SUBI1",  2);
        operandos_opcode.put("AND",    3);
        operandos_opcode.put("ANDI",   2);
        operandos_opcode.put("OR",     3);
        operandos_opcode.put("ORI",    2);
        operandos_opcode.put("EOR",    3);
        operandos_opcode.put("EORI",   2);
        operandos_opcode.put("NOT",    2);
        operandos_opcode.put("INC",    2);
        operandos_opcode.put("DEC",    2);
        operandos_opcode.put("MUL",    2);
        operandos_opcode.put("MULI",   2);
        operandos_opcode.put("SHL",    2);
        operandos_opcode.put("SHR",    2);
        operandos_opcode.put("MOV",    2);
        operandos_opcode.put("LDI",    2);
        operandos_opcode.put("INPUT",  0);
        operandos_opcode.put("OUTPUT", 1);
        operandos_opcode.put("JMP",    1);
        operandos_opcode.put("BRL",    2);
        operandos_opcode.put("BRLE",   2);
        operandos_opcode.put("BRE",    2);
        operandos_opcode.put("BRNE",   2);
        operandos_opcode.put("BRGE",   2);
        operandos_opcode.put("BRG",    2);
        operandos_opcode.put("BRZ",    1);
        operandos_opcode.put("NOP",    0);
        operandos_opcode.put("RESET",  0);
    }

    void config_registradores(){
        registradores.put("R0", "0000");
        registradores.put("R1", "0001");
        registradores.put("R2", "0010");
        registradores.put("R3", "0011");
        registradores.put("R4", "0100");
        registradores.put("R5", "0101");
        registradores.put("R6", "0110");
        registradores.put("R7", "0111");
        registradores.put("R8", "1000");
        registradores.put("R9", "1001");
        registradores.put("PIN", "1010");
        registradores.put("POUT", "1011");
        registradores.put("RC_TIMER", "1100");
        registradores.put("TIMER", "1101");
        registradores.put("XL", "1110");
        registradores.put("XH", "1111");
    }

    void config_constantes_opcode(){
        constantes_opcode.put("ADI", 1);
        constantes_opcode.put("SUBI", 1);
        constantes_opcode.put("SUBI1", 0);
        constantes_opcode.put("ANDI", 1);
        constantes_opcode.put("ORI", 1);
        constantes_opcode.put("EORI", 1);
        constantes_opcode.put("MULI", 1);
        constantes_opcode.put("LDI", 1);
        constantes_opcode.put("JMP", 0);
    }
    
    
    
    public ArrayList<String> trata_entrada(String entrada){
        char[] caracs = (entrada += " ").toCharArray();
        char[] quebra = " ,//   ".toCharArray();

        ArrayList<String> linha = new ArrayList<>();
        String correto = "";

        for (int i = 0; i < caracs.length; i++){

            if(caracs[i] == quebra[2] && caracs[i+1] == quebra[3]){
                break;
            }

            if (caracs[i] == quebra[0] || caracs[i] == quebra[1]){
                if (correto.length() > 0){
                    linha.add(correto);
                }
                correto = "";
            }            
            else{
                String letra = Character.toString(caracs[i]);
                correto += letra;
            }
        }

        if (linha.isEmpty()){
            return null;
        }

        if (linha.get(0).equals("SUBI")){
            if(linha.get(1).contains("#")){
                linha.remove("SUBI");
                linha.add(0, "SUBI1");
            }
        }

        return linha;
       
    }
    
    
    
    public int inst_n_encontrada (ArrayList<String> linha){
        String code = this.opcodes.get(linha.get(0));
        
        if (code == null){
            return 1;
        }
        return 0;
    }
    
    
    
    public int args_demais(ArrayList<String> linha){
        int qnt_operandos = this.operandos_opcode.get(linha.get(0));
        
        if (linha.size() > qnt_operandos + 1){
            return 1;
        }
        return 0;
    }
    
    
    
    public int args_faltando (ArrayList<String> linha){
        int qnt_operandos = this.operandos_opcode.get(linha.get(0));
        
        if (linha.size() < qnt_operandos + 1){
            return 1;
        }
        return 0;
    }
    
    
    
    public String complemento_0 (String bin){
        String fim = "";
        for (int i = 0; i < (8 - bin.length()); i++){
            fim += 0;
        }
        return fim + bin;
    }
    
    
    
    public int argumentos_errados (ArrayList<String> linha){
        String inst = linha.get(0);
        
        //instruções com destino + operando_A
        if(inst.equals("MOV") || inst.equals("SHR") || inst.equals("SHL") || 
           inst.equals("DEC") || inst.equals("INC") || inst.equals("NOT")){
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = this.registradores.get(linha.get(1));
            operando_A = this.registradores.get(linha.get(2));
            operando_B = "0000";
            
            if (destino == null || operando_A == null){
                return 1;
            }
        }
         
        //instruções com destino + operando_A + operando_B
        if (inst.equals("ADD") || inst.equals("SUB") || inst.equals("AND") ||
            inst.equals("OR")  || inst.equals("EOR")){
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = this.registradores.get(linha.get(1));
            operando_A = this.registradores.get(linha.get(2));
            operando_B = this.registradores.get(linha.get(3));
            
            if (destino == null || operando_A == null || operando_B == null){
                return 1;
            }
            
        }
        
        //instruções com operando_A + operando_B
        if (inst.equals("MUL") || inst.equals("BRL")  || inst.equals("BRLE") ||
            inst.equals("BRE") || inst.equals("BRNE") || inst.equals("BRGE") || inst.equals("BRG")){
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = this.registradores.get(linha.get(1));
            operando_A = this.registradores.get(linha.get(2));
            operando_B = "0000";
            
            if(operando_A == null || operando_B == null){
                return 1;
            }
            
        }
            
        //instruções com opcode    
        if (inst.equals("INPUT") || inst.equals("NOP") || inst.equals("RESET")){
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = "0000";
            operando_A = "0000";
            operando_B = "0000";
            
        }
        
        //instruções com operando_A
        if (inst.equals("OUTPUT") || inst.equals("BRZ")){
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = this.registradores.get(linha.get(1));
            operando_A = "0000";
            operando_B = "0000";
            
            if(destino == null){
                return 1;
            }
        }
        
        //instruções com destino + constante
        if(inst.equals("ADI")  || inst.equals("SUBI") || inst.equals("ANDI") || 
           inst.equals("ORI")  || inst.equals("EORI")  || inst.equals("LDI") || inst.equals("MULI")){
            
            if (linha.get(2).contains("#") == false || this.registradores.get(linha.get(1)) == null){
                return 1;
            }
            
            opcode     = this.opcodes.get(linha.get(0));
            char[] ch = (linha.get(2)).toCharArray();
            String n = "";
            for (int i = 1; i < ch.length; i++){
                n += Character.toString(ch[i]);
            }
            destino = this.registradores.get(linha.get(1));
            operando_A = this.complemento_0(Integer.toBinaryString(Integer.valueOf(n)));
        }
        
        //instrucões com constante - destino
        if(inst.equals("SUBI1")){
            
            if (linha.get(1).contains("#") == false || this.registradores.get(linha.get(2)) == null){
                return 1;
            }
            
            opcode    = this.opcodes.get(linha.get(0));
            char[] ch = (linha.get(1)).toCharArray();
            String n = "";
            for (int i = 1; i < ch.length; i++){
                n += Character.toString(ch[i]);
            }
            destino = this.complemento_0(Integer.toBinaryString(Integer.valueOf(n)));
            operando_B = this.registradores.get(linha.get(2));
        }
        
        //instruções com constante
        if (inst.equals("JMP")){
            if(!linha.get(1).contains("#") || linha.get(1).length() < 2){
                return 1;
            }

            char[] ch   = (linha.get(1)).toCharArray();
            String n = "";
            for (int i = 1; i < ch.length; i++){
                n += Character.toString(ch[i]);
            }
            
            opcode     = this.opcodes.get(linha.get(0));
            destino    = this.complemento_0(Integer.toBinaryString(Integer.valueOf(n)));
            operando_B = "0000";
        }
        
        return 0;
     
    }

    
    
    
    public String linha_para_bin(String entrada){
        operando_A = "";
        operando_B = "";
        
        entrada = entrada.toUpperCase();
        
        ArrayList linha = this.trata_entrada(entrada);
        
        if (linha == null){
           
            return null;
        }
        
        if (this.inst_n_encontrada(linha) == 1){
            return "instrução não encontrada";
        }
        
        if (this.args_demais(linha) == 1){
            return "quantidade de parametros a mais";
        }
        
        if (this.args_faltando(linha) == 1){
            return "quantidade de parametros insuficiente";
        }
        
        if (this.argumentos_errados(linha) == 1){
            return "parametros incorretos utilizados";
        }
        
        return opcode + destino + operando_A + operando_B;
    }
}


