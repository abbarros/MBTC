package br.com.api.trading.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.api.trading.domain.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradingService {
	
	private static final String HMAC_SHA512 = "HmacSHA512";
	private static final String PATH = "get_account_info";
	
	@Value("${barros.token}")
	private String key;
	
	@Value("${barros.username}")
	private String secret;
	
	public Account findByAccount() throws Exception {
		String data = "/tapi/v3/?tapi_method=" + PATH + "&tapi_nonce=1538079117321";
		String token = this.toHMAC(data);
		return null;
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
