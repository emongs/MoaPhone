package controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardBean;
import model.MemberBean;

import com.sist.msk.Action;

import dao.BoardDao;
import dao.MybatisBoardDao;

public class BoardAction extends Action {

	public String list(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {

		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute("mb");
		request.setCharacterEncoding("utf-8");
		int pageSize = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String phone_kind = request.getParameter("phone_kind");
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();
		
		if(phone_kind != null) {
		count = dbPro.getArticleCount(phone_kind);
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow, phone_kind);
			number = count - (currentPage - 1) * pageSize;
		}

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("bottomLine", bottomLine);
		// =========================================================
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("phone_kind", phone_kind);
		request.setAttribute("articleList", articleList);
		request.setAttribute("mb", mb);
		} 
		return "/view/board/list.jsp";
	}

	public String content(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("content phone : " + phone_kind);
		String pageNum = request.getParameter("pageNum");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();
		BoardBean article = dbPro.getArticle(num,phone_kind);
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		request.setAttribute("article", article);

		return "/view/board/content.jsp";

	}

	public String deleteForm(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("deleteForm phone : " + phone_kind);

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("phone_kind", phone_kind);
		return "/view/board/deleteForm.jsp";
	}

	public String deletePro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		//String boardid = request.getParameter("boardid"); // 보드1에서 세션으로 정의...
		String phone_kind = request.getParameter("phone_kind");		
		System.out.println("deletpPro phone : " + phone_kind);// 여기서 헤더에서 정의해줌.
		// 헤더에서 해당 게시판의 종류에 따라서 세션을 다른 값으로 넘겨줌. ex 공지사항 1 / 자유게시판 2 / 등등등
		HttpSession session = request.getSession();
		/*if (boardid != null) {
			session.setAttribute("boardid", boardid);
		}

		if (session.getAttribute("boardid") == null) {
			boardid = "1";
		} else {
			boardid = (String) session.getAttribute("boardid");
		}*/

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("password");
		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();
		int check = dbPro.deleteArticle(num, passwd,phone_kind);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		request.setAttribute("phone_kind", phone_kind);

		return "/view/board/deletePro.jsp";
	}

	public String updateForm(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("updateForm phone : "+ phone_kind);

		String boardid = "";
		HttpSession session = request.getSession();

		/*boardid = (String) session.getAttribute("boardid"); // session의 리턴타입은
															// Object
		if (boardid == null) {
			boardid = "1";
		}*/

		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();
		BoardBean article = dbPro.getArticle(num,phone_kind);

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("phone_kind", phone_kind);
		request.setAttribute("article", article);

		return "/view/board/updateForm.jsp";
	}

	public String updatePro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		BoardBean article = new BoardBean();
		String pageNum = request.getParameter("pageNum");
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("updatePro phone : " + phone_kind);
		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();

		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setTitle(request.getParameter("title"));
		article.setPassword(request.getParameter("password"));
		article.setContent(request.getParameter("content"));
		article.setPhone_kind(request.getParameter("phone_kind"));

		int check = dbPro.updateArticle(article,phone_kind);
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("phone_kind", phone_kind);
		
		System.out.println("a--" + article);

		return "/view/board/updatePro.jsp";
	}

	public String writeForm(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int num = 0;
		int ref = 1;
		int re_step = 0;
		int re_level = 0;
		String pageNum = "";
		System.out.println("asdfasdfasdfasdfasdfasdf");
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("writeForm phone : " + phone_kind);

		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			pageNum = request.getParameter("pageNum");
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}

		request.setAttribute("num", num);
		request.setAttribute("phone_kind", phone_kind);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);

		return "/view/board/writeForm.jsp";
	}

	public String writePro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		BoardBean article = new BoardBean();
		System.out.println("wirteprowirteprowirteprowirtepro");
		String phone_kind = request.getParameter("phone_kind");
		System.out.println("writePro phoen : " + phone_kind);
		/*String ses = (String) session.getAttribute("boardid");
		if (ses == null) {
			ses = "1";
		}*/

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
		article.setPhone_kind(phone_kind);

		System.out.println("a--" + article);
		MybatisBoardDao dbPro = MybatisBoardDao.getInstance();
		dbPro.insertArticle(article,phone_kind);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("phone_kind", phone_kind);
		return "/view/board/writePro.jsp";
	}

}
