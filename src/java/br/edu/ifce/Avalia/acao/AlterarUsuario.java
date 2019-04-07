/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarcisio
 */
public class AlterarUsuario implements Acao{
    
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        banco.conectar();        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
            try {
                PreparedStatement pst = banco.con.prepareStatement("DELETE FROM tbl_usuarios WHERE codigo=?");
                pst.setInt(1, codigo);
                pst.execute();
            } catch (SQLException e) {
                System.out.println("Error ao tentar excluir usuário no banco: "+e.getMessage());
            }

        banco.desconectar();
        return "retirect:controle?acao=ListaUsuarios";
    }
    
}
