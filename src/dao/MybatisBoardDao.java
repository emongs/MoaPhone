package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.BoardBean;

public class MybatisBoardDao extends MybatisConnector {

	private final String namespace = "ldg.mybatisBoard";
	private static MybatisBoardDao instance = new MybatisBoardDao();
	SqlSession sqlSession;
	
	public static MybatisBoardDao getInstance() {
		return instance;
	}
	
	public int getArticleCount(String phone_kind) throws Exception {
		
		sqlSession = sqlSession();
		System.out.println("getArticleCount ");
		Map map = new HashMap();
		System.out.println("phone_kind :" +phone_kind);
		map.put("phone_kind", phone_kind);
		try {
			return sqlSession.selectOne(namespace + ".getArticleCount",map);
		} finally {
			sqlSession.close();
		}
	}
	
	public List getArticles(int start, int end, String phone_kind) throws Exception {
		sqlSession = sqlSession();
		System.out.println("getArticles start, end, boarid : " + start + " " + end + " " + phone_kind);
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("phone_kind", phone_kind);
		
		try{
			return sqlSession.selectList(namespace + ".getArticles", map);
		} finally {
			sqlSession.close();
		}
	}
	
	public BoardBean getArticle(int num, String phone_kind) throws Exception {
		sqlSession = sqlSession();
		System.out.println("getArticle num phone_kind : " + num + " " + phone_kind);
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("phone_kind", phone_kind);
		BoardBean article = new BoardBean();
		try{
			int result = sqlSession.update(namespace + ".update_readcount",map);
			article = (BoardBean) sqlSession.selectOne(namespace + ".getArticle", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
			return article;
		}
	}
	
	public void insertArticle(BoardBean article, String phone_kind) throws Exception {
		sqlSession = sqlSession();
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		
		
		
		try {
			HashMap map = new HashMap();
			map.put("phone_kind", phone_kind);
			int number = sqlSession.selectOne(namespace + ".insertArticle_new",map);
			if(number != 0) {
				number = number + 1;
			} else {
				number = 1;
			}
			
			if( num != 0) {
				map.put("ref",ref);
				map.put("re_step",re_step);
				sqlSession.update(namespace +".insertArticle_update",map);
				sqlSession.commit();
				re_step = re_step + 1;
				re_level = re_level + 1;
			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			article.setNum(number);
			article.setRef(ref);
			article.setRe_step(re_step);
			article.setRe_level(re_level);
			
			System.out.println("insert : " + article);
			int result = sqlSession.insert(namespace + ".insertArticle_insert",article);
			System.out.println("insert ok : " + result);
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
	}
	
	public int updateArticle(BoardBean article, String phone_kind) throws Exception {
		sqlSession = sqlSession();
		System.out.println("updateArticle: ");
		HashMap map = new HashMap();
		map.put("num", article.getNum());
		map.put("phone_kind", phone_kind);
		int x = -1;

		try {
			String password = (String) sqlSession.selectOne(namespace + ".updateArticle_chpw", map);
			if (password.equals(article.getPassword())) {
				x = sqlSession.update(namespace + ".update_update", article);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		System.out.println(x);
		return x;
	}
	
	public int deleteArticle(int num, String password, String phone_kind) throws Exception {

		sqlSession = sqlSession();
		String dbPasswd = "";
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("password", password);
		map.put("phone_kind", phone_kind);
		int x = -1;
		try {

			dbPasswd = (String) sqlSession.selectOne(namespace + ".delete_chpw", map);
			if (dbPasswd.equals(password)) {
				x = sqlSession.delete(namespace + ".delete_delete", map);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		System.out.println(x);
		return x;
	}
	
	
	
	
	
	
}
	