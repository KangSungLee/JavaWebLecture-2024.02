package ch07_dao.msg;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet({ "/ch07/msg/list", "/ch07/msg/insert", "/ch07/msg/update",
	"/ch07/msg/delete", "/ch07/msg/listWriter"})
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageDao mDao = new MessageDao();
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		String content = null;
		String writer = null;
		Message message = null;
		int mid = 0;
		String modTime = null;
		
		switch (action) {
		case "list":
			List<Message> list = mDao.getMessageList();
			rd = request.getRequestDispatcher("/ch07/msg/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			break;
		case "listWriter":
			
		case "insert":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/msg/insert.jsp");
				rd.forward(request, response);
			} else {
				content = request.getParameter("content");
				writer = request.getParameter("writer");
				
				message = new Message(content,writer);
				mDao.insertMessage(message);
				
				response.sendRedirect("/jw/ch07/msg/list");
			}
		case "update":
			if (method.equals("GET")) {
				mid = Integer.parseInt(request.getParameter("mid"));
				message = mDao.getMessageByMid(mid);
				rd = request.getRequestDispatcher("/ch07/msg/msgUpdate.jsp");
				request.setAttribute("message", message);
				rd.forward(request, response);
			} else {
				mid = Integer.parseInt(request.getParameter("mid"));
				content = request.getParameter("content");
				writer = request.getParameter("writer");
				modTime = request.getParameter("modTime");

				message = new Message(mid, content, writer, LocalDateTime.parse(modTime));
				mDao.updateMessage(message);

				response.sendRedirect("/jw/ch07/msg/list");
			}
			break;
		case "delete":
			mid = Integer.parseInt(request.getParameter("mid"));
			mDao.deleteMessage(mid);
			response.sendRedirect("/jw/ch07/msg/list");
//				+ "?district=Kyonggi&num=30&offset=0");
			break;
		default:
			
		}
	}
}