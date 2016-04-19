package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Delete{
	public static void main(String[] args) throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/auther";
		String user = "java";
		String password = "ic-com.Test";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		String deleteId = "1 or id = id";
		delete(connection, deleteId);

		connection.close();

	}

	public static void delete(Connection connection, String deleteId) throws SQLException{


		String sql = "delete from auther where id = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setObject(1, deleteId, Types.INTEGER);

		int updateCount = statement.executeUpdate(sql);

		if(updateCount == 1){
			System.out.println("登録成功");
			connection.commit();
		} else{
			System.out.println("登録失敗");
		}

		statement.close();
	}
}
