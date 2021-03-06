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
import chapter6.exception.NoRowsUpdatedRuntimeException;
import chapter6.service.UserService;

@WebServlet(urlPatterns = {"/settings"})
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User loginUser = null;
		HttpSession session = request.getSession();
		loginUser = (User) session.getAttribute("loginUser");
		if(session.getAttribute("editUser") == null){
			User editUser = new UserService().getUser(loginUser.getId());
			session.setAttribute("editUser", editUser);
		}

		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User editUser = getEditUser(request);

		session.setAttribute("editUser", editUser);

		if(isValid(request, messages)){
			try{
				new UserService().update(editUser);
			}catch(NoRowsUpdatedRuntimeException e){
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");

			}
			session.setAttribute("loginUser", editUser);
			session.removeAttribute("editUser");
			response.sendRedirect("./");
		}else{
			session.removeAttribute("editUser");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("settings");
		}

	}

	private User getEditUser(HttpServletRequest request) throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User)session.getAttribute("editUser");

		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setEmail(request.getParameter("email"));
		editUser.setDescription(request.getParameter("description"));

		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		if(StringUtils.isEmpty(account)){
			messages.add("アカウント名を入力してください");
		}
		if(StringUtils.isEmpty(password)){
			messages.add("パスワードを入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}

	}
}
