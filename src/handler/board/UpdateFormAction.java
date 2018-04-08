package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardBean;
import dao.BoardDao;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		

		String boardid = "";
		HttpSession session = request.getSession();
		
		boardid = (String) session.getAttribute("boardid"); //session의 리턴타입은 Object
		if(boardid == null) {
			boardid = "1";
		}

		BoardDao dbPro = BoardDao.getInstance();
		BoardBean article = dbPro.getArticle(num);
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		return "/view/board/updateForm.jsp";
	}

}
