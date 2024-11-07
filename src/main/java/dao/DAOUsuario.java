package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionBanco;
import models.Usuario;

public class DAOUsuario {
	
	private Connection connection;
	
	public DAOUsuario() {
		connection = ConnectionBanco.getConnection();
	}
	
	public Long gravarUsuario(Usuario usuario) throws Exception {
		
		String sql = "insert into usuario(nome,tipoconta,email,senha) values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getTipoConta());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getSenha());
		
		statement.execute();
		
		ResultSet result = statement.getGeneratedKeys();
		
		if(result.next()) {
			Long idUsuario = result.getLong(1);
			connection.commit();
			return idUsuario;
		}
		
		connection.commit();
		
		return null;
	}
	
	public Long logarUsuaurio(Usuario usuario) throws Exception {
		
		String sql = "select * from usuario where upper(email) = upper(?) " + "and upper(senha) = upper(?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getEmail());
		statement.setString(2, usuario.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getLong("id");
		}
		
		return null;
	}
	
	public boolean validarEmail(String email) throws Exception {
		String sql = "select count(1) > 0 as existe from usuario where upper(email) = upper('"+email+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		
		return resultado.getBoolean("existe");
		
	}
	
	public boolean verificarTipoContaVendedor(Long id) throws Exception {
		String sql = "select tipoconta from usuario where id = ? and tipoconta = 'vendedor' ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true;
		}
		
		return false;	
	}
	
	
	public boolean verificarTipoContaCliente(Long id) throws Exception {
		String sql = "select tipoconta from usuario where id = ? and tipoconta = 'cliente' ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true;
		}
		
		return false;	
	}
	
	public Usuario consultaUsuario(Long idUsuario) throws Exception {
				
		String sql = "select * from usuario where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUsuario);
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setSenha(resultado.getString("senha"));
			
			return usuario;
		}
		
		return null;
	}
	
	public void atualizarUsuario(Usuario usuario, Long idUsuario) throws Exception {
				
		String sql = "update usuario set nome = ?, email = ?, senha = ? where id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.setLong(4, idUsuario);
		
		statement.executeUpdate();
		
		connection.commit();
		
		
	}
	
	
	public void deletarUsuario(Usuario usuario, Long idUsuario) throws Exception {
		
		String sql = "delete from usuario where id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, idUsuario);
		
		statement.execute();
		
		connection.commit();
		
	}
	
	public String obterTipoConta(Long idUsuario) throws Exception {
		String sql = "select tipoconta from usuario where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUsuario);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return resultSet.getString("tipoconta");
		}
		
		return null;
		
	}

}
