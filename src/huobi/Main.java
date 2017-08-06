package huobi;

public class Main {
	public static void main(String[] args) {
		try {
			SubModel sub = SubModel.getKLineSubModel("btccny", "1min", 112);
			WebSocketUtils.executeWebSocket("btccny", sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
