/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.servlet;

import br.edu.ifce.Avalia.acao.Acao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarcisio
 */
@WebServlet(name = "Controle", urlPatterns = {"/controle"})
@MultipartConfig
public class Controle extends HttpServlet {

     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String paramAcao = request.getParameter("acao");
        String nomeDaAcao = null;
        String nomeDaClasse = "br.edu.ifce.Avalia.acao."+paramAcao;
        
        Acao acao;
        try {
            Class classe = Class.forName(nomeDaClasse);
            acao = (Acao) classe.newInstance();
            nomeDaAcao = acao.executa(request, response);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
             throw new ServletException(ex);
        }
        
        String[] tipoDeEndereco = nomeDaAcao.split(":");
        
        if(tipoDeEndereco[0].equals("forward")){
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+tipoDeEndereco[1]);
            rd.forward(request, response);
        }else{
            response.sendRedirect(tipoDeEndereco[1]);
        }    
    }
}
