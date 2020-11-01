package Product;

public class ProductAddVO { // 상품 구매를 선택하여 장바구니에 담고 나면 해당 상품에 대한 추가 구매 상품 목록을 보여주기 위한 db테이블 자료

	private String p_id; // 메인 상품의 id, 해당 상품을 구입했을 경우, 해당 상품에 쓰일 수 있는 종류의 추가 상품을 불러오기 위해, 메인 상품의 id 값을 이용
	private String pa_name; // 상품의 이름
	private String pa_content; // 상품의 설명
	private int pa_price; // 상품의 가격
	private String pa_pic; // 상품 사진 url
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getPa_name() {
		return pa_name;
	}
	public void setPa_name(String pa_name) {
		this.pa_name = pa_name;
	}
	public String getPa_content() {
		return pa_content;
	}
	public void setPa_content(String pa_content) {
		this.pa_content = pa_content;
	}
	public int getPa_price() {
		return pa_price;
	}
	public void setPa_price(int pa_price) {
		this.pa_price = pa_price;
	}
	public String getPa_pic() {
		return pa_pic;
	}
	public void setPa_pic(String pa_pic) {
		this.pa_pic = pa_pic;
	}
	
}
