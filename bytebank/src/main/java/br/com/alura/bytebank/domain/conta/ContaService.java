package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Set;

import br.com.alura.bytebank.ConnectionFactory;
import br.com.alura.bytebank.domain.RegraDeNegocioException;

public class ContaService {

	private ConnectionFactory connection;

	public ContaService() {
		this.connection = new ConnectionFactory();
	}

	public Set<Conta> listarContasAbertas() {
		Connection conn = connection.recuperarConexao();
		return new ContaDAO(conn).listar();
	}

	public BigDecimal consultarSaldo(Integer numeroDaConta) {
		var conta = buscarContaPorNumero(numeroDaConta);
		return conta.getSaldo();
	}

	public void abrir(DadosAberturaConta dadosDaConta) {
		Connection conn = connection.recuperarConexao();
		new ContaDAO(conn).salvar(dadosDaConta);;
	}

	public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
		var conta = buscarContaPorNumero(numeroDaConta);
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
		}

		if (valor.compareTo(conta.getSaldo()) > 0) {
			throw new RegraDeNegocioException("Saldo insuficiente!");
		}
		
		if (!conta.getEstaAtiva()) {
			throw new RegraDeNegocioException("Conta inativa");
		}

		Connection conn = connection.recuperarConexao();
		BigDecimal novoValor = conta.getSaldo().subtract(valor);
        new ContaDAO(conn).alterar(conta.getNumero(), novoValor);
	}

	public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
		var conta = buscarContaPorNumero(numeroDaConta);
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
		}

		if (!conta.getEstaAtiva()) {
			throw new RegraDeNegocioException("Conta inativa");
		}
		
		Connection conn = connection.recuperarConexao();
		BigDecimal novoValor = conta.getSaldo().add(valor);
		new ContaDAO(conn).alterar(conta.getNumero(), novoValor);
		
	}
	
	public void realizarTransferencia(Integer numeroDaContaOrigem, Integer numeroDaContaDestino, BigDecimal valor) {
		this.realizarSaque(numeroDaContaOrigem, valor);
		this.realizarDeposito(numeroDaContaDestino, valor);
	}
	

	public void encerrar(Integer numeroDaConta) {
		var conta = buscarContaPorNumero(numeroDaConta);
		if (conta.possuiSaldo()) {
			throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
		}

		Connection conn = connection.recuperarConexao();
		
		new ContaDAO(conn).deletar(numeroDaConta);
	}
	
	public void encerrarLogico(Integer numeroDaConta) {
		var conta = buscarContaPorNumero(numeroDaConta);
		if (conta.possuiSaldo()) {
			throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
		}

		Connection conn = connection.recuperarConexao();
		
		new ContaDAO(conn).alterarLogico(numeroDaConta);
	}

	private Conta buscarContaPorNumero(Integer numero) {
		Connection conn = connection.recuperarConexao();
		Conta conta = new ContaDAO(conn).listarPorNumero(numero);
		if(conta != null) {
			return conta;
		} else {
			throw new RegraDeNegocioException("Não existe conta cadastrada com esse número!");
		}
		 
	}
}
