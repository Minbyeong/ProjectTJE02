package Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Product.DBConnectionMgr;

public class ProductDAO {

	Connection con;
	DBConnectionMgr dbcp;

	public ProductDAO() throws Exception { //1, 2 단계의 연결은 dbcp가 처리를 해준다.
		dbcp = DBConnectionMgr.getInstance(); // 3, 4단계의 코딩만 내가 하면 된다.
		System.out.println("2. db연결 성공.!!");
	}

	public boolean create(ShoppingListVO vo) throws Exception { // 상품을 구매했을 경우 장바구니 목록에 들어가도록 create문 작성
		con = dbcp.getConnection();
		// 3. sql문을 만들다.
		String sql = "insert into shoppinglist values (null, ?, ?, ?, ?, ?, ?)";// 값이 없으면 null을 넣어주면됨, 자동 증가 값일 경우 해당
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getP_id());
		ps.setString(2, vo.getP_name());
		ps.setString(3, vo.getPs_content());
		ps.setInt(4, vo.getPs_price());
		ps.setString(5, vo.getP_pic());
		ps.setString(6, vo.getUser_id());
		System.out.println("3. SQL문 생성 성공.!!");
		// 4. sql문은 전송
		int row = ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");

		boolean result = false; //반환값을 boolean으로 설정한 이유는 들어갔는지 않들어갔는지에 대한 결과를 받아보기 위해서
		if (row == 1) {
			result = true;
		}
		dbcp.freeConnection(con, ps);
		return result;
	}

	public ProductMainVO one(String p_id) throws Exception { // 선택한 메인 상품의 구성 요소를 찾고 db의 값을 불러온다.
		con = dbcp.getConnection();
		// 3. sql문을 만들다.
		String sql = "select * from product_main where p_id = ?";// 값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p_id);

		System.out.println("3. SQL문 생성 성공.!!");
		// 4. sql문은 전송
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 정송 성공.!!");
		ProductMainVO bag = new ProductMainVO();// 가방을 만들어서,
		if (rs.next() == true) {// 결과가 있는지 없는지 체크 해주는 메서드
			// 가방에 넣기
			bag.setP_id(rs.getString("p_id")); // 커서(위치 알려주는친구)
			bag.setP_name(rs.getString("p_name"));
			bag.setP_content(rs.getString("p_content"));
			bag.setP_price(rs.getInt("p_price"));
			bag.setP_pic(rs.getString("p_pic"));
		}
		dbcp.freeConnection(con, ps, rs);
		return bag;
	}

	public ArrayList<ProductSubVO> subcon(String p_id) throws Exception { // 해당 메인 상품의 선택 옵션들의 값을 담기 위한 list
		con = dbcp.getConnection();
		// 3. sql문을 만든다.
		String sql = "select ps_content from product_sub where p_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p_id);
		// 4. sql문은 전송
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 전송 성공.!!");
		ArrayList<ProductSubVO> list = new ArrayList<ProductSubVO>();
		while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
			ProductSubVO bag = new ProductSubVO();// 가방만들어서,
			// 가방에 넣기
			bag.setPs_content(rs.getString("ps_content"));
			// 컨테이너에 넣는다.
			list.add(bag);
		}
		dbcp.freeConnection(con, ps, rs);
		return list;
	}

	public ArrayList<ProductSubVO> subprc(String p_id) throws Exception { // 해당 메인 상품의 선택 옵션들의 값을 담기 위한 list
		con = dbcp.getConnection();
		// 3. sql문을 만든다.
		String sql = "select ps_price from product_sub where p_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p_id);
		// 4. sql문은 전송
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 전송 성공.!!");
		ArrayList<ProductSubVO> list = new ArrayList<ProductSubVO>();
		while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
			ProductSubVO bag = new ProductSubVO();// 가방만들어서,
			// 가방에 넣기
			bag.setPs_price(rs.getInt("ps_price"));
			// 컨테이너에 넣는다.
			list.add(bag);
		}
		dbcp.freeConnection(con, ps, rs);
		return list;
	}

	public JSONObject add(String p_id) throws Exception { // jsonobject의 사용 연습 // 추가 상품의 전체 값을 가져와 jsonobject로 만들어서 저장하는 과정
		con = dbcp.getConnection();
		// 3. sql문을 만든다.
		String sql = "select * from product_add where p_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, p_id);
		// 4. sql문은 전송
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 전송 성공.!!");
		List<ProductAddVO> list = new ArrayList<ProductAddVO>();
		while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
			ProductAddVO bag = new ProductAddVO();// 가방만들어서,
			// 가방에 넣기
			bag.setP_id(rs.getString("p_id"));
			bag.setPa_name(rs.getString("pa_name"));
			bag.setPa_content(rs.getString("pa_content"));
			bag.setPa_price(rs.getInt("pa_price"));
			bag.setPa_pic(rs.getString("pa_pic"));
			// 컨테이너에 넣는다.
			list.add(bag);
		}
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();// 배열이 필요할때
		for (int i = 0; i < list.size(); i++) { // 배열
			JSONObject sObject = new JSONObject();// 배열 내에 들어갈 json
			sObject.put("p_id", list.get(i).getP_id());
			sObject.put("pa_name", list.get(i).getPa_name());
			sObject.put("pa_content", list.get(i).getPa_content());
			sObject.put("pa_price", list.get(i).getPa_price());
			sObject.put("pa_pic", list.get(i).getPa_pic());
			jArray.add(sObject);
		}

		obj.put("add", jArray);// 배열을 넣음
		dbcp.freeConnection(con, ps, rs);
		return obj;
	}

	public JSONObject shopping(String user_id) throws Exception { // 사용자의 장바구니 목록 전체를 jsonobject를 사용하여 담아내는 과정.
		con = dbcp.getConnection();
		// 3. sql문을 만든다.
		String sql = "select * from shoppinglist where user_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user_id);
		// 4. sql문은 전송
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 전송 성공.!!");
		List<ShoppingListVO> list = new ArrayList<ShoppingListVO>();
		while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
			ShoppingListVO bag = new ShoppingListVO();// 가방만들어서,
			// 가방에 넣기
			bag.setL_id(rs.getInt("l_id"));
			bag.setP_id(rs.getString("p_id"));
			bag.setP_name(rs.getString("p_name"));
			bag.setPs_content(rs.getString("ps_content"));
			bag.setPs_price(rs.getInt("ps_price"));
			bag.setP_pic(rs.getString("p_pic"));
			bag.setUser_id(rs.getString("user_id"));
			// 컨테이너에 넣는다.
			list.add(bag);
		}
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();// 배열이 필요할때
		for (int i = 0; i < list.size(); i++) { // 배열
			JSONObject sObject = new JSONObject();// 배열 내에 들어갈 json
			sObject.put("l_id", list.get(i).getL_id());
			sObject.put("p_id", list.get(i).getP_id());
			sObject.put("p_name", list.get(i).getP_name());
			sObject.put("ps_content", list.get(i).getPs_content());
			sObject.put("ps_price", list.get(i).getPs_price());
			sObject.put("p_pic", list.get(i).getP_pic());
			sObject.put("user_id", list.get(i).getUser_id());
			jArray.add(sObject);
		}

		obj.put("shopping", jArray);// 배열을 넣음
		dbcp.freeConnection(con, ps, rs);
		return obj;
	}

	public boolean delete(int l_id) throws Exception {
		con = dbcp.getConnection();
		// 3. sql문을 만들다.
		String sql = "delete from shoppinglist where l_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, l_id);
		System.out.println("3. SQL문 생성 성공.!!");

		int row = ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");

		boolean result = false;
		if (row == 1) {
			result = true;
		}
		dbcp.freeConnection(con, ps);
		return result;

	}

}
