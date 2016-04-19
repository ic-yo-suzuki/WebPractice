package chapter5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	static{
		String driver = "com.mysql.jdbc.Driver";
		try{
			Class.forName(driver);

		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}

	private static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost/auther";
		String user = "java";
		String password = "ic-com.Test";

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		return connection;
	}

	public List<Book> selectAll() throws SQLException{
		Connection connection = getConnection();
		String sql = "select * from book order by id;";
		PreparedStatement statement = connection.prepareStatement(sql);

		List<Book> ret = new ArrayList<Book>();

		ResultSet rs = statement.executeQuery();

		while(rs.next()){
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setAutherId(rs.getInt("auther_id"));
			book.setTitle(rs.getString("title"));

			ret.add(book);
		}

		rs.close();
		statement.close();
		connection.close();

		return ret;
	}

	public int insert(Book book) throws SQLException{
		Connection connection = getConnection();

		String sql = "insert into book(auther_id, title) values(?, ?);";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, book.getAutherId());
		statement.setString(2, book.getTitle());

		int updateCount = statement.executeUpdate();

		statement.close();
		connection.commit();
		connection.close();

		return updateCount;
	}
}
