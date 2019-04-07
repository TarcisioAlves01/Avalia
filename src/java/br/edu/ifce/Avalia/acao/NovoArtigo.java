/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;


import br.edu.ifce.Avalia.modelo.Artigo;
import br.edu.ifce.Avalia.acao.ControleArtigo;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author tarcisio
 */
public class NovoArtigo implements Acao{

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response){
        
        String codigoUsuario = request.getParameter("codigo_usuario");
        ControleArtigo controle = new ControleArtigo();
        Artigo artigo = new Artigo();
        Date data = new Date();
        
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
        

        artigo.setTitulo(request.getParameter("titulo_artigo"));
        artigo.setNomeDoarquivo(lerArquivo.getSubmittedFileName());
        artigo.setArquivo(bt);
        artigo.setFase(1);        
        artigo.setDataPublic((String)java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(data));
        artigo.setDataAvaliacao("indefinido");
        artigo.setDono(Integer.parseInt(codigoUsuario));
            
        controle.setArtigo(artigo);
        
        //Após convertido é deletado o arquivo para não acumular lixo
        //novo.delete();
        System.out.println("Ok, gravado dados no banco! :)");

        return "retirect:controle?acao=SolicitarNovoArtigo&codigo_usuario="+codigoUsuario;
    }
    
}
