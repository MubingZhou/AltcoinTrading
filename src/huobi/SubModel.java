package huobi;

public class SubModel {
	private String sub;
	private Long id;

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
		
		return sub0;
	}
	
	public static SubModel getMarketDepthSubModel(String symbol, String type, long id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".depth." + type;
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		return sub0;
	}
	
	public static SubModel getTradeDetailSubModel(String symbol, long id){
		SubModel sub0 = new SubModel();
		
		String topic = "market." + symbol + ".trade.detail";
		
		sub0.setSub(topic);
		sub0.setId(id);
		
		return sub0;
	}
}
