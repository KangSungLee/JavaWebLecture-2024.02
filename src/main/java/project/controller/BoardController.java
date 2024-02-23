package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.entity.Reply;
import project.entity.User;
import project.service.BoardServies;
import project.service.BoardServiesImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.runtime.BodyContentImpl;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet({"/bbs/board/list", "/bbs/board/insert", "/bbs/board/update",
			"/bbs/board/delete", "/bbs/board/detail"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardServies bSvc = new BoardServiesImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		String method = request.getMethod();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		String title = "", content = "", field = "", query = "", page_ = "";
		String uid = "";
		Board board = null;
		int bid = 0, page = 0;
		String sessUid = (String) session.getAttribute("sessUid");
		
		switch(action) {
		case "list":		// /jw/bbs/board/list?p=1&f=title&q=검색
			page_ = request.getParameter("p");
			field = request.getParameter("f");
			query = request.getParameter("q");
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			field = (field == null || field.equals("")) ? "title" : field;
			query = (query == null || query.equals("")) ? "" : query;
			session.setAttribute("currentBoardPage", page);
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			List<Board> boardList = bSvc.getBoardList(page, field, query);
			request.setAttribute("boardList", boardList); 
			
			// for pagination
			int totalItems = bSvc.getBoardCount(field, query);
			int totalPages = (int)Math.ceil(totalItems * 1.0 / bSvc.COUNT_PER_PAGE);
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++) {
				pageList.add(String.valueOf(i));
			}
			request.setAttribute("pageList", pageList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
			rd.forward(request, response);
			break;
			
		case "insert":
			if(sessUid == null || sessUid.equals("")) {
				response.sendRedirect("/jw/bbs/user/login");
				break;
			} 
			if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
				rd.forward(request, response);
			} else {
				title = request.getParameter("title");
				content = request.getParameter("content");
				board = new Board(title, content, sessUid);
				bSvc.insertBoard(board);
				response.sendRedirect("/jw/bbs/board/list?p=1");
			}
			break;
		
		case "detail":
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = request.getParameter("uid");
			if (!uid.equals(sessUid)) {
				bSvc.increaseViewCount(bid);						
			}
			
			board = bSvc.getBoard(bid);
			request.setAttribute("board", board);
			
			List<Reply> replyList = null;		// 댓글 목록 필요!!!
			request.setAttribute("replyList", replyList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
			rd.forward(request, response);
			break;
		
		case "delete":
			bid = Integer.parseInt(request.getParameter("bid"));
			bSvc.deleteBoard(bid);
			page = (Integer) session.getAttribute("currentBoardPage");
			field = (String) session.getAttribute("field");
			query = (String) session.getAttribute("query");
			response.sendRedirect("/jw/bbs/board/list?p=" + page + "&f=" + field + "&q=" + query);
			break;
			
		case "update":
			if (method.equals("GET")) {
				bid = Integer.parseInt(request.getParameter("bid"));
				board = bSvc.getBoard(bid);
				request.setAttribute("board", board);
				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
				rd.forward(request, response);
			} else {
				bid = Integer.parseInt(request.getParameter("bid"));
				uid = request.getParameter("uid");
				title = request.getParameter("title");
				content = request.getParameter("content");
				board = new Board(bid, title, content);
				
				bSvc.updateBoard(board);
				response.sendRedirect("/jw/bbs/board/detail?bid=" + bid + "&uid=" + uid);
			}
			break;
		}
	}

}
