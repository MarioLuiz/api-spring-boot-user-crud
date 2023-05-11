package desafio.crud.usercrud.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = -8229762168625038467L;
	
	@NotEmpty(message = "Preenchimento Obrigatório do nome")
	private  String nome;
	
	@NotEmpty(message = "Preenchimento Obrigatório de e-mail")
	@Email(message = "E-mail valido obrigatório")
	private String email;
	
	// padrao dd/MM/yyyy
	@NotEmpty(message = "Preenchimento Obrigatório da dataNascimento")
	private String dataNascimento;
	
	@NotEmpty(message = "Preenchimento Obrigatório do endereco")
	private String endereco;
	
	@NotNull(message = "Preenchimento Obrigatório das habilidades")
	private List<String> habilidades;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}
}
