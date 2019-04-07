/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Artigo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tarcisio
 */
public class ListarArtigos implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        ControleArtigo artigo = new ControleArtigo();
        int solicitante = Integer.parseInt(request.getParameter("codigo_usuario"));
        String tipo = request.getParameter("tipo");
        List<Artigo> ListaArtigos = artigo.bucarTodosArtigos(solicitante, tipo);
        
        request.setAttribute("ListaArtigos", ListaArtigos);
        return "forward:listarArtigos.jsp";  
    }
    
}
