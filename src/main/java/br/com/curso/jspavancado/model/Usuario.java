package br.com.curso.jspavancado.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String login;
	private String senha;
	@Column(columnDefinition = "text")
	private String imagemBase64;
	private String imagemMimeType;

	public Usuario() {
		super();
	}

	public Usuario(Long id) {
		super();
		this.id = id;
	}

	public Usuario(Long id, String login, String senha, String imagemMimeType) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.imagemMimeType = imagemMimeType;
	}

	public Usuario login(String login) {
		this.login = login;
		return this;
	}

	public Usuario senha(String senha) {
		this.senha = senha;
		return this;
	}

	public Usuario fotoPerfil(String fileUpload) {
		this.imagemBase64 = fileUpload.split(",")[1];
		this.imagemMimeType = fileUpload.split(";")[0].split(":")[1];
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public String getImagemMimeType() {
		return imagemMimeType;
	}

	public void setImagemMimeType(String imagemMimeType) {
		this.imagemMimeType = imagemMimeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ \"" + id + "\", \"" + login + "\", \"" + senha + "\", \"" + imagemMimeType + "\"]";
	}

}
