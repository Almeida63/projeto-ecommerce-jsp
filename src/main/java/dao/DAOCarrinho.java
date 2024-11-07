package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionBanco;
import models.Carrinho;

public class DAOCarrinho {
	
	private Connection connection;
	
	public DAOCarrinho() {
		connection = ConnectionBanco.getConnection();
	}

	
	
	public Long adicionarProdutoCarrinho(Long idProduto, Long idUsuario) throws Exception{
		
		String sql = "insert into carrinho(idProduto, idUsuario) values(?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setLong(1, idProduto);
		statement.setLong(2, idUsuario);
		
		statement.execute();
		
		ResultSet result = statement.getGeneratedKeys();
		
		if(result.next()) {
			Long idCarrinho = result.getLong(1);
			connection.commit();
			return idCarrinho;
		}
		
		connection.commit();
		
		return null;
	}
	
	public void deletarProdutoCarrinho(Long idProduto, Long idUsuario) throws Exception {
		
		String sql = "delete from carrinho where idProduto = ? and idUsuario = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idProduto);
		statement.setLong(2, idUsuario);
		
		statement.execute();
		
		connection.commit();
	}
	
	public List<Carrinho> visualizarCarrinhoUsuario(Long idUsuario) throws Exception {
		
		List<Carrinho> list = new ArrayList<>();
		
		String sql = "select c.idCarrinho, c.idProduto, c.idUsuario, p.titulo, p.descricao, p.tipoproduto, p.preco, p.fotoproduto, p.extensaofoto "
				+ "from carrinho c join produto p on c.idproduto = p.id where c.idUsuario = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUsuario);
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			Carrinho carrinho = new Carrinho();
			carrinho.setIdCarrinho(result.getLong("idCarrinho"));
			carrinho.setIdProduto(result.getLong("idProduto"));
			carrinho.setIdUsuario(result.getLong("idUsuario"));
			
			carrinho.setTitulo(result.getString("titulo"));
			carrinho.setDescricao(result.getString("descricao"));
			carrinho.setTipoProduto(result.getString("tipoProduto"));
			carrinho.setPreco(result.getBigDecimal("preco"));
			carrinho.setFotoProduto(result.getString("fotoProduto"));
			carrinho.setExtensaoFoto(result.getString("extensaoFoto"));
			
			list.add(carrinho);
		}
		
	    System.out.println("Produtos no carrinho para o usuário " + idUsuario + ": " + list.size());

		
		return list;
	}
}
