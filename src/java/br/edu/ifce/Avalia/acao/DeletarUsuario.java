/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarcisio
 */
public class DeletarUsuario implements Acao{
    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        banco.conectar();
        String codigoDoSolicitante = request.getParameter("codigo_solitante");
        String tipoSolicitante = request.getParameter("tipo_solitante");
        String codigo = request.getParameter("codigo_usuario");
        
            try {
                PreparedStatement pst = banco.con.prepareStatement("DELETE FROM tbl_usuarios WHERE codigo=?");
                pst.setString(1, codigo);
                pst.execute();
            } catch (SQLException e) {
                System.out.println("Error ao tentar excluir usuário no banco: "+e.getMessage());
            }
        
        banco.desconectar();
        return "retirect:controle?acao=ListaUsuarios&codigo="+codigoDoSolicitante+"&tipo="+tipoSolicitante;        
    }
    
}
