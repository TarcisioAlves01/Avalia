/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.Avalia.acao;

import br.edu.ifce.Avalia.modelo.Artigo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarcisio Alves
 */
public class ControleArtigo {
    Banco banco = new Banco();
    
    public void setArtigo(Artigo artigo){
        
        try {
            banco.conectar();
            PreparedStatement pst = banco.con.prepareStatement("INSERT INTO tbl_artigo (titulo, nomeDoarquivo, arquivo, fase, dataPuclic, dataAvaliacao, dono) VALUES (?,?,?,?,?, ?, ?)");
             pst.setString(1, artigo.getTitulo());
             pst.setString(2, artigo.getNomeDoarquivo());
             pst.setBytes(3, artigo.getArquivo());
             pst.setInt(4, artigo.getFase());
             pst.setString(5, artigo.getDataPublic());
             pst.setString(6, artigo.getDataAvaliacao());
             pst.setInt(7, artigo.getDono());
             pst.execute();
        } catch (SQLException ex) {
            System.out.println("Error ao tentar setar dados no banco: \n"+ex.getMessage());
        }
        
    }
    public void alterar(Artigo artigo){
        try {
                banco.conectar();
                PreparedStatement pst = banco.con.prepareStatement("UPDATE tbl_artigo SET titulo = ?, nomeDoarquivo = ?, arquivo = ? WHERE codigo = ?");
                pst.setString(1, artigo.getTitulo());
                pst.setString(2, artigo.getNomeDoarquivo());
                pst.setBytes(3, artigo.getArquivo()); 
                pst.setInt(4, artigo.getCodigo());
                pst.execute();			            

            } catch (SQLException ex) {
                System.out.println("Error ao tentar atualizaros dados no banco: \n"+ex.getMessage());
            }
    }
    //O metodo converte um arquivo em byte e retorna um tipo byte
    public byte[] fileToByteArray(File arquivo){
    FileInputStream fs = null;
    byte[] byt = null;

            try {
                    fs = new FileInputStream(arquivo);
            byt = new byte[(int)arquivo.length()];
            for(int i = 0;i < arquivo.length();i++){
                try {
                                    byt[i] = (byte) fs.read();
                            } catch (IOException e) {
                                    System.out.println("Erro: "+e.getMessage());
                            }
            }
            } catch (FileNotFoundException e) {
                    System.out.println("Erro: "+e.getMessage());
            }
    return byt;
    }
	
    //Converte um array de byte em um arquivo PDF e o salva no diretorio arquivos, para isso deve passar o nome do arquivo e o array
    public boolean byteArrayToFile(String arquivo, byte[] bytes) {

            if(arquivo != null && bytes != null) {

                    File someFile = new File(System.getProperty("user.dir")+"/web/arquivos/"+arquivo);
                    FileOutputStream fos=null;
                    try {
                            fos = new FileOutputStream(someFile);
                    } catch (FileNotFoundException e) {
                            System.out.println("Erro: "+e.getMessage());
                    }

                    try {
                            fos.write(bytes);
                    } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }

                    try {
                            fos.flush();
                    } catch (IOException e) {
                            // TODO Auto-generated catch block
                            System.out.println("Erro: "+e.getMessage());
                    }

                    try {
                            fos.close();
                            return true;
                    } catch (IOException e) {
                            // TODO Auto-generated catch block
                            System.out.println("Erro: "+e.getMessage());
                    }

            }else {
                    System.out.println("Arquivo ou nome vazios!");			
            }
            return false;
    }

    public boolean arquivoExite(String nome) {
            File origem = new File(System.getProperty("user.home")+"/git/Avalia/web/arquivos/"+nome);
            if(origem.length() != 0) {
                    return true;
            }
            return false;
    }
    
        public List<Artigo> bucarTodosArtigos(int solicitante, String tipo) {
    	
    	try {
            banco.conectar();
            PreparedStatement pst = banco.con.prepareStatement("SELECT * FROM tbl_artigo");
            banco.rs = pst.executeQuery();
            List<Artigo> listArtigo = new ArrayList<>();

            while (banco.rs.next()) {
                Artigo artigo = new Artigo();
                artigo.setCodigo(banco.rs.getInt("codigo"));
                artigo.setTitulo(banco.rs.getString("titulo"));
                artigo.setNomeDoarquivo(banco.rs.getString("nomeDoarquivo"));  
                artigo.setArquivo(banco.rs.getBytes("arquivo"));
                artigo.setFase(banco.rs.getInt("fase"));
                artigo.setDataPublic(banco.rs.getString("dataPuclic"));
                artigo.setDataAvaliacao(banco.rs.getString("dataAvaliacao"));
                artigo.setDono(banco.rs.getInt("dono"));
                artigo.setSolicitante(solicitante);
                artigo.setTipo(tipo);
                listArtigo.add(artigo);							
            }
            return listArtigo;
        } catch (SQLException e) {
                System.out.println("Error ao buscar lista de usuário \n: "+e.getMessage());
        }    	
        return null;
    }

    public Artigo getArtigo(String codigo) {
        Artigo artigo = null; 
        Banco banco = new Banco();

            try {
                banco.conectar(); 
                PreparedStatement ps = banco.con.prepareStatement("SELECT * FROM tbl_artigo WHERE codigo=?");
                ps.setString(1, codigo);
                banco.rs = ps.executeQuery();

                if(banco.rs.next()) {
                    artigo = new Artigo();
                    artigo.setCodigo(banco.rs.getInt("codigo"));
                    artigo.setTitulo(banco.rs.getString("titulo"));
                    artigo.setNomeDoarquivo(banco.rs.getString("nomeDoarquivo"));
                    artigo.setArquivo(banco.rs.getBytes("arquivo"));
                    artigo.setFase(banco.rs.getInt("fase"));
                    artigo.setDataPublic(banco.rs.getString("dataPuclic"));
                    artigo.setDataAvaliacao(banco.rs.getString("dataAvaliacao"));
                    artigo.setDono(banco.rs.getInt("dono"));
                }
                banco.desconectar(); 

            } catch (SQLException ex) {            
                System.out.println("Erro ao buscar o usuário \n "+ex.getMessage());
            }
        
        return artigo;    
    }
    
}
