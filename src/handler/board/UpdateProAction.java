package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import dao.BoardDao;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		BoardBean article = new BoardBean();
		String pageNum = request.getParameter("pageNum");

		BoardDao dbPro = BoardDao.getInstance();
		
		
		
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setTitle(request.getParameter("title"));
		article.setPassword(request.getParameter("password"));
		article.setContent(request.getParameter("content"));

		int check = dbPro.updateArticle(article);
		request.setAttribute("check", check);
		System.out.println("a--" + article);
		
		
		return "/view/board/updatePro.jsp";
	}

}
