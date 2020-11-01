package Product;

public class ProductSubVO { //메인 상품의 선택 옵션 값을 저장하기 위한 db테이블 자료
	private String ps_id; // 옵션의 id
	private String ps_type; // 해당 옵션은 어떤 종류의 것인지 (ex) 디스플레이, ram 등등)
	private String ps_content; // 옵션의 들어갈 설명 내용
	private int ps_price; // 옵션을 선택했을 때 추가될 가격
	private String p_id; // 선택한 상품의 옵션을 가져오기 위해서 준 상품의 id값
	
	public String getPs_id() {
		return ps_id;
	}
	public void setPs_id(String ps_id) {
		this.ps_id = ps_id;
	}
	public String getPs_type() {
		return ps_type;
	}
	public void setPs_type(String ps_type) {
		this.ps_type = ps_type;
	}
	public String getPs_content() {
		return ps_content;
	}
	public void setPs_content(String ps_content) {
		this.ps_content = ps_content;
	}
	public int getPs_price() {
		return ps_price;
	}
	public void setPs_price(int ps_price) {
		this.ps_price = ps_price;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	
}
