package huobi;

public class SubModel {
	private String sub;
	private Long id;
	private String type;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public static SubModel getKLineSubModel(String symbol, String period, long id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".kline." + period;
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,kline");
		
		return sub0;
	}
	
	public static SubModel getMarketDepthSubModel(String symbol, String type, long id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".depth." + type;
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,marketdepth");
		
		return sub0;
	}
	
	public static SubModel getTradeDetailSubModel(String symbol, long id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".trade.detail";
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		sub0.setType("sub,tradedetail");
		
		return sub0;
	}
}
