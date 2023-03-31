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
		var conta = new Conta(dadosDaConta.numero(), cliente);
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
			
		String sql = "SELECT * FROM conta"; 
		
		try {
			ps = conn.prepareStatement(sql);
			resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Integer numero = resultSet.getInt(1);
				BigDecimal saldo = resultSet.getBigDecimal(2);
				String nome = resultSet.getString(3);
				String cpf = resultSet.getString(4);
				String email = resultSet.getString(5);
				DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
				Cliente cliente = new Cliente(dadosCadastroCliente);
				contas.add(new Conta(numero, cliente));
			}
			resultSet.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contas;
	}
	
	
	
	
	
}
