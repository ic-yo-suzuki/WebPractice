package chapter6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.User;
import chapter6.service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignUp extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if(isValid(request, messages)){
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setDescription(request.getParameter("description"));

			new UserService().register(user);

			response.sendRedirect("./");
		}else{
			session.setAttribute("errorMessage", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String passoword = request.getParameter("password");

		if(StringUtils.isEmpty(account)){
			messages.add("アカウント名を入力してください");
		}
		if(StringUtils.isEmpty(passoword)){
			messages.add("パスワードを入力してください");
		}
		// TODO アカウントがすでに利用されていないか、メールアドレスが
		//　　　登録されていないかなどの確認も必要

		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
