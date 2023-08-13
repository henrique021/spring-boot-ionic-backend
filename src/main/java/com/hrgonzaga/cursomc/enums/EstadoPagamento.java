package com.hrgonzaga.cursomc.enums;

public enum EstadoPagamento {

	
	
	
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private Integer id;
	private String descricao;

	EstadoPagamento(int i, String string) {
		this.id = i;
		this.descricao = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
