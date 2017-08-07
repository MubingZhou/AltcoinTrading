package huobi_websocket;

import java.text.SimpleDateFormat;

public class ReqModel {
	// for req, can request about 300 data each time
	private String req;
	private String id;
	private long from;
	private long to;
	private String type;
	private String period = "";
	private String symbol;

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}
	
	private static ReqModel getKLineReqModel(String symbol, String period, String id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".kline." + period;
		
		req0.setReq(topic);
		req0.setId(id);
		
		req0.setType("req,kline");
		req0.setPeriod(period);
		req0.setSymbol(symbol);
		
		return req0;
	}
	
	public static ReqModel getKLineReqModel(String symbol, String period, String id, String from, String to){
		// "from" inclusive, "to" exclusive
		ReqModel req0 = getKLineReqModel(symbol, period, id);
		
		long fromLong = 0;
		long toLong = 0;
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fromLong = sdf.parse(from).getTime() / 1000 - 1;
			toLong = sdf.parse(to).getTime() / 1000-1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		req0.setFrom(fromLong);
		req0.setTo(toLong);
		
		req0.setType("req,kline");
		req0.setPeriod(period);
		req0.setSymbol(symbol);
		
		return req0;
	}
	
	public static ReqModel getMarketDepthReqModel(String symbol, String type, String id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".depth." + type;
		
		req0.setReq(topic);
		req0.setId(id);
		
		req0.setType("req,marketdepth");
		req0.setSymbol(symbol);
		
		return req0;
	}
	
	public static ReqModel getTradeDetailReqModel(String symbol, String id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".trade.detail";
		
		req0.setReq(topic);
		req0.setId(id);
		
		req0.setType("req,tradedetail");
		req0.setSymbol(symbol);
		
		return req0;
	}
	
	public static ReqModel getMarketDetailReqModel(String symbol, String id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".detail";
		
		req0.setReq(topic);
		req0.setId(id);
		
		req0.setType("req,marketdetail");
		req0.setSymbol(symbol);
		
		return req0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
