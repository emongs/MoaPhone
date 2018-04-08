package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import model.MemberBean;

import org.apache.ibatis.session.SqlSession;

public class MybatisMemberDao extends MybatisConnector {

	private final String namespace = "ldg.mybatisMember";
	private static MybatisMemberDao instance = new MybatisMemberDao();
	SqlSession sqlSession;

	public static MybatisMemberDao getInstance() {
		return instance;
	}
	
	public int confirmId(String id) throws Exception {
		sqlSession = sqlSession();
		System.out.println("confirmId Params=id: " + id);
		HashMap map = new HashMap();
		map.put("id", id);
		int x = -1;
		try {
			System.out.println("7777");
			String resID = (String) sqlSession.selectOne(namespace + ".confirmId",map);
			System.out.println("8888");
			System.out.println("resID : " + resID);
			if(resID!=null ) {
				x = 1;
				System.out.println("after resID : " + resID);
			}
		} finally {
			sqlSession.close();
		}
		
		return x;
	}
	
	public int confirmNickname(String nickname) throws Exception {
		sqlSession = sqlSession();
		System.out.println("confirmNickname Params=nickname : " + nickname);
		HashMap map = new HashMap();
		map.put("nickname", nickname);
		int x = -1;
		try {
			String resNickname = (String) sqlSession.selectOne(namespace + ".confirmNickname",map);
			System.out.println("resNickname : " + resNickname);
			if(resNickname!=null ) {
				x = 1;
				System.out.println("after resNickname : " + resNickname);
			}
			
		} finally {
			sqlSession.close();
		}
		
		return x;

	}
	
	public void insertMember(MemberBean member) throws Exception {
		sqlSession = sqlSession();
		try {
			
			int result = sqlSession.insert(namespace + ".insertMember",member);
			System.out.println("insertMember ok : " + result);
		
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	public MemberBean login(String id, String passwd) throws Exception {
		sqlSession = sqlSession();
		MemberBean mb = new MemberBean();
		mb = null;
		//HttpSession session = 
		System.out.println("param id : " + id);
		System.out.println("param pw : " + passwd);
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		System.out.println("11");
		try {
			mb = (MemberBean) sqlSession.selectOne(namespace + ".login",map);
			System.out.println("mybatisMemberDao login mb id : " + mb.getId());
			System.out.println("mybatisMemberDao login mb email : " + mb.getEmail());
		} finally {
			sqlSession.close();
			return mb;
		}
	}
	
	public String findId(String email, String nickname) throws Exception {
		sqlSession = sqlSession();
		String id = null;
		
		HashMap map = new HashMap();
		map.put("email",email);
		map.put("nickname",nickname);
		try {

			id = (String) sqlSession.selectOne(namespace+".findId",map);
			if (id!=null) {
				System.out.println("find id : " + id);
			} else {
				id = "";
			}
		} finally {
			sqlSession.close();
		}

		System.out.println("MyBatisMemberDao findID return value : " + id);
		return id;
	}
	
	public String findPassword(String id, String nickname) throws Exception {
		sqlSession = sqlSession();
		System.out.println("findpw Params id, nickname : " + id + " / "
				+ nickname);
		String passwd = null;
		HashMap map = new HashMap();
		map.put("id",id);
		map.put("nickname",nickname);

		try {
			passwd = (String) sqlSession.selectOne(namespace+".findPassword",map);
			if (passwd!=null) {
				System.out.println("find pw : " + passwd);

			} else {
				passwd = "";
			}
		} finally {
			sqlSession.close();
		}

		System.out.println("MybatismemberDao findPassword return value : " + passwd);
		return passwd;
	}
	
	
	public MemberBean updateMember(MemberBean member) throws Exception {
		sqlSession = sqlSession();
		HashMap map = new HashMap();
		int result = -1;
		
		map.put("nickname", member.getNickname());
		map.put("passwd", member.getPasswd());
		map.put("email", member.getEmail());
		map.put("id", member.getId());
		try {
			result = sqlSession.update(namespace+".updateMember",map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return member;
	}
}
