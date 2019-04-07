package br.edu.ifce.Avalia.modelo;

/**
 *
 * @author Tarcisio Alves
 */
public class Artigo {
    private int codigo;
    private String titulo;
    private String nomeDoarquivo;
    private byte[] arquivo;
    private int fase;
    private String dataPublic;
    private String dataAvaliacao;
    private int dono;
    private int solicitante;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(int solicitante) {
        this.solicitante = solicitante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeDoarquivo() {
        return nomeDoarquivo;
    }

    public void setNomeDoarquivo(String nomeDoarquivo) {
        this.nomeDoarquivo = nomeDoarquivo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public String getDataPublic() {
        return dataPublic;
    }

    public void setDataPublic(String dataPublic) {
        this.dataPublic = dataPublic;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getDono() {
        return dono;
    }

    public void setDono(int dono) {
        this.dono = dono;
    }
    
}
