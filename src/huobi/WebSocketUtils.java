package huobi;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import com.alibaba.fastjson.JSONObject;

public class WebSocketUtils extends WebSocketClient {

//	private static final String url = "wss://be.huobi.com/ws";
	private static String url = "wss://api.huobi.com/ws";

	private static WebSocketUtils chatclient = null;

	public WebSocketUtils(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public WebSocketUtils(URI serverURI) {
		super(serverURI);
	}

	public WebSocketUtils(URI serverUri, Map<String, String> headers, int connecttimeout) {
		super(serverUri, new Draft_17(), headers, connecttimeout);
	}
	
	/**
	 * set url
	 * if type = "tradepair", then this function will parse "info" as trade pair
	 * if type = "url", then this function will parse "info" as url
	 * @param info
	 * @param type
	 */
	public static String setUrl(String info, String type){
		String lowerCaseInfo = info.toLowerCase();
		String lowerCaseType = type.toLowerCase();
		String returnInfo = "OK";
		boolean isTypeOK = false;
		
		// trade pair
		if(lowerCaseType.equals("tradepair")){
			isTypeOK = true;
			
			boolean isOK = false;
			if(lowerCaseInfo.equals("btccny") || lowerCaseInfo.equals("ltccny")){
				url = "wss://api.huobi.com/ws";
				isOK = true;
			}
				
			if(lowerCaseInfo.equals("ethcny") || lowerCaseInfo.equals("etccny")){
				url = "wss://be.huobi.com/ws";
				isOK = true;
			}
				
			if(lowerCaseInfo.equals("ethbtc") || lowerCaseInfo.equals("ltcbtc") || lowerCaseInfo.equals("etcbtc") || lowerCaseInfo.equals("bccbtc")){
				url = "wss://api.huobi.pro/ws";
				isOK = true;
			}
			
			if(!isOK){
				returnInfo = "error: trade pair not existing";
			}	
		}
		
		// url
		if(lowerCaseType.equals("url")){
			url = info;
			isTypeOK = true;
		}
		
		if(!isTypeOK){
			returnInfo = "error: type not correct! " + returnInfo;
		}
		
		return returnInfo;
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("开流--opened connection");
	}

	@Override
	public void onMessage(ByteBuffer socketBuffer) {
		try {
			String marketStr = CommonUtils.byteBufferToString(socketBuffer);
			String market = CommonUtils.uncompress(marketStr);
			if (market.contains("ping")) {
				System.out.println(market.replace("ping", "pong"));
				// Client 心跳
				chatclient.send(market.replace("ping", "pong"));
			} else {
				System.out.println(" market:" + market);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(String message) {
		System.out.println("接收--received: " + message);
	}

	public void onFragment(Framedata fragment) {
		System.out.println("片段--received fragment: " + new String(fragment.getPayloadData().array()));
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("关流--Connection closed by " + (remote ? "remote peer" : "us"));
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("WebSocket 连接异常: " + ex);
	}

	public static Map<String, String> getWebSocketHeaders() throws IOException {
		Map<String, String> headers = new HashMap<String, String>();
		return headers;
	}

	/*
	private static void trustAllHosts(WebSocketUtils appClient) {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			appClient.setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * "method" should be "sub" or "req"
	 * "topic" in the form of "market.btccny.kline.1min"
	 * @param tradepair
	 * @param method
	 * @param topic
	 * @throws Exception
	 */
	public static void executeWebSocket(String tradepair, Object SubOrReqModel) throws Exception {
		String info = setUrl(tradepair, "tradepair");
		if(!info.equals("OK")){
			System.out.println("Url uncorrect: " + info);
			return;
		}
		
		// WebSocketImpl.DEBUG = true;
		chatclient = new WebSocketUtils(new URI(url), getWebSocketHeaders(), 1000);
		//trustAllHosts(chatclient);
		//trustAllCertificates();
		chatclient.connectBlocking();
		
		chatclient.send(JSONObject.toJSONString(SubOrReqModel));
		
		/*
		// 订阅K线数据 sub 根据自己需要订阅数据
		SubModel subModel = new SubModel();
		subModel.setSub("market.btccny.kline.1min");
		subModel.setId(10000L);
		chatclient.send(JSONObject.toJSONString(subModel));
		
		// 订阅数据深度
		SubModel subModel1 = new SubModel();
		subModel1.setSub("market.btccny.depth.percent10");
		subModel1.setId(10001L);
		chatclient.send(JSONObject.toJSONString(subModel1));
		// 取消订阅省略

		// 请求数据 sub 根据自己需要请求数据
		ReqModel reqModel = new ReqModel();
		reqModel.setReq("market.btccny.depth.percent10");
		reqModel.setId(10002L);
		chatclient.send(JSONObject.toJSONString(reqModel));

		// 请求数据
		ReqModel reqModel1 = new ReqModel();
		reqModel1.setReq("market.btccny.detail");
		reqModel1.setId(10003L);
		chatclient.send(JSONObject.toJSONString(reqModel1));
		System.out.println("send : " + JSONObject.toJSONString(reqModel));
		*/
	}
	
	// Mubing's codes...
	public static void trustAllCertificates(){
		// to access to HTTPS url, we need below codes to trust all certificates
		// Create a new trust manager that trust all certificates
		TrustManager[] trustAllCerts = new TrustManager[]{
			    new X509TrustManager() {
			        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			            return null;
			        }
			        public void checkClientTrusted(
			            java.security.cert.X509Certificate[] certs, String authType) {
			        }
			        public void checkServerTrusted(
			            java.security.cert.X509Certificate[] certs, String authType) {
			        }
			    }
			};
		// Activate the new trust manager
			try {
			    SSLContext sc = SSLContext.getInstance("SSL");
			    sc.init(null, trustAllCerts, new java.security.SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			}
	}
}