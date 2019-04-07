package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarcisio Alves
 */

public class ListaUsuarios implements Acao{
    public String executa(HttpServletRequest request, HttpServletResponse response) {
        Banco banco = new Banco();
        List<Usuario> listUser = new ArrayList<>();
        try {
            banco.conectar();
            PreparedStatement pst = banco.con.prepareStatement("select * from tbl_usuarios");
            banco.rs = pst.executeQuery();           

            while (banco.rs.next()) {
                Usuario user = new Usuario();
                user.setCodigo(banco.rs.getInt("codigo"));
                user.setNome(banco.rs.getString("nome"));
                user.setInscricao(banco.rs.getString("inscricao"));
                user.setTipo(banco.rs.getString("tipo"));
                user.setEmail(banco.rs.getString("email"));
                listUser.add(user);							
            }            
        } catch (SQLException e) {
                System.out.println("Error ao buscar lista de usuário \n: "+e.getMessage());
        }  
        banco.desconectar();
        request.setAttribute("usuarios", listUser);
        return "forward:listarUsuarios.jsp";
    }
    
}
