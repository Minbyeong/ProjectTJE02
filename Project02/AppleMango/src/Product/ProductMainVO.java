package Product;

public class ProductMainVO { // 메인 상품의 자료를 관리하기 위한 db테이블 자료
	private String p_id; // 상품의 등록 id
	private String p_name; // 상품의 이름
	private String p_content; // 상품의 기본 설명(제품의 구성)
	private int p_price; // 상품의 가격
	private String p_pic; // 상품의 사진 url
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
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_pic() {
		return p_pic;
	}
	public void setP_pic(String p_pic) {
		this.p_pic = p_pic;
	}
	
}
