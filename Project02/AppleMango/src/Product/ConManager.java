package Product;

public class ConManager {

	static ConManager con;//null;
	
	public static ConManager getInstance() { //db연결에 2단계 중에서 커넥션을 해주는 부분. 커넥션이 없을 경우 진해하겠다.
		if (con == null) {
			con = new ConManager();
		}
		return con;
	}
}
