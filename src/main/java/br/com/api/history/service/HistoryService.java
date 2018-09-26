package br.com.api.history.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.api.history.domain.Ticker;
import br.com.api.history.repository.TickerHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {
	
	private final TickerHistoryRepository ticker;
	
	public Ticker save(Ticker entity) {
		return ticker.save(entity);
	}
	
	public Collection<Ticker> findAll() {
		return ticker.findAllByOrderByDateDesc();
	}
	
	public Ticker lastTicker() {
		 return ticker.findFirstByOrderByDateDesc();
	}

}
