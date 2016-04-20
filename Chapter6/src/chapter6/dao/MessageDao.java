package chapter6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chapter6.beans.Message;


public class MessageDao {
	public void insert(Connection connection, Message message) throws Exception{
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("insert into message(user_id, text, insert_date, update_date) ");
			sql.append("values(?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);");
			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUserId());
			ps.setString(2, message.getText());
			ps.executeUpdate();
		}catch(SQLException e){

		}finally{
			ps.close();
		}
	}
}
