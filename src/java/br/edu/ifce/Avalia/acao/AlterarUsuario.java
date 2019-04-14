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
public class AlterarUsuario implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        String solicitante = request.getParameter("codigo_solitante");
        String tipoDeSolicitante = request.getParameter("tipo_solitante");
        String usuario = request.getParameter("codigo_usuario");
        String subacao = request.getParameter("subacao");
        PegarUsuario pegar = new PegarUsuario();
        
        if (subacao.equals("solicitar")){
           Usuario user =  pegar.getUsuario(usuario);
           user.setSolicitante(solicitante);
           user.setTipoSolicitante(tipoDeSolicitante);
           
           request.setAttribute("user", user);
           return "forward:alterarUsuario.jsp";
        }
        
        Usuario user2 = new Usuario();
        user2.setCodigo(Integer.parseInt(usuario));
        user2.setNome(request.getParameter("nome"));
        user2.setInscricao(request.getParameter("inscricao"));
        user2.setTipo(request.getParameter("tipo"));
        user2.setEmail(request.getParameter("email"));
        user2.setSolicitante(solicitante);
        user2.setTipoSolicitante(tipoDeSolicitante);
        user2.setSenha(request.getParameter("senha1"));
        user2.setSenha2(request.getParameter("senha2"));
        
        if(user2.getInscricao().equals("") || user2.getNome().equals("") || user2.getEmail().equals("") || user2.getSenha().equals("") ||user2.getSenha2().equals("")){            user2.setAlerta("Confira se os campos estão preenchidos corretamente");
            user2.setAlerta("Certifique-se que os comapos estão preenchidos corretamente!");
            request.setAttribute("user", user2);            
            return "forward:alterarUsuario.jsp";
        }else if(!user2.getSenha().equals(user2.getSenha2())){
            user2.setAlerta("As senhas não estão iguais, certifique-se!");
            request.setAttribute("user", user2);            
            return "forward:alterarUsuario.jsp";
        }
        
        Banco banco = new Banco();
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
        return "retirect:controle?acao=Home&codigo_usuario="+solicitante;
    }
    
}
