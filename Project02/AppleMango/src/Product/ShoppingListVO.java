package Product;

public class ShoppingListVO { // 장바구니 목록을 관리하기 위한 db테이블 자료

	private int l_id; // 장바구니의 번호
	private String p_id; // 상품의 번호
	private String p_name; // 상품의 이름
	private String ps_content; // 상품 설명
	private int ps_price; // 상품 가격
	private String p_pic; // 상품의 사진 url
	private String User_id; // 해당 사용자의 상품을 호출하기 위한 사용자의 id값이 들어간다.

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getPs_content() {
		return ps_content;
	}

	public void setPs_content(String p_content) {
		this.ps_content = p_content;
	}

	public int getPs_price() {
		return ps_price;
	}

	public void setPs_price(int p_price) {
		this.ps_price = p_price;
	}

	public String getP_pic() {
		return p_pic;
	}

	public void setP_pic(String p_pic) {
		this.p_pic = p_pic;
	}

	public String getUser_id() {
		return User_id;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}

}
