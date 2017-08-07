package huobi_websocket;

public class SubModel {
	private String sub;
	private String id;
	private String type;
	private String period;
	private String symbol;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static SubModel getKLineSubModel(String symbol, String period, String id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".kline." + period;
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,kline");
		sub0.setPeriod(period);
		sub0.setSymbol(symbol);
		
		return sub0;
	}
	
	public static SubModel getMarketDepthSubModel(String symbol, String type, String id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".depth." + type;
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,marketdepth");
		sub0.setSymbol(symbol);
		
		return sub0;
	}
	
	public static SubModel getTradeDetailSubModel(String symbol, String id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".trade.detail";
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,tradedetail");
		sub0.setSymbol(symbol);
		
		return sub0;
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
