package com.hrgonzaga.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.hrgonzaga.cursomc.domain.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="não pode ser vazio")
	@Length(min=5, max=120, message="Deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="não pode ser vazio")
	@Email
	private String  email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	

}
