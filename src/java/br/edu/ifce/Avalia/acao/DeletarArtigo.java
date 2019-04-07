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
 * @author Tarcisio Alves
 */
public class DeletarArtigo implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        banco.conectar();         
        int codigo_artigo = Integer.parseInt(request.getParameter("codigo_artigo"));
        String tipo_usuario = request.getParameter("tipo_usuario");
        String codigo_usuario = request.getParameter("codigo_usuario");
        String nomeArquivo = request.getParameter("nomeArquivo");
        
            try {
                PreparedStatement pst = banco.con.prepareStatement("DELETE FROM tbl_artigo WHERE codigo=?");
                pst.setInt(1, codigo_artigo);
                pst.execute();
            } catch (SQLException e) {
                System.out.println("Error ao tentar excluir usuário no banco: "+e.getMessage());
            }
        File arquivo = new File("/home/tarcisio/git/Avalia/web/arquivos/" + File.separator + nomeArquivo);
        arquivo.delete();
        banco.desconectar();
        return "retirect:controle?acao=ListarArtigos&codigo_usuario="+codigo_usuario+"&tipo="+tipo_usuario;        
    }
    
}
