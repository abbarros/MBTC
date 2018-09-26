package br.com.api.data.service;

import org.springframework.stereotype.Service;

import br.com.api.data.client.DataClient;
import br.com.api.data.model.TickerData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataService {
	
	private final DataClient data;
	
	public TickerData ticker() {
		return data.ticker();
	}
	
	public TickerData lastTicker() {
		return data.ticker();
	}

}
