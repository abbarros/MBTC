package br.com.api.history.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.history.domain.Ticker;

public interface TickerHistoryRepository extends MongoRepository<Ticker, String> {}
