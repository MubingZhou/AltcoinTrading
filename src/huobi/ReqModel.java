package huobi;

import java.text.SimpleDateFormat;

public class ReqModel {
	private String req;
	private Long id;
	private long from;
	private long to;

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public static ReqModel getKLineReqModel(String symbol, String period, long id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".kline." + period;
		
		req0.setReq(topic);
		req0.setId(id);
		
		return req0;
	}
	
	public static ReqModel getKLineReqModel(String symbol, String period, long id, String from, String to){
		ReqModel req0 = getKLineReqModel(symbol, period, id);
		
		long fromLong = 0;
		long toLong = 0;
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fromLong = sdf.parse(from).getTime();
			toLong = sdf.parse(to).getTime();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		req0.setFrom(fromLong);
		req0.setTo(toLong);
		
		return req0;
	}
	
	public static ReqModel getMarketDepthReqModel(String symbol, String type, long id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".depth." + type;
		
		req0.setReq(topic);
		req0.setId(id);
		
		return req0;
	}
	
	public static ReqModel getTradeDetailReqModel(String symbol, long id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".trade.detail";
		
		req0.setReq(topic);
		req0.setId(id);
		
		return req0;
	}
	
	public static ReqModel getMarketDetailReqModel(String symbol, long id){
		ReqModel req0 = new ReqModel();
		
		String topic = "market." + symbol + ".detail";
		
		req0.setReq(topic);
		req0.setId(id);
		
		return req0;
	}
	
}
