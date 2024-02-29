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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/mini/aDetail", "/mini/aInsert", "/mini/aList", "/mini/auctions"})

public class ReverseAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionsService aSvc = new AuctionsServiceImpl();
    
	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String[] uri = request.getRequestURI().split("/");
	      String action = uri[uri.length - 1];
	      String method = request.getMethod();
	      HttpSession session = request.getSession();
	      RequestDispatcher rd = null;
	      String title = "", start_price_ = "", content = "", user_id = "", page_ = "", current_price_ = "", seller_id = "";
	      int start_price = 0, page = 0, auction_id = 0, current_price = 0;
	      Auctions auctions = null;
	      String sessuser_id = (String) session.getAttribute("sessuser_id");
	      
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
	    	  if(sessuser_id == null || sessuser_id.equals("")) {
					response.sendRedirect("/jw/auctiondb/user/login");
					break;
				} 
	    	 if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/mini/aInsert.jsp");
				rd.forward(request, response);
	    	 } else {
//	    		user_id = request.getParameter("user_id");
				title = request.getParameter("title");
				start_price_ = request.getParameter("start_price");
				start_price = (start_price_ == null || start_price_.equals("")) ? 0 : Integer.parseInt(start_price_);
				content = request.getParameter("content");
				//					로그인중인 세션아이디
				auctions = new Auctions(sessuser_id, title, start_price, content);
				aSvc.insertAuctions(auctions);
				
				response.sendRedirect("/jw/mini/aList?p=1");
	    	 }
	    	 break;
	      // 역경매 디테일
	      case "aDetail":
				auction_id = Integer.parseInt(request.getParameter("auction_id"));
				auctions = aSvc.getAuctions(auction_id);
				int discount = auctions.getStart_price() - auctions.getCurrent_price();
				request.setAttribute("auctions", auctions);
				request.setAttribute("discount", discount);
				
				String now = LocalDateTime.now().toString().substring(0, 19);
				LocalDateTime startDate = LocalDateTime.parse(now, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		        LocalDateTime endDate = LocalDateTime.parse(auctions.getEnd_time().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		        Duration duration = Duration.between(startDate, endDate);
		        long seconds = duration.getSeconds();
		        int secondsInt = (int) seconds;
				request.setAttribute("secondsInt", secondsInt);
				
				rd = request.getRequestDispatcher("/mini/aDetail.jsp");
				rd.forward(request, response);
				break;
		  // 경매 참여
	      case "auctions":
	    	  	auction_id = Integer.parseInt(request.getParameter("auction_id"));
	    	  	current_price_ = request.getParameter("current_price");
	    	  	current_price = (current_price_ == null || current_price_.equals("")) ? 1 : Integer.parseInt(current_price_);
//	    	  	seller_id = request.getParameter("seller_id");		현재 로그인중인 세션id로
	    	  	auctions = new Auctions(auction_id, current_price, sessuser_id);
	    	  	aSvc.auctionParticipation(auctions);
	    	  	
	    	  	response.sendRedirect("/jw/mini/aList?p=1");
	    	  	break;
	        }
	     
	   }
	}