/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Tarcisio Alves
 */
public class PegarUsuario {
    
    public Usuario getUsuario(String codigo){
        Usuario user = null; 
        Banco banco = new Banco();

            try {
                banco.conectar(); 
                PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_usuarios WHERE codigo=?");
                ps.setString(1, codigo);
                banco.rs = ps.executeQuery();

                if(banco.rs.next()) {
                    user = new Usuario();
                    user.setCodigo(banco.rs.getInt("codigo"));
                    user.setNome(banco.rs.getString("nome"));
                    user.setInscricao(banco.rs.getString("inscricao"));
                    user.setTipo((String)banco.rs.getString("tipo"));
                    user.setEmail(banco.rs.getString("email"));	
                    user.setSenha(banco.rs.getString("senha"));
                }
                banco.desconectar(); 

            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }
        return user;
    }
    
   
    
}
