package br.com.api.history.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.api.history.domain.Ticker;
import br.com.api.history.repository.TickerHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TickerHistoryService {
	
	private final TickerHistoryRepository repository;

	public Collection<Ticker> historyTicker() {
		return repository.findAll();
	}

}
