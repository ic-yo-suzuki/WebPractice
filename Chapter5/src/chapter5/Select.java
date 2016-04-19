package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/auther";
		String user = "java";
		String password = "ic-com.Test";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		select(connection);
		connection.commit();
		connection.close();

	}

	public static void select(Connection connection) throws SQLException{
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM auther";
		ResultSet rs = statement.executeQuery(sql);

		System.out.println("=======SELECTの結果(ここから)=======");

		while(rs.next()){
			int userID = rs.getInt("id");
			String userName = rs.getString("name");
			String userKana = rs.getString("kana");
			String userSex = rs.getString("sex");
			System.out.println(userID + ", " + userName + ", " + userKana + ", " + userSex);
		}
		System.out.println("=======SELECTの結果(ここまで)=======");

		rs.close();
		statement.close();
	}
}
