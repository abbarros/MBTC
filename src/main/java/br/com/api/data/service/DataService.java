package br.com.api.data.service;

import org.springframework.stereotype.Service;

import br.com.api.data.client.DataClient;
import br.com.api.data.model.TickerResponse;
import br.com.api.history.domain.Ticker;
import br.com.api.history.repository.TickerHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataService {
	
	private final DataClient data;
	private final TickerHistoryRepository repository;
	
	public TickerResponse ticker() {
		TickerResponse ticker = data.ticker();
		this.save(ticker);
		
		return ticker;
	}
	
	private void save(TickerResponse ticker) {
		Ticker entity = Ticker.builder()
			.last(ticker.getTicker().getLast())
			.high(ticker.getTicker().getHigh())
			.low(ticker.getTicker().getLow())
			.date(ticker.getTicker().getDate())
			.build();
		
		repository.save(entity);
	}

}
