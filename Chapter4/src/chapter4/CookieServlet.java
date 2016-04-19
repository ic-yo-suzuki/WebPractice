package chapter4;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userName = null;
		userName = request.getParameter("name");
		if (StringUtil.isEmpty(userName)) {
			userName = getUserNameFromCookie(request);
		}
		setUserNameToCookie(response, userName);
		request.setAttribute("userName", userName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cookie.jsp");
		dispatcher.forward(request, response);
	}

	private void setUserNameToCookie(HttpServletResponse response, String userName){
		Cookie cookie = new Cookie ("userName", encode(userName));
		cookie.setMaxAge(30);
		response.addCookie(cookie);
	}

	private String getUserNameFromCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			if("userName".equals(name)){
				return decode(cookie.getValue());
			}
		}
		return null;
	}

	private String encode(String string){
		if(string == null){
			return null;
		}
		try{
			return URLEncoder.encode(string, "UTF-8");
		}catch(UnsupportedEncodingException e){
			throw new RuntimeException(e);
		}
	}

	private String decode(String string){
		if(string == null){
			return null;
		}
		try{
			return URLDecoder.decode(string, "UTF-8");
		}catch(UnsupportedEncodingException e){
			throw new RuntimeException(e);
		}
	}
}
