package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

public class ContaDAO {

	private Connection conn;

	ContaDAO(Connection connection) {
		this.conn = connection;
	}

	public void salvar(DadosAberturaConta dadosDaConta) {
		var cliente = new Cliente(dadosDaConta.dadosCliente());
		var conta = new Conta(dadosDaConta.numero(), BigDecimal.ZERO, cliente, true);
		String sql = "INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email)"
				+ "VALUES (?, ?, ?, ?, ?) ";

		PreparedStatement prepareStatement;
		try {
			prepareStatement = conn.prepareStatement(sql);

			prepareStatement.setInt(1, conta.getNumero());
			prepareStatement.setBigDecimal(2, BigDecimal.ZERO);
			prepareStatement.setString(3, dadosDaConta.dadosCliente().nome());
			prepareStatement.setString(4, dadosDaConta.dadosCliente().cpf());
			prepareStatement.setString(5, dadosDaConta.dadosCliente().email());
			prepareStatement.setBoolean(6, true);
			
			prepareStatement.execute();
			prepareStatement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Set<Conta> listar() {
		PreparedStatement ps;
		ResultSet resultSet;
		Set<Conta> contas = new HashSet<>();

		String sql = "SELECT * FROM conta WHERE esta_ativa = true";

		try {
			ps = conn.prepareStatement(sql);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Integer numero = resultSet.getInt(1);
				BigDecimal saldo = resultSet.getBigDecimal(2);
				String nome = resultSet.getString(3);
				String cpf = resultSet.getString(4);
				String email = resultSet.getString(5);
				Boolean estaAtiva = resultSet.getBoolean(6);
				
				DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
				Cliente cliente = new Cliente(dadosCadastroCliente);
				contas.add(new Conta(numero, saldo, cliente, estaAtiva));
			}
			resultSet.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contas;
	}

	public void alterar(Integer numero, BigDecimal valor) {
		PreparedStatement ps;
		String sql = "UPDATE conta SET saldo = ? WHERE numero = ?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setBigDecimal(1, valor);
			ps.setInt(2, numero);

			ps.execute();
			conn.commit();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			throw new RuntimeException(e);
		}
	}

	public Conta listarPorNumero(Integer numero) {
		String sql = "SELECT * FROM conta WHERE numero = ? AND esta_ativa = true";

		PreparedStatement ps;
		ResultSet resultSet;
		Conta conta = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Integer numeroRecuperado = resultSet.getInt(1);
				BigDecimal saldo = resultSet.getBigDecimal(2);
				String nome = resultSet.getString(3);
				String cpf = resultSet.getString(4);
				String email = resultSet.getString(5);
				Boolean estaAtiva = resultSet.getBoolean(6);

				DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
				Cliente cliente = new Cliente(dadosCadastroCliente);

				conta = new Conta(numeroRecuperado, saldo, cliente, estaAtiva);
			}
			resultSet.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return conta;
	}
	
	public void deletar(Integer numeroDaConta) {
		String sql = "DELETE FROM conta WHERE numero = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, numeroDaConta);
			
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	public void alterarLogico(Integer numeroDaConta) {
		PreparedStatement ps;
		String sql = "UPDATE conta SET esta_ativa = false WHERE numero = ?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setInt(1, numeroDaConta);

			ps.execute();
			conn.commit();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			throw new RuntimeException(e);
		}
	}

}
