package br.com.api.data.service;

import org.springframework.stereotype.Service;

import br.com.api.data.client.DataClient;
import br.com.api.data.model.TickerResponse;
import br.com.api.history.domain.Ticker;
import br.com.api.history.service.TickerHistoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataService {
	
	private final DataClient data;
	private final TickerHistoryService service;
	
	public TickerResponse ticker() {
		Ticker lastTicker = service.lastTicker();
		
		TickerResponse ticker = data.ticker();
		this.save(ticker);
		
		execute(lastTicker, ticker);
		
		return ticker;
	}

	private void save(TickerResponse ticker) {
		Ticker entity = Ticker.builder()
			.last(ticker.getTicker().getLast())
			.high(ticker.getTicker().getHigh())
			.low(ticker.getTicker().getLow())
			.date(ticker.getTicker().getDate())
			.build();
		
		service.save(entity);
	}
	
	private void execute(Ticker lastTicker, TickerResponse ticker) {
		
	}

}
