package chapter6.service;

import static chapter6.utils.DBUtil.*;

import java.sql.Connection;

import chapter6.beans.User;
import chapter6.dao.UserDao;
import chapter6.utils.CipherUtil;

public class LoginService {
	public User login(String accountOrEmail, String password) throws Exception{
		Connection connection = null;
		User user = null;
		try{
			connection = getConnection();
			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			user = userDao.getUser(connection, accountOrEmail, encPassword);
			commit(connection);

		}catch(RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return user;
	}
}
