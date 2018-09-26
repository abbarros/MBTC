package br.com.api.history.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.history.domain.Ticker;

public interface TickerHistoryRepository extends MongoRepository<Ticker, String> {

	Collection<Ticker> findAllByOrderByDateDesc();
	
	Ticker findFirstByOrderByDateDesc();
	
}
