/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Artigo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarcisio Alves
 */
public class Kanban implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        ControleArtigo artigo = new ControleArtigo();
        
        
        int codigo_usuario = Integer.parseInt(request.getParameter("codigo_usuario"));       
        String tipo_usuario = request.getParameter("tipo_usuario");
        String subacao = request.getParameter("subacao");
        
        if (subacao.equals("listar")){
            
            List<Artigo> ListaArtigos = artigo.bucarTodosArtigos(codigo_usuario, tipo_usuario);
            request.setAttribute("ListaArtigos", ListaArtigos);
            return "forward:kanban.jsp";
            
        }else if (subacao.equals("2")){
            String codigo_artigo = request.getParameter("codigo_artigo");
            try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement(" INSERT INTO tbl_nota (artigo, usuario) VALUES (?,?)");
                pst.setString(1, codigo_artigo);
                pst.setInt(2, codigo_usuario);
                pst.execute();
                banco.desconectar();
            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }
            
            try {
            banco.conectar();
            PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_artigo SET fase = ? WHERE codigo = ?");
            pst.setString(1, "3");
            pst.setString(2, codigo_artigo);
            pst.execute();			            

        } catch (SQLException ex) {
            System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
        }
            
            return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario;
            
        } else if(subacao.equals("3")){
            double nota = Double.parseDouble(request.getParameter("nota"));
            int codigo_artigo = Integer.parseInt(request.getParameter("codigo_artigo"));
            try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement("update tbl_nota set nota = ? where artigo = ? end usuario = ?");
                pst.setDouble(1, nota);
                pst.setInt(2, codigo_artigo);
                pst.setInt(3, codigo_usuario);
                pst.execute();
                banco.desconectar();
            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }        
            return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario ;
        }
        return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario ;
    }
    
}
