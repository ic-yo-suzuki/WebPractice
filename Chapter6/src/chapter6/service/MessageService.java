package chapter6.service;

import static chapter6.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import chapter6.beans.Message;
import chapter6.beans.UserMessage;
import chapter6.dao.MessageDao;
import chapter6.dao.UserMessageDao;

public class MessageService {
	public void register(Message message) throws Exception{
		Connection connection = null;
		try{
			connection = getConnection();
			MessageDao messageDao = new MessageDao();

			messageDao.insert(connection, message);
			commit(connection);
		}catch(RuntimeException e){
			e.printStackTrace();
			rollback(connection);
			throw e;
		}catch(Error e){
			e.printStackTrace();
			rollback(connection);
			throw e;
		}finally{
			connection.close();
		}
	}
	private static final int LIMIT_NUM = 1000;
	public List<UserMessage> getMessage() throws Exception {
		Connection connection = null;
		List<UserMessage> ret;
		try{
			connection = getConnection();
			UserMessageDao messageDao = new UserMessageDao();
			ret = messageDao.getUserMessages(connection, LIMIT_NUM);
			commit(connection);
		}catch(RuntimeException e){
			e.printStackTrace();
			rollback(connection);
			throw e;
		}catch(Error e){
			e.printStackTrace();
			rollback(connection);
			throw e;
		}finally{
			connection.close();
		}
		return ret;
	}
}
