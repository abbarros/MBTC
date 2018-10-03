package br.com.api.trading.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.api.config.CoreFeignConfig;
import br.com.api.trading.mapper.Account;
import feign.Headers;

@FeignClient(name = "tradingClient", url = "https://www.mercadobitcoin.net/tapi/v3", configuration = CoreFeignConfig.class)
public interface TradingClient {
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	Account buy(Map<String, ?> formParams,
			@RequestHeader("TAPI-ID") String tapiId,
			@RequestHeader("TAPI-MAC") String tapiMac);

}