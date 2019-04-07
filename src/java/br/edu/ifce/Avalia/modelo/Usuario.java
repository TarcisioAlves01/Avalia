package br.edu.ifce.Avalia.modelo;

/**
 *
 * @author Tarcisio Alves
 */
public class Usuario {
        private int codigo;	
	private String nome;	
	private String inscricao;
	private String tipo;
	private String email;
	private String senha;
        private String senha2;

        public String getSenha2() {
            return senha2;
        }

        public void setSenha2(String senha2) {
            this.senha2 = senha2;
        }
            private String alerta;

        public String getAlerta() {
            return alerta;
        }

        public void setAlerta(String alerta) {
            this.alerta = alerta;
        }
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
    
}
