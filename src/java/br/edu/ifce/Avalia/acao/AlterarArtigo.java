/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;


import br.edu.ifce.Avalia.modelo.Artigo;
import br.edu.ifce.Avalia.modelo.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author tarcisio
 */
public class AlterarArtigo implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Artigo artigo = new Artigo();
        ControleArtigo controle = new ControleArtigo();
        String codigo = request.getParameter("codigo_artigo");
        String titulo = request.getParameter("titulo");
        String tipo_usuario = request.getParameter("tipo_usuario");
        String codigo_usuario = request.getParameter("codigo_usuario");
        
        if(request.getParameter("subacao").equals("update")){
                
                String codigoUsuario = request.getParameter("codigo_usuario");               

                //Pasta onde serão gravados os arquivos 
                String pastaDestino = System.getProperty("user.home")+"/git/Avalia/web/WEB-INF/arquivos/"; 


                File pasta = new File(pastaDestino);

                //Verifica se a pasta existe, Caso nãp exista cria
                if (!pasta.exists()){
                   pasta.mkdir();
                   System.out.println("Não havia a pasta de destino, assim se fez necessário sua criação \n"+pasta.getAbsolutePath());
                }        

                Part lerArquivo = null;
                try {
                    lerArquivo = request.getPart("arquivo_artigo");
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(NovoArtigo.class.getName()).log(Level.SEVERE, null, ex);
                }

                String nomeArquivo = lerArquivo.getSubmittedFileName(); 

                try {
                    //grava o arquivo no disco
                    lerArquivo.write(pastaDestino + File.separator + nomeArquivo);
                } catch (IOException ex) {
                    Logger.getLogger(NovoArtigo.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Até o momento foi feito o Upload do arquivo, daqui pra frente será convertido e gravado no bando, onde em seguida será excluido

                File novo = new File(pastaDestino + File.separator + nomeArquivo);
                byte[] bt = controle.fileToByteArray(novo);

                artigo.setCodigo(Integer.parseInt(codigo));
                artigo.setTitulo(titulo);
                artigo.setNomeDoarquivo(nomeArquivo);
                artigo.setArquivo(bt); 
                artigo.setSolicitante(Integer.parseInt(request.getParameter("codigo_usuario")));
                artigo.setTipo(request.getParameter("tipo_usuario"));                
                controle.alterar(artigo);                
                
                PegarUsuario pegar = new PegarUsuario();
                Usuario user =  user = pegar.getUsuario(request.getParameter("codigo_usuario"));
                
                //request.setAttribute("user", user);
                return "retirect:controle?acao=AutenticaUsuario&nome_login="+user.getEmail()+"&email_login="+user.getSenha();
                
        }else{
            artigo = controle.getArtigo(codigo);
            artigo.setSolicitante(Integer.parseInt(codigo_usuario));
            artigo.setTipo(tipo_usuario);
            
        }
        request.setAttribute("artigo", artigo);
        return "forward:alterarArtigo.jsp";        
    }
    
}
