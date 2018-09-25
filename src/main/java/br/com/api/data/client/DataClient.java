package br.com.api.data.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.api.data.model.TickerResponse;

@FeignClient(name = "dataClient", url = "https://www.mercadobitcoin.net/api")
public interface DataClient {
	
	@GetMapping(value = "/LTC/ticker")
	TickerResponse ticker();
	
}

