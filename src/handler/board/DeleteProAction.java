package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String boardid = request.getParameter("boardid"); // 보드1에서 세션으로 정의... 여기서 헤더에서 정의해줌. 
		//헤더에서 해당 게시판의 종류에 따라서 세션을 다른 값으로 넘겨줌. ex 공지사항 1 / 자유게시판 2 / 등등등
		HttpSession session = request.getSession();
		if(boardid != null) {
			session.setAttribute("boardid", boardid);
		} 

		if(session.getAttribute("boardid") == null) {
			boardid = "1";
		} else {
			boardid = (String)session.getAttribute("boardid");
		}
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("password");
		BoardDao dbPro = BoardDao.getInstance();
		int check = dbPro.deleteArticle(num,passwd);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/view/board/deletePro.jsp";
	}

}
