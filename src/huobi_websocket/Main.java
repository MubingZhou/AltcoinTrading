package huobi_websocket;

public class Main {
	public static void main(String[] args) {
		try {
			String symbol = "btccny";
			//SubModel sub = SubModel.getKLineSubModel("btccny", "1min", "112");
			//ReqModel req = ReqModel.getKLineReqModel(symbol, "30min", "12", "2017-07-01 00:00:00", "2017-07-06 17:00:00");
			//WebSocketUtils.executeWebSocket("btccny", req);
			
			HistoricalDataDownload.downloadHistData(symbol, "2017-07-02 00:00:00", "2017-08-08 00:00:00", "60min");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
