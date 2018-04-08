package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberBean;

import com.sist.msk.Action;

import dao.BoardDao;
import dao.MemberDao;
import dao.MybatisMemberDao;

public class MemberAction extends Action {

	public String login_signup(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		return "/view/member/login_signup.jsp";
	}

	public String confirmId(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String id = request.getParameter("id");
		MybatisMemberDao manager = MybatisMemberDao.getInstance();
		System.out.println("id : " + id);
		if (id == null)
			System.out.println("id is null");
		else if (id.equals(""))
			System.out.println("id is blank");
		int chk = manager.confirmId(id);
		System.out.println("chk : " + chk);

		request.setAttribute("id", id);
		request.setAttribute("chk", chk);

		return "/local/confirmId.jsp";
	}

	public String confirmNickname(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String nickname = request.getParameter("nickname");
		MybatisMemberDao manager = MybatisMemberDao.getInstance();
		int chk = manager.confirmNickname(nickname);

		request.setAttribute("nickname", nickname);
		request.setAttribute("chk", chk);
		return "/local/confirmNickname.jsp";
	}

	public String memberInsert(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		MemberBean member = new MemberBean();

		member.setEmail(request.getParameter("email"));
		member.setId(request.getParameter("id"));
		member.setNickname(request.getParameter("nickname"));
		member.setPasswd(request.getParameter("passwd"));

		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		MybatisMemberDao manager = MybatisMemberDao.getInstance();
		manager.insertMember(member);

		return "/view/member/memberInsert.jsp";
	}

	public String loginPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		MybatisMemberDao manager = MybatisMemberDao.getInstance();
		MemberBean mb = new MemberBean();
		mb = null;
		HttpSession session = request.getSession();
		System.out.println("mb before login status : " + mb);
		mb = manager.login(id, passwd);
		
	//	System.out.println("id : " + mb.getId());
	//	System.out.println("nn : " + mb.getNickname());

		if (mb != null) {
			session.setAttribute("id", mb.getId());
			session.setAttribute("nickname", mb.getNickname());
		}

		session.setAttribute("mb", mb);

		return "/view/member/loginPro.jsp";

	}

	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();
		session.invalidate();

		return "/view/member/logout.jsp";
	}

	public String findId(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		System.out.println("memberActon findId email : " + email);
		System.out.println("memberActon findId nickname : " + nickname);
		if (email == null) {
			email = "";
		}

		if (nickname == null) {
			nickname = "";
		}

		System.out.println("after if memberActon findId email : " + email);
		System.out
				.println("after if memberActon findId nickname : " + nickname);
		MemberDao manager = MemberDao.getInstance();
		String findid = manager.findId(email, nickname); // ""으로 들어감

		request.setAttribute("nickname", nickname); // 파라미터로 넘어온 닉네임 
		request.setAttribute("email", email); // 파라미터로 넘어온 이메일
		request.setAttribute("findid", findid); // 그 DAO를 거쳐서 넘어온 findid
		return "/local/findId.jsp";
	}

	public String findPassword(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		System.out.println("memberActon findPassword id : " + id);
		System.out.println("memberActon findPassword nickname : " + nickname);
		if (id == null) {
			id = "";
		}

		if (nickname == null) {
			nickname = "";
		}

		System.out.println("after if memberActon findPassword email : " + id);
		System.out.println("after if memberActon findPassword nickname : "
				+ nickname);
		MemberDao manager = MemberDao.getInstance();
		String findpw = manager.findId(id, nickname); // ""으로 들어감

		request.setAttribute("nickname", nickname); // 파라미터로 넘어온 닉네임
		request.setAttribute("id", id); // 파라미터로 넘어온 이메일
		request.setAttribute("findpw", findpw); // 그 DAO를 거쳐서 넘어온 findid

		return "/local/findPassword.jsp";
	}

	public String individual_chkForm(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		return "/view/member/individual_chkForm.jsp";
	}

	public String individual_chkpro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		MemberBean mb = new MemberBean();
		mb = (MemberBean) session.getAttribute("mb");
		String id = mb.getId();
		int check = -1;
		if (password.equals(mb.getPasswd())) {
			check = 1;
		} else {
			check = 0;
		}

		request.setAttribute("check", check);
		return "/view/member/individual_chkpro.jsp";
	}

	public String individual_admin(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute("mb");
		
		request.setAttribute("mb", mb);
		return "/view/member/individual_admin.jsp";
	}
	
	public String updateMember(HttpServletRequest request,
			 HttpServletResponse response)  throws Throwable { 
		
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("mb");

		member.setEmail(request.getParameter("email"));
		member.setNickname(request.getParameter("nickname"));
		member.setPasswd(request.getParameter("passwd"));
		
		System.out.println("after ses id : " + member.getId());
		System.out.println("after ses email : " +member.getEmail());
		System.out.println("after ses nickname: " +member.getNickname());
		System.out.println("after ses passwd: " +member.getPasswd());
		
		
		session.setAttribute("mb", member);
		MybatisMemberDao manager = MybatisMemberDao.getInstance();
		manager.updateMember(member);
		
		
		return  "/view/member/updateMember.jsp"; 
	}

}
