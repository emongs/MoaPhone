package select;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.PhoneBean;

public class Selector {

	/*
	 * public static void main(String[] args) { Selector sel = new Selector();
	 * sel.getGongsi(); }
	 */
	public static String encodeURIComponent(String s) {
		String result = null;

		try {
			result = URLEncoder.encode(s, "euc-kr").replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!").replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		}

		// This exception should never occur.
		catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	public List selectPhone(String si, String gu) {

		System.out.println("4546464");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPass = "1111";
		String makeCompany = "";
		List<String> li = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);

			if (si == null) {
				li.add("");
				li.add("삼성");
				li.add("LG");
				li.add("APPLE");

				return li;

			} else if (gu == null && si != null) {
				System.out.println("ss" + si);
				String query = "select model_kor from  " + si;
				stmt = conn.createStatement();
				li.add("");
				rs = stmt.executeQuery(query);
				if (rs.next()) {
					do {
						li.add(rs.getString(1));
					} while (rs.next());
				}
				System.out.println("123132123" + li);
				return li;
			} else if (si != null && gu != null) {
				li.add("");
				li.add("12개월");
				li.add("24개월");
				return li;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public PhoneBean showPrice(String si, String gu, String dong) {

		// si => 제조사 gu => 핸드폰 이름
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPass = "1111";
		String query = ""; // 이 쿼리 하나로 제조사의 핸드폰 모델의 정보를 다 가져올수 있음 .. 통신사별 가격까지.
		String KTquery = "";
		String LGquery = "";
		PhoneBean pb = new PhoneBean();
		System.out.println("Selector showPrice si : " + si);
		System.out.println("Selector showPrice gu : " + gu);
		System.out.println("Selector showPrice dong : " + dong);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
			System.out.println("1");
			query = "select * from " + si + " where model_kor = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gu);

			System.out.println("2");
			rs = pstmt.executeQuery();
			System.out.println("3");
			if (rs.next()) {
				if (si.equals("APPLE")) {
					pb.setApple_ser(rs.getInt("apple_ser"));
				} else if (si.equals("LG")) {
					pb.setLg_ser(rs.getInt("lg_ser"));
				} else {
					pb.setSs_ser(rs.getInt("ss_ser"));
				}
				pb.setModel_kor(gu);
				if (si.equals("APPLE")) {
					pb.setModel_eng(rs.getString("model_eng"));
					pb.setPrice_kt(rs.getString("price_kt"));
					pb.setPrice_sk(rs.getString("price_sk"));
					pb.setPrice_lg(rs.getString("price_lg"));
					pb.setRelease_date(rs.getTimestamp("release_date"));
					pb.setPhoto(rs.getString("photo"));

					pb.setOs(rs.getString("os"));
					pb.setStandard(rs.getString("standard"));
					pb.setMemory(rs.getString("memory"));
					pb.setBattery(rs.getString("battery"));
					pb.setCpu(rs.getString("cpu"));
					pb.setDisplay(rs.getString("display"));
					pb.setCamera(rs.getString("camera"));
				} else {
					pb.setModel_eng_kt(rs.getString("model_eng_kt"));
					System.out.println("db select model_eng with rs : "
							+ rs.getString("model_eng_kt"));
					pb.setModel_eng_sk(rs.getString("model_eng_sk"));
					pb.setModel_eng_lg(rs.getString("model_eng_lg"));
					pb.setPrice_kt(rs.getString("price_kt"));
					pb.setPrice_sk(rs.getString("price_sk"));
					pb.setPrice_lg(rs.getString("price_lg"));
					pb.setRelease_date(rs.getTimestamp("release_date"));
					pb.setPhoto(rs.getString("photo"));
					pb.setOs(rs.getString("os"));
					pb.setStandard(rs.getString("standard"));
					pb.setMemory(rs.getString("memory"));
					pb.setBattery(rs.getString("battery"));
					pb.setCpu(rs.getString("cpu"));
					pb.setDisplay(rs.getString("display"));
					pb.setCamera(rs.getString("camera"));
				}
			}

		} catch (Exception e) {

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

		return pb;

	}

	public String returnURL(PhoneBean pb, String sellType, String makeBrand,
			String goods_company) {
		// goods_company : SKT,KT,LGT

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		if (day.length() == 1) {
			day = "0" + day;
		}
		String brand_name = null; // c
		String goods_selltype = null; // c
		String goods_brand = null; // c
		String goods_price = null; // c
		String url = null;

		if (makeBrand.equals("ss")) {
			brand_name = encodeURIComponent("삼성전자");
		} else if (makeBrand.equals("LG")) {
			brand_name = encodeURIComponent("LG전자");
		} else {
			brand_name = encodeURIComponent("애플");
		}

		goods_selltype = sellType.substring(0, 2); // 숫자 나오니까 노상관

		if (goods_company.equals("SKT")) {
			goods_price = pb.getPrice_sk();
			goods_brand = encodeURIComponent(goods_price + "/"
					+ pb.getModel_kor() + "/" + pb.getModel_eng_sk());
		} else if (goods_company.equals("KT")) {
			goods_price = pb.getPrice_kt();
			goods_brand = encodeURIComponent(goods_price + "/"
					+ pb.getModel_kor() + "/" + pb.getModel_eng_kt());
		} else {
			goods_price = pb.getPrice_lg();
			goods_brand = encodeURIComponent(goods_price + "/"
					+ pb.getModel_kor() + "/" + pb.getModel_eng_lg());
		}

		if (brand_name.equals("%BE%D6%C7%C3")) {
			goods_brand = encodeURIComponent(goods_price + "/"
					+ pb.getModel_kor() + "/" + pb.getModel_eng());
		}

		url = "http://www.iadmin.co.kr/app/calculator_new.asp?"
				+ "goods_company=" + goods_company + "&brand_name="
				+ brand_name + "&goods_selltype="
				+ goods_selltype
				+ "&goods_brand="
				+ goods_brand // price/한글모델/영어모델 ex)갤럭시S8/SM-G950N
				+ "&goods_family=" + "&goods_price=" + goods_price
				+ "&add_price=15" + "&regy=" + year + "&regm=" + mon + "&regd="
				+ day;

		return url;

	}


	public List getGongsiList(String getURL) {

		String url = getURL;
		List<String> gongsi = new ArrayList();

		Document doc = null;
		String line = "";
		try {
			doc = Jsoup.connect(url).get();

			Elements media1 = doc
					.select("table tr:nth-child(2n+1) td:nth-child(9n+0) strong"); // 공시지원금
																					// 월
			
			System.out.println("gongsi");
			for (Element src : media1) {

				String templine = src.text();
				gongsi.add(templine);
			}

			for (String s : gongsi) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-genefared catch block
			e.printStackTrace();
		}

		return gongsi;

	}
	
	public List getSelectList(String getURL) {

		String url = getURL;
		List<String> select = new ArrayList();

		Document doc =  null;
		String line = "";
		try {
			doc = Jsoup.connect(url).get();
			Elements media2 = doc
					.select("table tr:nth-child(2n) td:nth-child(8n+0) strong"); // 선택약정
																					// 월
																					// 청구금액

			System.out.println("select");
			
			for (Element src : media2) {

				String templine = src.text();
				select.add(templine);
			}
			
			for (String s : select) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-genefared catch block
			e.printStackTrace();
		}

		return select;

	}
	
	public List<String> getFareList(String getURL) {

		String url = getURL;
		List<String> fare = new ArrayList();

		Document doc=null;
		String line = "";
		try {
			doc = Jsoup.connect(url).get();

			Elements media3 = doc.select("table td:nth-child(1) strong"); // 요금제리스트

			
			System.out.println("fare");
			for (Element src : media3) {

				String templine = src.text();
				fare.add(templine);
			}
			
			for (String s : fare) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-genefared catch block
			e.printStackTrace();
		}

		return fare;

	}
}
