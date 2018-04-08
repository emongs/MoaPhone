package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardBean;
import dao.BoardDao;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		BoardBean article = new BoardBean();
		String ses = (String) session.getAttribute("boardid");
		if (ses == null) {
			ses = "1";
		}
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setTitle(request.getParameter("title"));
		article.setPassword(request.getParameter("password"));
		article.setContent(request.getParameter("content"));

		System.out.println("a--" + article);
		BoardDao dbPro = BoardDao.getInstance();
		dbPro.insertArticle(article);
		
		
		return "/view/board/writePro.jsp";
	}

}
