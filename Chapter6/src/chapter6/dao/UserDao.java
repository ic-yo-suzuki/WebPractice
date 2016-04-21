package chapter6.dao;

import static chapter6.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import chapter6.beans.User;
import chapter6.exception.NoRowsUpdatedRuntimeException;
import chapter6.exception.SQLRuntimeException;

public class UserDao {



	public void insert(Connection connection, User user) {
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();

			sql.append("insert into user ( ");
			sql.append("account, name, email, password, description, insert_date, update_date");
			sql.append(" ) values ( ");
			sql.append("?, ?, ?, ?, ?, ");
			sql.append("CURRENT_TIMESTAMP, ");
			sql.append("CURRENT_TIMESTAMP);");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getDescription());
			System.out.println(sql.toString());
			ps.executeUpdate();

		}catch(SQLException e){
//			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws Exception {
		List<User> ret = new ArrayList<User>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String description = rs.getString("description");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setDescription(description);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;

		}finally{
			close(rs);
		}
	}

	public User getUser(Connection connection, String accountOrEmail, String encPassword) {
		PreparedStatement ps = null;
		List<User> userList = null;
		try{
			String sql = "select * from user where (account = ? or email = ?) and password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1,  accountOrEmail);
			ps.setString(2, accountOrEmail);
			ps.setString(3, encPassword);

			ResultSet rs = ps.executeQuery();
			userList = toUserList(rs);
			if(userList.isEmpty()){
				return null;
			}else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");
			}else{

			}
		}catch(Exception e){
		}finally{
			close(ps);
		}
		return userList.get(0);
	}

	public User getUser(Connection connection, int id) {
		PreparedStatement ps = null;
		List<User> userList = null;
		try{
			String sql = "select * from user where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			userList = toUserList(rs);
			if(userList.isEmpty()){
				return null;
			}else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");
			}
		}catch(Exception e){

		}finally{
			close(ps);
		}
		return userList.get(0);
	}

	public void update(Connection connection, User user) {
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("update user set account = ?, name = ?, email = ?, password = ?, description = ?, update_date = CURRENT_TIMESTAMP ");
			sql.append("where id = ? and update_date = ?;");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getDescription());
			ps.setInt(6, user.getId());
			ps.setTimestamp(7, new Timestamp(user.getUpdateDate().getTime()));
			System.out.println(ps.toString());
			if((ps.executeUpdate()) == 0){
				throw new NoRowsUpdatedRuntimeException();
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}

	}

}
