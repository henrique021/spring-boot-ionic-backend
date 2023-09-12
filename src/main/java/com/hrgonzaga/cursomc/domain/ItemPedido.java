package com.hrgonzaga.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	
	public ItemPedido() {
		
	}
	
	
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public double getSubTotal() {
		
		return (preco-desconto) * quantidade;
	}

	@JsonIgnore
	public Pedido getPedido() {
		
		return id.getPedido();
	}
	
	
	public Produto getProduto() {
		
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append("  Qtde:  ");
		builder.append(getQuantidade());
		builder.append(", Preço Unitário:  ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal:  ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	

}