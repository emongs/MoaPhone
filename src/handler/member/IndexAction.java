package handler.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.board.CommandAction;

public class IndexAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		// TODO Auto-generated method stub
		
		req.setAttribute("message", "Success Member Connection!");
		return "/view/index/index.jsp";
	
	}
	
}
