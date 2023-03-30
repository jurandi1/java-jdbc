package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

	public static void main(String... x) {

	try {
		Connection con = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/byte_bank?user=junior&password=@Testando");
	
		System.out.println("Recuperei a conexão");
		
		con.close();
	} catch (SQLException e) {
		System.out.println(e);
	}
	
	}

}
