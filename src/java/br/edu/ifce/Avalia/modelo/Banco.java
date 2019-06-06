package br.edu.ifce.Avalia.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tarcisio Alves
 */
public class Banco {
    public Statement stm;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/Avalia?useSSL=false";//?useSSL=false
    private String usuario = "root";
    private String senha = "";
    public Connection con;
    
    public void conectar(){     
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro no Driver! \n"+ex.getMessage());
            }
            con=DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar se conectar ao banco de dados! \n"+ex.getMessage());
        }
    }
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar deconectar ao banco de dados! \n"+ex.getMessage());
        }
    }    
    
}
