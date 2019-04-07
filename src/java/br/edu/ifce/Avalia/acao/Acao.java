package br.edu.ifce.Avalia.acao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarcisio Alves
 */
public interface Acao {
    public String executa(HttpServletRequest request, HttpServletResponse response);
    
}
