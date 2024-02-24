package test1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/test1/tes1/item")
public class tes1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
        switch(action) {
		case "item":
			if(method.equals("GET")) {
			rd = request.getRequestDispatcher("/ch14/test1.jsp");
	        rd.forward(request, response);
			} else {
				int countdownEndValue = Integer.parseInt(request.getParameter("countdownEnd")) ;     
		        System.out.println("Countdown End Value: " + countdownEndValue);
		        response.sendRedirect("/test1/tes1");
			}
			break;
        }
	}
}
