import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	
	
	public Connection con;
	public PreparedStatement pst;
	public ResultSet rs;
	
	public void conectar() {
		try {			
			String addressDB = "jdbc:mysql://localhost:3306/db_consultorio";
			String userDB = "root";
			String passwordDB = "123";
			Class.forName("com.mysql.cj.jdbc.Driver");//CONFIGURAÇÃO PARA DIZER QUE ESTAMOS UTILIZANDO UM BANCO MYSQL
			con = DriverManager.getConnection(addressDB,userDB,passwordDB); //CONFIGURAÇÃO PARA ACESSAR O BANCO DE DADOS
			
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(FormMedico.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
