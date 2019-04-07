/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarcisio
 */
public class SolicitarNovoArtigo implements Acao{
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        Usuario user = null; 
        String codigo = request.getParameter("codigo_usuario");
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
                }
                banco.desconectar(); 

            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }
        request.setAttribute("user", user);
        return "forward:form_artigo.jsp"; 
    }
}
