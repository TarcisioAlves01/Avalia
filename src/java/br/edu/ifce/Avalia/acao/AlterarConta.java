/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarcisio Alves
 */
public class AlterarConta implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
    
        String codigo = request.getParameter("codigo_usuario");
        String subacao = request.getParameter("subacao");
        String tipoDeUsuario = request.getParameter("tipoDeusuario");
        String subsubacao = request.getParameter("subsubacao");
        Usuario user = new Usuario();
        Banco banco = new Banco();
        
        try {
            banco.conectar(); 
            PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_usuarios WHERE codigo =?");
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
        
        if (subacao.equals("solicitar")){
            request.setAttribute("user", user);
            
            if(tipoDeUsuario.equals("Aluno") || tipoDeUsuario.equals("Avaliador")){                
                return "forward:form_AuterausuarioComum.jsp";
            }else{
                return "forward:form_AuterausuarioModerador.jsp";
            }
        }
        
       
            
        Usuario user2 = new Usuario();
        user2.setCodigo(Integer.parseInt(codigo));
        user2.setNome(request.getParameter("nome"));
        user2.setInscricao(request.getParameter("inscricao"));
        user2.setTipo(request.getParameter("tipo"));
        user2.setEmail(request.getParameter("email"));
        user2.setSenha(request.getParameter("senha1"));
        user2.setSenha2(request.getParameter("senha2"));

        if(user2.getInscricao().equals("") || user2.getNome().equals("") || user2.getEmail().equals("") || user2.getSenha().equals("") ||user2.getSenha2().equals("")){
            user2.setAlerta("Confira se os campos estão preenchidos corretamente");
            request.setAttribute("user", user2);
            if (subsubacao.equals("moderador")){
                return "forward:form_AuterausuarioModerador.jsp";
            }
            return "forward:form_AuterausuarioComum.jsp";
        }else if(!user2.getSenha().equals(user2.getSenha2())){
            user2.setAlerta("As senhas não estão iguais, certifique-se!");
            request.setAttribute("user", user2);
            if (subsubacao.equals("moderador")){
                return "forward:form_AuterausuarioModerador.jsp";
            }
            return "forward:form_AuterausuarioComum.jsp";
        }else{

            if(subsubacao.equals("comum")){
                try {
                    banco.conectar();
                    PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_usuarios SET nome = ?, inscricao = ?, email = ?, senha = ? WHERE codigo = ?");
                    pst.setString(1, user2.getNome());
                    pst.setString(2, user2.getInscricao());
                    pst.setString(3, user2.getEmail());
                    pst.setString(4, user2.getSenha());
                    pst.setInt(5, user.getCodigo());
                    pst.execute();
                    banco.desconectar();

                } catch (SQLException ex) {
                    System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
                }

            } else if (subsubacao.equals("moderador")){

                try {
                    banco.conectar();
                    PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_usuarios SET nome = ?, inscricao = ?, tipo = ?, email = ?, senha = ? WHERE codigo = ?");
                    pst.setString(1, user2.getNome());
                    pst.setString(2, user2.getInscricao());
                    pst.setString(3, user2.getTipo());
                    pst.setString(4, user2.getEmail());
                    pst.setString(5, user2.getSenha());
                    pst.setInt(6, user2.getCodigo());
                    pst.execute();			            
                    banco.desconectar();
                } catch (SQLException ex) {
                    System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
                }


            }               
            return "retirect:controle?acao=AutenticaUsuario&nome_login="+user2.getEmail()+"&email_login="+user2.getSenha();
        }
                  
       
    }
    
}
