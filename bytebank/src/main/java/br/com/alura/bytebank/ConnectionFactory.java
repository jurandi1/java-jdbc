package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {

	public Connection recuperarConexao() {
		try {
			return createDataSource().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/byte_bank");
        config.setUsername("junior");
        config.setPassword("@Testando");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}
