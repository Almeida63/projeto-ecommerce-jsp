package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionBanco;
import models.Produto;

public class DAOProduto {

	private Connection connection;

	public DAOProduto() {
		connection = ConnectionBanco.getConnection();
	}

	public Long gravarProduto(Produto produto) throws Exception {

		String sql = "insert into produto(titulo, descricao, tipoproduto, preco, idvendedor, fotoproduto, extensaofoto) values(?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setString(1, produto.getTitulo());
		statement.setString(2, produto.getDescricao());
		statement.setString(3, produto.getTipo());
		statement.setBigDecimal(4, produto.getValor());
		statement.setLong(5, produto.getId_vendedor());
		statement.setString(6, produto.getFotoProduto());
		statement.setString(7, produto.getExtensaofoto());

		statement.execute();

		ResultSet result = statement.getGeneratedKeys();

		if (result.next()) {
			Long idProduto = result.getLong(1);
			connection.commit();
			return idProduto;
		}

		connection.commit();

		return null;

	}

	public List<Produto> imprimirListaVendedor(Long idVendedor) throws Exception {

		List<Produto> list = new ArrayList<>();

		String sql = "select * from produto where idvendedor = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idVendedor);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setTipo(result.getString("tipoProduto"));
			produto.setDescricao(result.getString("descricao"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			list.add(produto);
		}

		return list;
	}

	public void atualizarProduto(Produto produto, Long idProduto) throws Exception {

		String sql = "update produto set titulo = ?, descricao = ?, tipoproduto = ? , preco = ?, fotoproduto = ?, extensaofoto = ? where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, produto.getTitulo());
		statement.setString(2, produto.getDescricao());
		statement.setString(3, produto.getTipo());
		statement.setBigDecimal(4, produto.getValor());
		statement.setString(5, produto.getFotoProduto());
		statement.setString(6, produto.getExtensaofoto());
		statement.setLong(7, idProduto);

		statement.executeUpdate();

		connection.commit();
	}

	public void deletarProduto(Produto produto, Long idProduto) throws Exception {

		String sql = "delete from produto where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idProduto);

		statement.execute();

		connection.commit();
	}

	public Produto consultaProduto(Long idProduto) throws Exception {
		String sql = "select * from produto where id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, idProduto);

		ResultSet result = statement.executeQuery();

		if (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setDescricao(result.getString("descricao"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			return produto;

		}

		return null;

	}

	public List<Produto> exibirTodosProdutos() throws Exception {

		List<Produto> list = new ArrayList<>();

		String sql = "select * from produto";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setDescricao(result.getString("descricao"));
			produto.setTipo(result.getString("tipoProduto"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			list.add(produto);
			;
		}

		System.out.println("Total de produtos retornados: " + list.size());

		return list;

	}

	public List<Produto> consultaProdutoPaginada(Integer offSet) throws Exception {

		List<Produto> retorno = new ArrayList<Produto>();

		String sql = "select * from produto order by titulo offset " + offSet + " limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setDescricao(result.getString("descricao"));
			produto.setTipo(result.getString("tipoProduto"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			retorno.add(produto);
		}

		return retorno;

	}
	
	
	public int totalPaginaProduto() throws Exception {

		String sql = "select count(id) as total from produto";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		resultado.next();

		Double totalProdutos = resultado.getDouble("total");

		Double porPagina = 5.0;

		//Double pagina = totalProdutos / porPagina;

		//Double resto = pagina % 2;

		//if (resto > 0) {
			//pagina++;
		//}

		//return pagina.intValue();
		
		int totalPaginas = (int) Math.ceil((double) totalProdutos / porPagina);

	    return totalPaginas;

	}
	
	
	public List<Produto> filtroProduto(String tipoProduto) throws Exception {

		List<Produto> list = new ArrayList<>();

		String sql = "select * from produto where tipoproduto = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tipoProduto);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setDescricao(result.getString("descricao"));
			produto.setTipo(result.getString("tipoProduto"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			list.add(produto);
		}

		System.out.println("Tipo Produto: " + tipoProduto + " quantidade: " + list.size());

		return list;
	}

	public List<Produto> filtroProdutoPaginado(String tipoProduto, Integer offSet) throws Exception {
		List<Produto> list = new ArrayList<>();

		String sql = "select * from produto where tipoproduto = ? order by titulo offset ? limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tipoProduto);
		statement.setInt(2, offSet);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Produto produto = new Produto();
			produto.setId(result.getLong("id"));
			produto.setTitulo(result.getString("titulo"));
			produto.setDescricao(result.getString("descricao"));
			produto.setTipo(result.getString("tipoProduto"));
			produto.setValor(result.getBigDecimal("preco"));
			produto.setFotoProduto(result.getString("fotoProduto"));
			produto.setExtensaofoto(result.getString("extensaoFoto"));

			list.add(produto);
		}

		return list;
	}

	public int totalPaginaProdutoFiltrado(String tipoProduto) throws Exception {
		String sql = "select count(id) AS total FROM produto WHERE tipoproduto = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, tipoProduto);

		ResultSet resultado = statement.executeQuery();

		resultado.next();

		Double totalProdutos = resultado.getDouble("total");
		Double porPagina = 5.0;
		//Double pagina = totalProdutos / porPagina;

		//Double resto = pagina % 2;

		//if (resto > 0) {
			//pagina++;
		//}

		//return pagina.intValue();
		
	    int totalPaginas = (int) Math.ceil((double) totalProdutos / porPagina);

	    return totalPaginas;
	}

}
