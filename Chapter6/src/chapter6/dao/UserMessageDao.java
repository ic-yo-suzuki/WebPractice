package chapter6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import chapter6.beans.UserMessage;

public class UserMessageDao {
	public List<UserMessage> getUserMessages(Connection connection, int num) throws Exception{
		PreparedStatement ps  = null;
		List<UserMessage> ret = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from user_message ");
			sql.append("order by insert_date DESC limit " + num + ";");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			ret = toUserMessageList(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return ret;
	}

	public List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {
		List<UserMessage> ret = new ArrayList<UserMessage>();
		try{

			while(rs.next()){
				String account = rs.getString("account");
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserMessage message = new UserMessage();
				message.setAccount(account);
				message.setId(id);
				message.setUserId(userId);
				message.setName(name);
				message.setText(text);
				message.setInsertDate(insertDate);

				ret.add(message);

			}
			return ret;
		}finally{
			rs.close();
		}

	}
}
