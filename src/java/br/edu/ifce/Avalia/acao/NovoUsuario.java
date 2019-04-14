/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Banco;
import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarcisio Alves
 */
public class NovoUsuario implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Usuario user = new Usuario();   
        Banco banco = new Banco();
        String subacao = request.getParameter("subacao");
        
        if (subacao.equals("novo")){
            user.setInscricao(request.getParameter("inscricao"));
            user.setNome(request.getParameter("nome"));
            user.setEmail(request.getParameter("email"));
            user.setTipo("Aluno");
            user.setSenha(request.getParameter("senha1"));
            user.setSenha2(request.getParameter("senha2"));
            
            if(user.getInscricao().equals("") || 
               user.getNome().equals("") ||
               user.getEmail().equals("") ||
               user.getSenha().equals("") ||
               user.getSenha2().equals("")
            ){
                user.setAlerta("Confira se os campos estão preenchidos corretamente");
                request.setAttribute("user", user);
                return "forward:form_usuario.jsp";
            }else if(!user.getSenha().equals(user.getSenha2())){
                user.setAlerta("As senhas não estão iguais, certifique-se!");
                request.setAttribute("user", user);
                return "forward:form_usuario.jsp";
            }else{
                try {
                    banco.conectar();
                    PreparedStatement pst = banco.con.prepareStatement("INSERT INTO tbl_usuarios (nome, inscricao, tipo, email, senha) VALUES (?,?,?,?,?)");
                    pst.setString(1, user.getNome());
                    pst.setString(2, user.getInscricao());
                    pst.setString(3, user.getTipo());
                    pst.setString(4, user.getEmail());
                    pst.setString(5, user.getSenha());
                    pst.execute();
                    banco.desconectar();
                } catch (SQLException ex) {            
                    System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
                }
                 return "retirect:index.jsp";
            }           
            
        }else{
            user.setInscricao("");
            user.setNome("");
            user.setEmail("");
            user.setSenha("");
            user.setSenha2("");
            request.setAttribute("user", user);
            return "forward:form_usuario.jsp";
        }
        
    }
    
}
