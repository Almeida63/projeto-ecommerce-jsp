package models;

import java.math.BigDecimal;

public class Produto {
	private Long id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
	private String tipo;
	private Long id_vendedor;
	private String fotoProduto;
	private String extensaofoto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(Long id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFotoProduto() {
		return fotoProduto;
	}
	public void setFotoProduto(String fotoProduto) {
		this.fotoProduto = fotoProduto;
	}
	public String getExtensaofoto() {
		return extensaofoto;
	}
	public void setExtensaofoto(String extensaofoto) {
		this.extensaofoto = extensaofoto;
	}
	
	
	
	
	
	
	
	

}
