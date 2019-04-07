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
public class DisponibilizarParaAvaliacao implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        banco.conectar();         
        int codigo_artigo = Integer.parseInt(request.getParameter("codigo_artigo"));
        String tipo_usuario = request.getParameter("tipo_usuario");
        String codigo_usuario = request.getParameter("codigo_usuario");
        String nomeArquivo = request.getParameter("nomeArquivo");
        
            try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_artigo SET fase =? WHERE codigo = ?");
                pst.setString(1, "2");
                pst.setInt(2, codigo_artigo);
                pst.execute();			            

            } catch (SQLException ex) {
                System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
            }
        File arquivo = new File("/home/tarcisio/git/Avalia/web/arquivos/" + File.separator + nomeArquivo);
        arquivo.delete();
        banco.desconectar();
        return "retirect:controle?acao=ListarArtigos&codigo_usuario="+codigo_usuario+"&tipo="+tipo_usuario;        
    }
    
}
