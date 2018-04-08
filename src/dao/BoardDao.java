package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.BoardBean;

public class BoardDao extends DBConnector {

	private static BoardDao instace = new BoardDao();

	public static BoardDao getInstance() {
		return instace;
	}

	public void insertArticle(BoardBean article, String phone_kind) throws Exception {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();

		int number = 0;
		String sql = "";

		try {
			pstmt = conn.prepareStatement("select nvl(max(num),0)" + " from mp_board where phone_kind = ?");
			pstmt.setString(1, phone_kind);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				number = rs.getInt(1) + 1;
			} else {
				number = 1;
			}

			if (num != 0) {
				sql = "update mp_board set re_step=re_step+1" + "where ref=? and re_step > ? and phone_kind = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.setString(3, phone_kind);
				pstmt.executeUpdate();
				re_step = re_step + 1;
				re_level = re_level + 1;

			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}

			sql = "insert into mp_board (num,writer,title,password";
			sql += ",reg_date,ref,re_step,re_level,content,phone_kind) ";
			sql += "values (?,?,?,?,sysdate,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, article.getWriter());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, article.getContent());
			pstmt.setString(8, article.getPhone_kind());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	public int getArticleCount(String phone_kind) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		int x = 0;
		try {
			pstmt = conn.prepareStatement("select nvl(count(*),0)" + "from mp_board where phone_kind = ?");
			pstmt.setString(1, phone_kind);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return x;
	}

	public List getArticles(int start, int end, String phone_kind) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List articleList = null;
		String query = "";
		try {
			conn = getConnection();
			query = " select * from " + "( select rownum rnum, a.* " + " from (select num,phone_kind,writer,title,password,"
					+ "content,reg_date,readcount,ref,re_step,re_level"
					+ " from mp_board where phone_kind = ? order by ref desc, re_step)"
					+ " a ) where rnum between ? and ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phone_kind);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					BoardBean article = new BoardBean();
					article.setNum(rs.getInt("num"));
					article.setPhone_kind(rs.getString("phone_kind"));
					article.setWriter(rs.getString("writer"));
					article.setTitle(rs.getString("title"));
					article.setPassword(rs.getString("password"));
					article.setContent(rs.getString("content"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return articleList;
	}

	public BoardBean getArticle(int num, String phone_kind) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean article = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update mp_board set readcount=readcount+1 where num = ? and phone_kind = ?");
			pstmt.setInt(1, num);
			pstmt.setString(2, phone_kind);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from mp_board where num = ? and phone_kind = ?");
			pstmt.setInt(1, num);
			pstmt.setString(2, phone_kind);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new BoardBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setPhone_kind(rs.getString("phone_kind"));
				article.setTitle(rs.getString("title"));
				article.setPassword(rs.getString("password"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_level(rs.getInt("re_level"));
				article.setRe_step(rs.getInt("re_step"));
				article.setContent(rs.getString("content"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return article;
	}

	public int updateArticle(BoardBean article, String phone_kind) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		String sql = "";
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select password from mp_board where num = ? and phone_kind = ?");
			pstmt.setInt(1, article.getNum());
			pstmt.setString(2, article.getPhone_kind());
			System.out.println("num:" + article.getNum());
			rs = pstmt.executeQuery();
			//System.out.println("password check : " + rs.getString("password"));
			if (rs.next()) {
				dbPasswd = rs.getString("password");
				System.out.println("pw : " + rs.getString("password"));
				if (dbPasswd.equals(article.getPassword())) {
					sql = "update mp_board set writer = ?, title = ?, password = ?, ";
					sql += "content = ? where num = ? and phone_kind = ?";
					pstmt = conn.prepareStatement(sql);
					
					
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getTitle());
					pstmt.setString(3, article.getPassword());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.setString(6, article.getPhone_kind());
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		System.out.println(x);
		return x;
	}

	public int deleteArticle(int num, String passwd, String phone_kind) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select password from mp_board where num = ? and phone_kind = ?");
			pstmt.setInt(1, num);
			pstmt.setString(2, phone_kind);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString("password");
				if (dbPasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("delete from mp_board where num = ? phone_kind = ?");
					pstmt.setInt(1, num);
					pstmt.setString(2, phone_kind);
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		System.out.println(x);
		return x;
	}

}