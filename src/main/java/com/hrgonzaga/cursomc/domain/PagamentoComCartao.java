package com.hrgonzaga.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hrgonzaga.cursomc.enums.EstadoPagamento;

import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	
	private static final long serialVersionUID = 1L;

	private int numeroDeParcelas;

	public PagamentoComCartao() {
		
	}
	
	

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, int numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}



	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(int numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
