package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MemberBean;
import dao.MemberDao;

public class MemberDao extends DBConnector {

	private static MemberDao instance = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		return instance;
	}

	public void insertMember(MemberBean member) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into MP_MEMBER values "
					+ "(?,?,?,sysdate,?)");

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	public MemberBean updateMember(MemberBean member) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("update MP_MEMBER set nickname = ? , passwd = ? , email = ? where id = ?");

			pstmt.setString(1, member.getNickname());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		
		return member;
	}

	public int confirmId(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("confirmId Params : " + id);

		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select id from MP_MEMBER where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = 1;
			} else {
				x = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	public int confirmNickname(String nickname) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select nickname from MP_MEMBER where nickname = ?");
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = 1;
			} else {
				x = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	public MemberBean login(String id, String pw) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		System.out.println("param id : " + id);
		System.out.println("param pw : " + pw);

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select * from MP_MEMBER where id = ? and passwd = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			System.out.println("ssss");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("gggg");
				mb.setId(rs.getString("id"));
				mb.setEmail(rs.getString("email"));
				mb.setNickname(rs.getString("nickname"));
				mb.setPasswd(rs.getString("passwd"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				System.out.println("id :" + mb.getId());
				System.out.println("nickname :" + mb.getNickname());
			} else {
				mb = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return mb;

	}

	public String findId(String email, String nickname) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("findId Params email, nickname : " + email + " / "
				+ nickname);
		String id = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select * from MP_MEMBER where email = ? and nickname = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
				System.out.println("find id : " + id);

			} else {
				id = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		System.out.println("findID return value : " + id);
		return id;
	}

	public String findPassword(String id, String nickname) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("findpw Params id, nickname : " + id + " / "
				+ nickname);
		String password = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select passwd from MP_MEMBER where id = ? and nickname = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString("passwd");
				System.out.println("find pw : " + password);

			} else {
				password = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		System.out.println("memberDao findPassword return value : " + password);
		return password;
	}
	
	

}
