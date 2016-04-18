package chapter4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<String> orders = (List<String>)session.getAttribute("orders");
		if(orders == null){
			orders = new ArrayList<String>();
		}
		String newOrder = request.getParameter("newOrder");
		if(newOrder.isEmpty() == false){
			orders.add(newOrder);
		}

		session.setAttribute("orders", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
		dispatcher.forward(request, response);

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String[]> parameterMap = request.getParameterMap();
		if(parameterMap.containsKey("reset")){
			HttpSession session = request.getSession();
			session.invalidate();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("shoppingCart.jsp");
		dispatcher.forward(request, response);

	}
}
