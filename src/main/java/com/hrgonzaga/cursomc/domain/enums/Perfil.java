package com.hrgonzaga.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"Quitado");
	
	
	private Integer id;
	private String descricao;
	
	
	
	private Perfil(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return descricao;
	}
	public void setNome(String descricao) {
		this.descricao = descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID invalido: "+ cod);
	}
	
	
}
