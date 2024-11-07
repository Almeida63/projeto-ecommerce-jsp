package models;

import java.math.BigDecimal;

public class Carrinho {
	private Long idCarrinho;
	private Long idProduto;
	private Long idUsuario;
	
	private String titulo;
	private String tipoProduto;
	private String descricao;
	private BigDecimal preco;
	private String fotoProduto;
	private String extensaoFoto;
	
	public Long getIdCarrinho() {
		return idCarrinho;
	}
	public void setIdCarrinho(Long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getFotoProduto() {
		return fotoProduto;
	}
	public void setFotoProduto(String fotoProduto) {
		this.fotoProduto = fotoProduto;
	}
	public String getExtensaoFoto() {
		return extensaoFoto;
	}
	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}
	
	
	
	

}
