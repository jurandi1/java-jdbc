package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.alura.bytebank.domain.cliente.Cliente;

public class ContaDAO {

	private Connection conn;

	ContaDAO(Connection connection) {
		this.conn = connection;
	}

	public void Salvar(DadosAberturaConta dadosDaConta) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
