package chapter6.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chapter6.beans.User;
import chapter6.service.UserService;

@WebServlet(urlPatterns = {"/settings"})
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		if(session.getAttribute("editUser") == null){
			User editUser = null;
			try {
				editUser = new UserService().getUser(loginUser.getId());
			}catch(Exception e){}
			session.setAttribute("editUser", editUser);
		}
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}
}
