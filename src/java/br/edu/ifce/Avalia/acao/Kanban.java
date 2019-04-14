/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Artigo;
import br.edu.ifce.Avalia.modelo.Usuario;
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
                PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_artigo SET fase = ?, avaliandoPor = ? WHERE codigo = ?");
                pst.setString(1, "3");
                pst.setInt(2, codigo_usuario);
                pst.setString(3, codigo_artigo);
                pst.execute();			            
                banco.desconectar();
            } catch (SQLException ex) {
                System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
            }
            
            return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario;
            
        } else if(subacao.equals("3")){
            String codigo_artigo = request.getParameter("codigo_artigo");
            Artigo at =  new Artigo();  
            
            Usuario user = null;
            
            
            try {
                banco.conectar(); 
                PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_usuarios WHERE codigo=?");
                ps.setString(1, request.getParameter("codigo_usuario"));
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
            

            try {
                banco.conectar(); 
                PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_artigo WHERE codigo=?");
                ps.setString(1, codigo_artigo);
                banco.rs = ps.executeQuery();
                
                if(banco.rs.next()) {
                    
                    at.setCodigo(banco.rs.getInt("codigo"));
                    at.setTitulo(banco.rs.getString("titulo"));
                    at.setNomeDoarquivo(banco.rs.getString("nomeDoarquivo"));
                    at.setArquivo(banco.rs.getBytes("arquivo"));
                    at.setFase(banco.rs.getInt("fase"));
                    at.setDataPublic(banco.rs.getString("dataPuclic"));
                    at.setDataAvaliacao(banco.rs.getString("dataAvaliacao"));
                    at.setDono(banco.rs.getInt("dono"));
                    at.setAvaliandoPor(banco.rs.getInt("avaliandoPor"));
                    at.setSolicitante(codigo_usuario);
                    at.setTipo(user.getTipo());
                }
                banco.desconectar(); 

            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o artigo \n "+ex.getMessage());
            }
            
            request.setAttribute("artigo", at);
            return "forward:nota_artigo.jsp";  
            
        }else if (subacao.equals("nota")){
            String codigo_artigo = request.getParameter("codigo_artigo");
            double nota = Double.parseDouble(request.getParameter("nota"));
            System.out.println(nota);
            System.out.println(nota);
            System.out.println(nota);
            System.out.println(nota);
            System.out.println(nota);
            
            try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_nota SET nota = ? WHERE artigo = ? and usuario = ?");
                pst.setDouble(1, nota);
                pst.setString(2, codigo_artigo);
                pst.setInt(3, codigo_usuario);
                pst.execute();			            

            } catch (SQLException ex) {
                System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
            }
            
             try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_artigo SET fase = ? WHERE codigo = ?");
                pst.setString(1, "4");
                pst.setString(2, codigo_artigo);
                pst.execute();			            

            } catch (SQLException ex) {
                System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
            }
            
            return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario ;
        
        }
        
        return "retirect:controle?acao=Home&codigo_usuario="+codigo_usuario ;
    }
    
}
