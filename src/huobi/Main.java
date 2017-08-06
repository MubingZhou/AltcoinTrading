package huobi;

public class Main {
	public static void main(String[] args) {
		try {
			String symbol = "btccny";
			SubModel sub = SubModel.getKLineSubModel("btccny", "1min", 112);
			ReqModel req = ReqModel.getKLineReqModel(symbol, "60min", 12, "2017-08-06 00:00:00", "2017-08-06 17:00:00");
			WebSocketUtils.executeWebSocket("btccny", req);
			
			//if(req.getClass().toString().equals("class huobi.ReqModel"))
			//	System.out.println("sd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
