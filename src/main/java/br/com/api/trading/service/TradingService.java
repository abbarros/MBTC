package br.com.api.trading.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.api.trading.client.TradingClient;
import br.com.api.trading.mapper.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradingService {
	
	private static final String HMAC_SHA512 = "HmacSHA512";
	private static final String PATH = "get_account_info";
	
	@Value("${barros.key}")
	private String key;
	
	@Value("${barros.secret}")
	private String secret;
	
	private final TradingClient client;
	
	public Account buy() throws Exception {
		Long millis = System.currentTimeMillis();
		String TAPIMAC = TAPIMAC(PATH, millis);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tapi_method", PATH);
		map.put("tapi_nonce", millis.toString());
		
		return client.buy(map, key, TAPIMAC);
	}
	
	private String TAPIMAC(String method, Long millis) throws InvalidKeyException, NoSuchAlgorithmException {
		String data = "/tapi/v3/?tapi_nonce=" + millis + "&tapi_method=" + method;
		return this.toHMAC(data);
	}
	
	public String toHMAC(String data) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA512);
		Mac mac = Mac.getInstance(HMAC_SHA512);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes()));
	}
	
	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

}
