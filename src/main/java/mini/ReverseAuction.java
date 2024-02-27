package mini;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/mini/aDetail", "/mini/aInsert", "/mini/aList"})

public class ReverseAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionsService aSvc = new AuctionsServiceImpl();
    
	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String[] uri = request.getRequestURI().split("/");
	      String action = uri[uri.length - 1];
	      String method = request.getMethod();
	      HttpSession session = request.getSession();
	      RequestDispatcher rd = null;
	      String title = "", start_price_ = "", content = "", equipment_id = "", page_ = "";
	      int start_price = 0, page = 0;
	      Auctions auctions = null;
	      
	      request.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html; charset=utf-8");
	      
	      switch(action) {
	      case "aList":
	    	  	page_ = request.getParameter("p");
	    	  	page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
	    	  	List<Auctions> auctionsList = aSvc.getAuctionsList(page);
	    	  	request.setAttribute("auctionsList", auctionsList); 
	    	  	
	    	  	int totalItems = aSvc.getAuctionsCount();
				int totalPages = (int)Math.ceil(totalItems * 1.0 / aSvc.COUNT_PER_PAGE);
	    	  	List<String> pageList = new ArrayList<String>();
				for (int i = 1; i <= totalPages; i++) {
					pageList.add(String.valueOf(i));
				}
				request.setAttribute("pageList", pageList);
				rd = request.getRequestDispatcher("/mini/aList.jsp");
				rd.forward(request, response);
				break;
				
	      // 역경매 Insert
	      case "aInsert":
	    	 if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/mini/aInsert.jsp");
				rd.forward(request, response);
	    	 } else {
	    		equipment_id = request.getParameter("equipment_id");
				title = request.getParameter("title");
				start_price_ = request.getParameter("start_price");
				start_price = (start_price_ == null || start_price_.equals("")) ? 0 : Integer.parseInt(start_price_);
				content = request.getParameter("content");
				auctions = new Auctions(equipment_id, title, start_price, content);
				aSvc.insertAuctions(auctions);
				
				response.sendRedirect("/jw/mini/aList.jsp");
	    	 }
	      // 역경매 디테일
	      case "aDetail":
	         if (method.equals("GET")) {
//				rd = request.getRequestDispatcher("/mini/aDetail.jsp");
//				rd.forward(request, response);
	         } else {
	            
	         }
	         break;
	        }
	   }
	}