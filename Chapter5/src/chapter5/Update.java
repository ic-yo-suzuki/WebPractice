package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
	public static void main(String[] args) throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/auther";
		String user = "java";
		String password = "ic-com.Test";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		select(connection);

		connection.close();

	}

	public static void select(Connection connection) throws SQLException{
		Statement statement = connection.createStatement();

		String sql = "update auther set sex ='女' where id = 0;";
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
