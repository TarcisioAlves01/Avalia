package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Banco;
import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author Tarcisio Alves
 */
public class AutenticaUsuario implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("nome_login");
        String senha = request.getParameter("email_login"); 
        boolean encontrar = false;
        Banco banco = new Banco();
        
        
        String sql = "SELECT * FROM tbl_usuarios WHERE email=? AND senha=?";
            PreparedStatement ps;
            try {
                banco.conectar();
                ps = banco.con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, senha);
                banco.rs = ps.executeQuery();
                
                if (banco.rs.next()){
                    encontrar = true;                                       
                }else{                   
                   encontrar = false;
                }
                
            } catch (SQLException ex) {            
                    System.out.println("Error: "+ex.getMessage());
            }
            banco.desconectar();
        Usuario user = null;
        if(encontrar){
            user = this.getUsuario(email);            
            request.setAttribute("user", user);
            return "forward:index.jsp";  
        }
        request.setAttribute("user", user);
        
        return "forward:../..";
    }
    
    public Usuario getUsuario(String email){
        Usuario user = null; 
        Banco banco = new Banco();

            try {
                banco.conectar(); 
                PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_usuarios WHERE email=?");
                ps.setString(1, email);
                banco.rs = ps.executeQuery();

                if(banco.rs.next()) {
                    user = new Usuario();
                    user.setCodigo(banco.rs.getInt("codigo"));
                    user.setNome(banco.rs.getString("nome"));
                    user.setInscricao(banco.rs.getString("inscricao"));
                    user.setTipo((String)banco.rs.getString("tipo"));
                    user.setEmail(banco.rs.getString("email"));				
                }
                banco.desconectar(); 

            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }
        return user;
    }
    
}
