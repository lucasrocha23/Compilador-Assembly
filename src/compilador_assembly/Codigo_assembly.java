/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_assembly;
import java.io.Serializable;

/**
 *
 * @author gundo
 */
public class Codigo_assembly implements Serializable{

    private static final long serialVersionUID = 1L;
    private String nome_projeto = new String();
    private String codigo = new String();
    private String autor = new String();
    private String Senha = new String();
    
    public String getNome_projeto() {
        return nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    
    public String acesso(String senha){
        if(this.Senha.equals(senha))
            return this.codigo + "Separator" + this.autor;
        return "Senha Invalida!";
    } 
}
